package com.westbank.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

    public static XMLGregorianCalendar convert(Date date) {
        if (date != null) {
            final GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            return convert(gregorianCalendar);
        }
        return null;
    }

    public static XMLGregorianCalendar convert(GregorianCalendar date) {
        if (date != null) {
            try {
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
            } catch (final DatatypeConfigurationException e) {
                LOG.error("Cannot convert date '" + date + "': " + e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * Converts a java.util.Date input into ANSI SQL DATE format
     * <p>
     * YYYY-MM-DD
     *
     * @param input -- the input java.util.Date
     * @return -- ANSI SQL date format or null
     */
    public static String convertDate(Date input) {
        final java.sql.Date d = new java.sql.Date(input.getTime());
        return d.toString();
    }
}
