package com.savdev.cdiinjection.bean.cdi;

import javax.enterprise.inject.Specializes;

/**
 */
@Specializes
public class SpecializesDriver extends DefaultDriver
{
    public static final String DO_DRIVER = "SpecializesDriver";
    @Override
    public String doDriver()
    {
        return DO_DRIVER;
    }
}
