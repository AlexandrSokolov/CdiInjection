package com.savdev.cdiinjection.bean.cdi;

/**
 */
public class DefaultDriver implements Driver
{
    public static final String DO_DRIVER = "DefaultDriver";

    @Override
    public String doDriver()
    {
        return DO_DRIVER;
    }
}
