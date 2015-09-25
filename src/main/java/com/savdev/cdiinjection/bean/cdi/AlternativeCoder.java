package com.savdev.cdiinjection.bean.cdi;

import javax.enterprise.inject.Alternative;

/**
 */
@Alternative
public class AlternativeCoder implements Coder
{
    public static final String ALTERNATIVE_CODER = "AlternativeCoder";

    @Override
    public String doCode()
    {
        return ALTERNATIVE_CODER;
    }
}
