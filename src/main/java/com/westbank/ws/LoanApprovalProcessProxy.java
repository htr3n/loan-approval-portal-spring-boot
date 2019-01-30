package com.westbank.ws;

import com.westbank.entity.Address;
import com.westbank.entity.Customer;
import com.westbank.helper.Generator;
import com.westbank.helper.JaxbUtil;
import com.westbank.repository.CustomerRepository;
import com.westbank.web.form.LoanForm;
import com.westbank.ws.process.loanapproval._2019._01.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

public class LoanApprovalProcessProxy {

    private static final Logger LOG = LoggerFactory.getLogger(LoanApprovalProcessProxy.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Generator generator;

    @Autowired
    @Qualifier("processClient")
    private LoanApproval processClient;

    public boolean startProcess(LoanForm form, Customer customer) {
        final LoanApprovalRequest request = createLoanRequest(form, customer);
        Assert.notNull(request, "Loan approval request must be not null");
        request.setLoanFileId(generator.newLoanFileId());
        Assert.notNull(request.getLoanFileId(), "Loan file ID must be not null");
        try {
            LOG.debug("startProcess() -> request: {}", request);
            LOG.trace(JaxbUtil.toXml(request));
            processClient.start(request);
            return true;
        } catch (Exception e) {
            LOG.error("Error when starting the process: " + e.getMessage(), e);
        }
        return false;
    }

    public boolean processedByCreditBroker(String staffId, String role, String loanFileId) {
        final CreditBrokerDecision request = new CreditBrokerDecision();
        byte[] tokenString = Base64.getEncoder().encode((staffId + "/" + role).getBytes());
        request.setStaffId(staffId);
        request.setStaffRole(role);
        request.setLoanFileId(loanFileId);
        request.setSecureToken(tokenString);
        try {
            LOG.debug("processedByCreditBroker() -> request: {}", request);
            LOG.trace(JaxbUtil.toXml(request));
            processClient.processedByCreditBroker(request);
            return true;
        } catch (final Exception e) {
            LOG.error("Staff " + staffId + "(role=" + role + "): " + e.getMessage(), e);
        }

        return false;
    }

    public boolean processedByClerkOrSupervisor(String staffId, String role, String loanFileId) {
        final ClerkOrSupervisorDecision request = new ClerkOrSupervisorDecision();
        byte[] tokenString = Base64.getEncoder().encode((staffId + "/" + role).getBytes());
        request.setStaffId(staffId);
        request.setStaffRole(role);
        request.setLoanFileId(loanFileId);
        request.setSecureToken(tokenString);
        try {
            LOG.debug("processedByClerkOrSupervisor() -> request: {}", request);
            LOG.trace(JaxbUtil.toXml(request));
            processClient.processedByClerkOrSupervisor(request);
            return true;
        } catch (final Exception e) {
            LOG.error("Staff " + staffId + "(role=" + role + "): " + e.getMessage(), e);
        }
        return false;
    }

    public boolean informManagerDecision(String staffId, String role, String loanFileId, boolean isGranted) {
        final ManagerDecision request = new ManagerDecision();
        byte[] tokenString = Base64.getEncoder().encode((staffId + "/" + role).getBytes());
        request.setStaffId(staffId);
        request.setStaffRole(role);
        request.setLoanFileId(loanFileId);
        request.setGranted(isGranted);
        request.setSecureToken(tokenString);
        try {
            LOG.debug("informManagerDecision() -> request: {}", request);
            LOG.trace(JaxbUtil.toXml(request));
            processClient.decidedByManager(request);
            return true;
        } catch (Exception e) {
            LOG.error("Staff " + staffId + "(role=" + role + "): " + e.getMessage(), e);
        }
        return false;
    }

    public boolean informCustomerDecision(Long customerId, String customerName, String loanFileId, Long contractId, boolean accepted) {
        LOG.info("Customer signed contract #'{}'", contractId);
        final CustomerDecision request = createCustomerSignature(customerId, customerName, loanFileId, contractId);
        Assert.notNull(request, "Customer decision request must not be null");
        request.setAccepted(accepted);
        try {
            LOG.debug("informCustomerDecision() -> request: {}", request);
            LOG.trace(JaxbUtil.toXml(request));
            processClient.informedByCustomer(request);
            return true;
        } catch (Exception e) {
            LOG.error("The customer cannot sign the process due to " + e.getMessage(), e);
        }
        return false;
    }

    public boolean signedContractByManager(String staffId, String staffName, String loanFileId, Long contractId) {
        LOG.info("Manager signed contract #'" + contractId + "'");
        final ManagerSignature request = createManagerSignature(staffId, staffName, loanFileId, contractId);
        Assert.notNull(request, "The manager signature request must not be null");

        try {
            LOG.debug("signedContractByManager() -> request: {}", request);
            LOG.trace(JaxbUtil.toXml(request));
            processClient.signedByManager(request);
            return true;
        } catch (Exception e) {
            LOG.error("The manager cannot sign the process due to " + e.getMessage(), e);
        }
        return false;
    }

    private LoanApprovalRequest createLoanRequest(final LoanForm form, Customer customer) {
        final LoanApprovalRequest request = new LoanApprovalRequest();
        if (customer == null) {
            customerRepository.findById(form.getBorrowerCustomerId()).ifPresent(c -> {
                fillCustomerDataFromManagedEntity(request, c);
            });
        } else
            fillCustomerDataFromManagedEntity(request, customer);
        fillCoBorrowerData(request, form);
        fillLoanData(request, form);
        return request;
    }

    private void fillCoBorrowerData(final LoanApprovalRequest request, final LoanForm form) {
        request.setCoBorrower(form.isHasCoborrower());

        if (form.isHasCoborrower()) {
            request.setCoBorrowerCustomerId(form.getCoborrowerCustomerId());
            request.setCoBorrowerTitle(form.getCoborrowerTitle());
            request.setCoBorrowerFirstName(form.getCoborrowerFirstName());
            request.setCoBorrowerLastName(form.getCoborrowerLastName());
            request.setCoBorrowerDateOfBirth(form.getCoborrowerDateOfBirth());
            request.setCoBorrowerOccupation(form.getCoborrowerOccupation());
            request.setCoBorrowerLengthOfService(form.getCoborrowerLengthOfService());
            request.setCoBorrowerIncome(form.getCoborrowerIncome());
            request.setCoBorrowerEmail(form.getCoborrowerEmail());
        }
    }

    private void fillLoanData(final LoanApprovalRequest request, final LoanForm form) {
        request.setLoanAmount(form.getLoanAmount());
        request.setLoanReason(form.getLoanReason());
        request.setLoanTerm(form.getLoanTerm());
        request.setInterestRate(form.getInterestRate());
        request.setResidenceType(form.getResidenceType().name());
        request.setEstateType(form.getEstateType().name());
        request.setEstateLocation(form.getEstateAddress());
        request.setTotalPurchasePrice(form.getTotalPurchasePrice());
        request.setPersonalCapitalContribution(form.getPersonalCapitalContribution());
        request.setSettlementDate(form.getStartDate());
        request.setAccessSensitiveData(form.isAccessSensitiveData());
        request.setAgencyCode(form.getAgency());
    }

    private void fillCustomerDataFromManagedEntity(final LoanApprovalRequest request, Customer customer) {
        LOG.debug("Filling customer data from a managed entity {}", customer);
        request.setBorrowerCustomerId(customer.getId());
        request.setBorrowerTitle(customer.getTitle());
        request.setBorrowerFirstName(customer.getFirstName());
        request.setBorrowerLastName(customer.getLastName());
        request.setBorrowerDateOfBirth(customer.getDateOfBirth());
        request.setBorrowerPhone(customer.getPhone());
        request.setBorrowerMobilePhone(customer.getMobilePhone());
        request.setBorrowerEmail(customer.getEmail());
        request.setBorrowerOccupation(customer.getOccupation());
        request.setBorrowerLengthOfService(customer.getLengthOfService());
        request.setBorrowerIncome(customer.getIncome());
        if (customer.getMaritalStatus() != null)
            request.setBorrowerMaritalStatus(customer.getMaritalStatus().name());
        request.setBorrowerNumberOfChildren(customer.getNumberOfChildren());

        final Address address = customer.getAddress();

        if (address != null) {
            final com.westbank.ws.process.loanapproval._2019._01.Address borrowerAddress = new com.westbank.ws.process.loanapproval._2019._01.Address();
            borrowerAddress.setStreet(address.getStreet());
            borrowerAddress.setZipcode(address.getZipcode());
            borrowerAddress.setCity(address.getCity());
            borrowerAddress.setState(address.getState());
            borrowerAddress.setCountry(address.getCountry().getCode());
            request.setBorrowerAddress(borrowerAddress);
        }

    }

    private CustomerDecision createCustomerSignature(Long customerId, String customerName, String loanFileId, Long contractId) {
        final CustomerDecision signature = new CustomerDecision();
        signature.setCustomerId(customerId);
        signature.setContractId(contractId);
        signature.setLoanFileId(loanFileId);
        signature.setToken(createToken(customerName));
        return signature;
    }

    private ManagerSignature createManagerSignature(String staffId, String staffName, String loanFileId, Long contractId) {
        final ManagerSignature signature = new ManagerSignature();
        signature.setStaffId(staffId);
        signature.setContractId(contractId);
        signature.setLoanFileId(loanFileId);
        signature.setToken(createToken(staffName));
        return signature;
    }

    private TokenType createToken(String principal) {
        final TokenType token = new TokenType();
        token.setSerialNumber(UUID.randomUUID().toString());
        token.setX509SubjectName(principal);
        final byte[] signatureTokenX509Certificate = UUID.randomUUID().toString().getBytes();
        token.setX509Certificate(signatureTokenX509Certificate);
        token.setX509IssuerSerial(UUID.randomUUID().toString());
        token.setValidityFrom(LocalDate.now());
        token.setValidityUntil(LocalDate.now());
        return token;
    }

}
