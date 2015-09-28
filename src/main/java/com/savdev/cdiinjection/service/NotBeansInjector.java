package com.savdev.cdiinjection.service;

import javax.inject.Inject;

import com.savdev.cdiinjection.qualifier.MaxNumberQualifier;

/**
 */
public class NotBeansInjector
{
    @Inject
    @MaxNumberQualifier
    int maxNumber;
}
