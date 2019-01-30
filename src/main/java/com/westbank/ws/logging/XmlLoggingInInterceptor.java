package com.westbank.ws.logging;

import com.westbank.helper.XmlUtil;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class XmlLoggingInInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final Logger LOG = LoggerFactory.getLogger(XmlLoggingInInterceptor.class);

    public XmlLoggingInInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        try {
            InputStream is = message.getContent(InputStream.class);
            CachedOutputStream os = new CachedOutputStream();
            IOUtils.copy(is, os);
            os.flush();
            message.setContent(InputStream.class, os.getInputStream());
            is.close();
            // The output is already formatted but still too much noise
            // LOG.debug(IOUtils.toString(os.getInputStream()));
            LOG.trace(XmlUtil.format(IOUtils.toString(os.getInputStream())));
            os.close();
        } catch (Exception e) {
            LOG.error("Error: " + e.getMessage(), e);
        }
    }
}
