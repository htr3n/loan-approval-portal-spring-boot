package com.westbank.adapter.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DoubleAdapter extends XmlAdapter<String, Double> {

    public Double unmarshal(String v) {
        if (v != null && !v.isEmpty())
            return javax.xml.bind.DatatypeConverter.parseDouble(v);
        return null;
    }

    public String marshal(Double v) {
        if (v != null)
            return javax.xml.bind.DatatypeConverter.printDouble(v);
        return null;
    }

}
