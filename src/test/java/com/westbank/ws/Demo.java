package com.westbank.ws;

import com.westbank.entity.EstateType;
import com.westbank.entity.MaritalStatus;
import com.westbank.entity.ResidenceType;
import com.westbank.helper.JaxbUtil;
import com.westbank.ws.process.loanapproval._2019._01.Address;
import com.westbank.ws.process.loanapproval._2019._01.LoanApproval;
import com.westbank.ws.process.loanapproval._2019._01.LoanApprovalRequest;
import com.westbank.ws.process.loanapproval._2019._01.LoanApproval_Service;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class Demo {

    private static final Logger LOG = LoggerFactory.getLogger(Demo.class);
    private static final String PROCESS_ENDPOINT = "http://localhost:8080/ode/processes/LoanApproval?wsdl";
    private final static QName SERVICE_NAME = new QName("urn:com:westbank:ws:process:LoanApproval:2019:01", "LoanApproval");
    private URL wsdlURL;
    private LoanApproval processClient;
    private LoanApproval_Service loanApprovalService;

    @Before
    public void setUp() {
        try {
            wsdlURL = new URL(PROCESS_ENDPOINT);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        loanApprovalService = new LoanApproval_Service(wsdlURL, SERVICE_NAME);
        processClient = loanApprovalService.getLoanApprovalPort();
    }

    @Test
    public void testStart() {
        final LoanApprovalRequest request = new LoanApprovalRequest();
        request.setBorrowerCustomerId(Long.valueOf(1));
        request.setBorrowerTitle("Mrs.");
        request.setBorrowerFirstName("Alice");
        request.setBorrowerLastName("Power");
        request.setBorrowerPersonalId("1163829604");
        request.setBorrowerDateOfBirth(LocalDate.now());

        Address address = new Address();
        address.setStreet("123 King St");
        address.setZipcode("1234");
        address.setCity("City");
        address.setState("State");
        address.setCountry("AU");
        request.setBorrowerAddress(address);

        request.setBorrowerPhone("577194357");
        request.setBorrowerMobilePhone("101680573");
        request.setBorrowerEmail("alice@test.com");
        request.setBorrowerOccupation("Clerk");
        request.setBorrowerLengthOfService(Integer.valueOf(10));
        request.setBorrowerIncome(Double.valueOf(90000));
        request.setBorrowerMaritalStatus(MaritalStatus.MARRIED.name());
        request.setBorrowerNumberOfChildren(Integer.valueOf(2));
        request.setCoBorrower(false);
        request.setLoanFileId("6faff937");
        request.setLoanReason("LoanReason306462649");
        request.setLoanAmount(Double.valueOf(1000000));
        request.setLoanTerm(Integer.valueOf(10));
        request.setInterestRate(Double.valueOf(0.2));
        request.setResidenceType(ResidenceType.MAIN_HOUSE.name());
        request.setEstateType(EstateType.FLAT.name());
        request.setEstateLocation("9 Queen Ave");
        request.setSettlementDate(LocalDate.now());
        request.setTotalPurchasePrice(Double.valueOf(1200000));
        request.setPersonalCapitalContribution(Double.valueOf(500000));
        request.setAccessSensitiveData(true);
        try {
            LOG.debug("startProcess() -> loan request: {}", JaxbUtil.toXml(request));
            processClient.start(request);
        } catch (Exception e) {
            LOG.error("Error when starting the process: " + e.getMessage(), e);
        }
    }

    @Test
    public void testAll() {
    }
}
