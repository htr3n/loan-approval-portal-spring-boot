package com.westbank.ws.logging;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MessageLoggingInterceptor extends AbstractPhaseInterceptor<Message> {

    public MessageLoggingInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        //Get the message body into payload[] and set a new non-consumed  inputStream into Message
        InputStream in = message.getContent(InputStream.class);
        byte payload[] = new byte[0];
        try {
            payload = IOUtils.readBytesFromStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //log payload...
        ByteArrayInputStream bin = new ByteArrayInputStream(payload);
        message.setContent(InputStream.class, bin);
    }
}
