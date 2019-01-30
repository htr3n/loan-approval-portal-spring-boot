package com.westbank.helper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class JaxbUtil {

    public static String toXml(Object object) {
        final StringWriter writer = new StringWriter();
        if (object != null) {
            try {
                final JAXBContext ctx = JAXBContext.newInstance(object.getClass());
                final Marshaller marshaller = ctx.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(object, writer);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return writer.toString().trim();
    }

}
