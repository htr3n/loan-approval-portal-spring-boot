package com.westbank.ws.logging;

import com.westbank.helper.XmlUtil;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CacheAndWriteOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.io.CachedOutputStreamCallback;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;

/**
 * https://stackoverflow.com/a/10501704/339302
 */
public class XmlLoggingOutInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final Logger LOG = LoggerFactory.getLogger(XmlLoggingOutInterceptor.class);

    public XmlLoggingOutInterceptor() {
        super(Phase.PRE_STREAM);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        try {
            OutputStream os = message.getContent(OutputStream.class);
            CacheAndWriteOutputStream cwos = new CacheAndWriteOutputStream(os);
            message.setContent(OutputStream.class, cwos);
            cwos.registerCallback(new LoggingOutCallBack());
        } catch (Exception e) {
            LOG.error("Error: " + e.getMessage(), e);
        }
    }

    class LoggingOutCallBack implements CachedOutputStreamCallback {

        @Override
        public void onClose(CachedOutputStream cos) {
            try {
                if (cos != null) {
                    LOG.trace(XmlUtil.format(IOUtils.toString(cos.getInputStream())));
                }
            } catch (Exception e) {
                LOG.error("Error: " + e.getMessage(), e);
            }
        }

        @Override
        public void onFlush(CachedOutputStream os) {
        }
    }
}
