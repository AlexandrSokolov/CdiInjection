package com.savdev.cdiinjection.bean.cdi;

/**
 */
public class CoderImpl implements Coder
{
    public static final String CODER_IMPL = "CoderImpl";
    @Override
    public String doCode()
    {
        return CODER_IMPL;
    }
}
