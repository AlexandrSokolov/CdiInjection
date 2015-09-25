package com.savdev.cdiinjection.service;

import javax.inject.Inject;

import com.savdev.cdiinjection.bean.cdi.Coder;

/**
 */
public class DefaultCoderInjector
{
    @Inject
    Coder coder;

    public String whatToDo()
    {
        return coder.doCode();
    }
}
