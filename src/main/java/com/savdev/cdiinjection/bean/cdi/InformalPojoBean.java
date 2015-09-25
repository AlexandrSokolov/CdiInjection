package com.savdev.cdiinjection.bean.cdi;

import com.savdev.cdiinjection.qualifier.Informal;

/**
 */
@Informal
public class InformalPojoBean extends PojoBean
{
    public static final String INFORMAL_GREETING = "Hello, InformalPojoBean, but not PojoBean";

    @Override
    public String greet()
    {
        return INFORMAL_GREETING;
    }
}
