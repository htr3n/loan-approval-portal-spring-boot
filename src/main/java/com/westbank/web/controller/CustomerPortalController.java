package com.westbank.web.controller;

import com.westbank.entity.Customer;
import com.westbank.entity.LoanContract;
import com.westbank.entity.LoanFile;
import com.westbank.entity.LoanFileStatus;
import com.westbank.repository.CustomerRepository;
import com.westbank.repository.LoanContractRepository;
import com.westbank.repository.LoanFileRepository;
import com.westbank.web.form.TaskForm;
import com.westbank.web.guard.CustomerAccessGuard;
import com.westbank.ws.LoanApprovalProcessProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(CustomerPortalController.PATH)
public class CustomerPortalController {

    private static Logger LOG = LoggerFactory.getLogger(CustomerPortalController.class);

    static final String PATH = "/portal";
    static final String PORTAL_VIEW = "customer/portal";
    static final String ACTION_SIGN = "sign";
    static final String ACTION_CANCEL = "cancel";
    static final String ACTION_RELOAD = "reload";
    static final String ACTION_GRANT = "grant";
    static final String ACTION_DENY = "deny";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanFileRepository loanFileRepository;

    @Autowired
    private LoanApprovalProcessProxy processProxy;

    @Autowired
    private LoanContractRepository loanContractRepository;

    @Autowired
    private CustomerAccessGuard guard;

    @ModelAttribute("loanList")
    public TaskForm createTaskForm() {
        return new TaskForm();
    }

    @GetMapping
    protected String prepare(HttpSession session) {

        if (!guard.isAuthenticated(session))
            return guard.redirect(session);

        loadCustomerData(session);
        session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_PORTAL);
        session.removeAttribute(CustomerSession.PROCESS_STATUS);
        session.removeAttribute(CustomerSession.PROCESS_STATUS_KEY);
        return PORTAL_VIEW;
    }

    @PostMapping
    protected String processLoanList(@ModelAttribute TaskForm form, HttpSession session) {

        if (!guard.isAuthenticated(session))
            return guard.redirect(session);

        session.setAttribute(CustomerSession.NAV_INDEX, CustomerSession.NAV_PORTAL);
        final LocalDate actionTime = LocalDate.now();

        final Customer customer = (Customer) session.getAttribute(CustomerSession.ACTIVE_AUTHENTICATION);
        final String action = form.getAction();
        LOG.debug("Processing portal action='{}' with customer='{}'", action, customer);

        if (ACTION_RELOAD.equalsIgnoreCase(action)) {
            loadCustomerData(session);
        } else {
            boolean isGiveAccess = false;
            boolean isAccessible = false;

            if (ACTION_GRANT.equalsIgnoreCase(action)) {
                isGiveAccess = true;
                isAccessible = true;

            } else if (ACTION_DENY.equalsIgnoreCase(action)) {
                isGiveAccess = true;
                isAccessible = false;
            }

            if (isGiveAccess) {
                final String loanFileId = form.getLoanFileId();
                final LoanFile loanFile = loanFileRepository.findById(loanFileId).orElse(null);

                if (loanFile != null) {
                    loanFile.setAccessSensitiveData(isAccessible);
                    loanFileRepository.save(loanFile);
                    LOG.debug("Has customer given access to the bank? " + isAccessible);
                }
            } else {
                boolean isAccepted = ACTION_SIGN.equalsIgnoreCase(action);
                final String loanFileId = form.getLoanFileId();
                final Long contractId = form.getContractId();
                Optional<LoanContract> contract = loanContractRepository.findById(contractId);
                LOG.debug("Send customer's decision to the process");
                boolean isOK = processProxy.informCustomerDecision(customer.getId(),
                        customer.getFirstName() + " " + customer.getLastName(), loanFileId, contractId,
                        isAccepted);
                if (!isOK) {
                    session.setAttribute(CustomerSession.PROCESS_STATUS, CustomerSession.PROCESS_STATUS_ERROR);
                    session.setAttribute(CustomerSession.PROCESS_STATUS_KEY, CustomerSession.MSG_INVOCATION_ERR);
                } else {
                    // update contract status if the customer accepted, ...
                    if (isAccepted) {
                        if (contract.isPresent()) {
                            LOG.debug("Update the contract status to {} ", LoanFileStatus.ACCEPTED.name());
                            contract.get().setSignedByCustomer(actionTime);
                            final LoanFile loanFile = contract.get().getLoanFile();
                            if (loanFile != null) {
                                loanFile.setStatus(LoanFileStatus.ACCEPTED);
                            }
                            loanContractRepository.save(contract.get());
                            loanFileRepository.save(loanFile);
                        }
                    } else {
                        // ... otherwise, set the loan file status to CANCELED
                        loanFileRepository.findById(loanFileId).ifPresent(loanFile -> {
                            loanFile.setStatus(LoanFileStatus.CANCELED);
                            loanFileRepository.save(loanFile);
                        });
                    }
                }
            }
            loadCustomerData(session);
        }
        return PORTAL_VIEW;
    }

    private void loadCustomerData(HttpSession session) {
        final Customer customer = (Customer) session.getAttribute(CustomerSession.ACTIVE_AUTHENTICATION);
        if (customer != null) {
            final List<LoanFile> loans = loanFileRepository.findAllByBorrowerId(customer.getId());
            session.setAttribute(CustomerSession.LOAN_FILES, loans);
        }
        // TODO
        //final List<LoanContract> contracts = loanContractRepository.findAllByBorrowerId(customerId);
        //session.setAttribute(Constants.SESSION_CONTRACTS, contracts);
        //final Customer customer = customerRepository.findById(customerId).orElse(null);
        session.setAttribute(CustomerSession.ACTIVE_AUTHENTICATION, customer);
    }
}
