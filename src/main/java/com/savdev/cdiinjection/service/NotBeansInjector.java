package com.savdev.cdiinjection.service;

import javax.inject.Inject;

import com.savdev.cdiinjection.qualifier.MaxNumber;

/**
 */
public class NotBeansInjector
{
    @Inject
    @MaxNumber
    int maxNumber;
}
