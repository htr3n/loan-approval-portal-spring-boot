package com.westbank.helper;

public class XmlUtil {

    public static String format(String xmlStr) {
        try {
            // https://stackoverflow.com/a/962225/339302
            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            nu.xom.Serializer serializer = new nu.xom.Serializer(out);
            serializer.setIndent(2);
            serializer.setPreserveBaseURI(false);
            serializer.write(new nu.xom.Builder().build(xmlStr, ""));
            return out.toString("UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
