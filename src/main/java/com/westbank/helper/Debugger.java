package com.westbank.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class Debugger {

    static final Logger LOG = LoggerFactory.getLogger(Debugger.class);

    public static void print(Collection c) {
        LOG.debug("----------------------------------------------------");
        StringBuilder sb = new StringBuilder();
        for (Object o : c) {
            sb.append(" ").append(o);
        }
        LOG.debug("{}", sb.toString());
        LOG.debug("----------------------------------------------------");
    }

}
