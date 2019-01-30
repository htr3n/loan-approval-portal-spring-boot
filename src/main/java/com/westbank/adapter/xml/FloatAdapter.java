package com.westbank.adapter.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FloatAdapter extends XmlAdapter<String, Float> {

    public Float unmarshal(String v) {
        if (v != null && !v.isEmpty())
            return javax.xml.bind.DatatypeConverter.parseFloat(v);
        return null;
    }

    public String marshal(Float v) {
        if (v != null)
            return javax.xml.bind.DatatypeConverter.printFloat(v);
        return null;
    }

}
