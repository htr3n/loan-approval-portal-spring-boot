package com.westbank.ws.impl;

import com.westbank.AppProperties;
import com.westbank.entity.Role;
import com.westbank.helper.JaxbUtil;
import com.westbank.ws.business.taskdispatch._2019._01.TaskDispatch;
import com.westbank.ws.business.taskdispatch._2019._01.TaskDispatchRequest;
import com.westbank.ws.business.taskdispatch._2019._01.TaskDispatchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(
        serviceName = "TaskDispatch",
        portName = "TaskDispatchPort",
        targetNamespace = "urn:com:westbank:ws:business:TaskDispatch:2019:01",
        endpointInterface = "com.westbank.ws.business.taskdispatch._2019._01.TaskDispatch")
public class TaskDispatchPortImpl implements TaskDispatch {

    private static final Logger LOG = LoggerFactory.getLogger(TaskDispatchPortImpl.class);

    @Autowired
    private AppProperties appProperties;

    public TaskDispatchResponse dispatch(TaskDispatchRequest request) {
        LOG.debug("dispatch() --> {}", request);
        LOG.trace(JaxbUtil.toXml(request));
        Assert.notNull(request, "The request must not be null");
        try {
            final TaskDispatchResponse response = new TaskDispatchResponse();
            final double loanAmount = request.getLoanAmount();
            if (loanAmount < appProperties.getLoan().getThreshold())
                response.setRole(Role.ID_POST_PROCESSING_CLERK);
            else
                response.setRole(Role.ID_SUPERVISOR);
            LOG.debug(" Response {}", response);
            LOG.trace(JaxbUtil.toXml(response));
            return response;
        } catch (final Exception ex) {
            LOG.error("Error handling task dispatching: " + ex.getMessage(), ex);
            throw ex;
        }
    }

}
