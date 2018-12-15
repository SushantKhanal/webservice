package com.assignment.webservice.xmladapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Suraj Gautam.
 */
public class TypeAdapter extends XmlAdapter<String, BigDecimal> {
    @Override
    public BigDecimal unmarshal(String v) {
        try {
            if (Objects.nonNull(v)) {
                return new BigDecimal(v);
            }
        } catch (NumberFormatException ex) {
            //IF CANNOT PARSE TO BIG DECIMAL SET IT ZERO
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String marshal(BigDecimal v) {
        return null;
    }
}
