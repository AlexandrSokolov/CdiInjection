package com.savdev.cdiinjection.bean.cdi;

/**
 * Without qualifier annotation applied, the javax.enterprise.inject.Default is used by default
 */
public class PojoBean
{
    public static final String GREETING = "Hello, PojoBean";

    public String greet()
    {
        return GREETING;
    }
}
