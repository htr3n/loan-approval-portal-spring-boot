package com.westbank.ws.impl;

import com.westbank.entity.*;
import com.westbank.helper.Generator;
import com.westbank.helper.JaxbUtil;
import com.westbank.repository.CustomerRepository;
import com.westbank.repository.LoanFileRepository;
import com.westbank.repository.StaffRepository;
import com.westbank.ws.business.loanfile._2019._01.LoanFileRequest;
import com.westbank.ws.business.loanfile._2019._01.LoanFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;

@WebService(
        serviceName = "LoanFile",
        portName = "LoanFilePort",
        targetNamespace = "urn:com:westbank:ws:business:LoanFile:2019:01",
        endpointInterface = "com.westbank.ws.business.loanfile._2019._01.LoanFile")
public class LoanFilePortImpl implements com.westbank.ws.business.loanfile._2019._01.LoanFile {

    private static final Logger LOG = LoggerFactory.getLogger(LoanFilePortImpl.class);

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LoanFileRepository loanFileService;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private Generator generator;

    public LoanFileResponse update(LoanFileRequest request) {
        LOG.debug("update() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");

        final LoanFileResponse response = saveLoanRequest(request);
        LOG.debug(" Response {}", response);
        LOG.trace(JaxbUtil.toXml(response));
        return response;
    }

    private LoanFileResponse saveLoanRequest(LoanFileRequest request) {
        Assert.notNull(request, "Request must not be null");

        final LoanFile loanFile = new LoanFile();
        final LoanFileResponse response = new LoanFileResponse();
        Customer coborrower = null;

        Assert.notNull(request.getBorrowerCustomerId(), "Borrower's customer ID must not be null");
        LOG.debug("Request's borrower's customer ID  {}", request.getBorrowerCustomerId());
        Customer borrower = customerRepository.findById(request.getBorrowerCustomerId()).orElse(null);

        if (borrower != null) {
            LOG.debug("Borrower data exists");
        } else {
            LOG.debug("Creating a new borrower");
            borrower = new Customer();
            borrower.setPersonalId(request.getBorrowerPersonalId());
            borrower.setTitle(request.getBorrowerTitle());
            borrower.setFirstName(request.getBorrowerFirstName());
            borrower.setLastName(request.getBorrowerLastName());
            borrower.setPassword(generator.newCustomerPin());
            borrower.setPhone(request.getBorrowerPhone());
            borrower.setMobilePhone(request.getBorrowerMobilePhone());
            borrower.setEmail(request.getBorrowerEmail());
            borrower.setIncome(request.getBorrowerIncome());
            borrower.setOccupation(request.getBorrowerOccupation());
            borrower.setLengthOfService(request.getBorrowerLengthOfService());

            if (request.getBorrowerDateOfBirth() != null)
                borrower.setDateOfBirth(request.getBorrowerDateOfBirth());

            final Address a = new Address();
            a.setCity(request.getBorrowerCity());
            a.setCountry(new Country(request.getBorrowerCountry()));
            a.setState(request.getBorrowerState());
            a.setStreet(request.getBorrowerStreet());
            a.setZipcode(request.getBorrowerZipcode());
            borrower.setAddress(a);
            customerRepository.save(borrower);
        }
        // is there a co-borrower?
        if (request.isCoBorrower()) {
            Assert.notNull(request.getCoBorrowerCustomerId(), "Co-borrower's customer ID must not be null");
            coborrower = customerRepository.findById(request.getCoBorrowerCustomerId()).orElse(null);
            if (coborrower != null) {
                LOG.debug("Co-borrower data exists");
            } else {
                LOG.debug("Creating a new co-borrower");
                coborrower = new Customer();
                coborrower.setPersonalId(request.getCoBorrowerPersonalId());
                coborrower.setTitle(request.getCoBorrowerTitle());
                coborrower.setFirstName(request.getCoBorrowerFirstName());
                coborrower.setLastName(request.getCoBorrowerLastName());
                coborrower.setPassword(generator.newCustomerPin());
                coborrower.setIncome(request.getCoBorrowerIncome());
                coborrower.setOccupation(request.getCoBorrowerOccupation());
                coborrower.setLengthOfService(request.getCoBorrowerLengthOfService());
                coborrower.setEmail(request.getCoBorrowerEmail());
                if (request.getCoBorrowerDateOfBirth() != null)
                    coborrower.setDateOfBirth(request.getCoBorrowerDateOfBirth());
                LOG.debug("Saving the new co-borrower to DB");
                customerRepository.save(coborrower);
            }
        }
        loanFile.setLoanFileId(request.getLoanFileId());
        loanFile.setBorrower(borrower);
        loanFile.setCoBorrower(coborrower);
        loanFile.setLoanAmount(request.getLoanAmount());
        loanFile.setLoanReason(request.getLoanReason());
        loanFile.setLoanTerm(request.getLoanTerm());
        loanFile.setInterestRate(request.getInterestRate());
        try {
            loanFile.setResidenceType(ResidenceType.valueOf(request.getResidenceType()));
        } catch (IllegalArgumentException ex) {
            LOG.error("The resident type {} is not of a valid enum", request.getResidenceType());
            LOG.debug("Exception: ", ex);
        } catch (NullPointerException ignored) {
        }
        try {
            loanFile.setEstateType(EstateType.valueOf(request.getEstateType()));
        } catch (IllegalArgumentException ex) {
            LOG.error("The estate type {} is not of a valid enum", request.getEstateType());
            LOG.debug("Exception: ", ex);
        } catch (NullPointerException ignored) {
        }
        loanFile.setEstateLocation(request.getEstateLocation());

        //if (request.getSettlementDate() != null)
        //    loanFile.setSettlementDate(request.getSettlementDate());

        loanFile.setTotalPurchasePrice(request.getTotalPurchasePrice());
        loanFile.setPersonalCapitalContribution(request.getPersonalCapitalContribution());
        loanFile.setCreatedDate(LocalDate.now());

        if (request.getStaffId() != null)
            loanFile.setCreatedBy(staffRepository.findById(request.getStaffId()).orElse(null));

        loanFile.setAccessSensitiveData(request.isAccessSensitiveData());
        LOG.debug("Allows to access sensitive data? " + request.isAccessSensitiveData());
        loanFile.setStatus(LoanFileStatus.INITIALIZED);

        try {
            LOG.debug("Saving the new loan file to DB");
            loanFileService.save(loanFile);
            response.setLoanFileId(loanFile.getLoanFileId());
            response.setBorrowerId(borrower.getId());
            if (coborrower != null) {
                response.setCoborrowerId(coborrower.getId());
            }
        } catch (final Throwable e) {
            LOG.error("Cannot save data due to error: " + e.getMessage(), e);
        }
        return response;
    }

}
