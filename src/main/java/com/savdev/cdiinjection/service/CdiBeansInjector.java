package com.savdev.cdiinjection.service;

import javax.inject.Inject;

import com.savdev.cdiinjection.bean.cdi.InformalPojoBean;
import com.savdev.cdiinjection.bean.cdi.PojoBean;
import com.savdev.cdiinjection.qualifier.InformalQualifier;

/**
 */
public class CdiBeansInjector
{
    @Inject
    PojoBean pojoBean;

    @Inject
    @InformalQualifier
    InformalPojoBean informalPojoBean;

    public String helloPojoBean()
    {
        return pojoBean.greet();
    }

    public String helloInformalPojoBean()
    {
        return informalPojoBean.greet();
    }
}
