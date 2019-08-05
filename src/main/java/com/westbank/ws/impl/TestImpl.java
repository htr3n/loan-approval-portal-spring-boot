package com.westbank.ws.impl;

import com.westbank.helper.JaxbUtil;
import com.westbank.repository.CustomerRepository;
import com.westbank.ws.test._2019._01.Test;
import com.westbank.ws.test._2019._01.TestRequest;
import com.westbank.ws.test._2019._01.TestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        serviceName = "Test",
        portName = "Test",
        targetNamespace = "urn:com:westbank:ws:Test:2019:01",
        endpointInterface = "com.westbank.ws.test._2019._01.Test")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class TestImpl implements Test {

    private static final Logger LOG = LoggerFactory.getLogger(TestImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    public TestResponse go(TestRequest request) {
        LOG.debug("Executing operation go: {}", JaxbUtil.toXml(request));
        try {
            final TestResponse response = new TestResponse();
            response.setStatus("1994582911");
            LOG.debug("Response {}", JaxbUtil.toXml(response));
            return response;
        } catch (Exception ex) {
            LOG.error("Error when handling go()" + ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

}
