package com.savdev.cdiinjection.service;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.savdev.cdiinjection.qualifier.RandomQualifier;

/**
 */
public class RunTimeInjector
{
    @Inject
    @RandomQualifier
    Instance<Integer> randomInt;
}
