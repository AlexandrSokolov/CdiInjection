package com.savdev.cdiinjection.service;

import javax.inject.Inject;

import com.savdev.cdiinjection.bean.cdi.Driver;

/**
 */
public class DriverInjector
{
    @Inject
    Driver driver;

    public String doDrive()
    {
        return driver.doDriver();
    }
}
