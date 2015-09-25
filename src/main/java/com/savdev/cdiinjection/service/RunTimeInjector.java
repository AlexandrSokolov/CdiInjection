package com.savdev.cdiinjection.service;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.savdev.cdiinjection.qualifier.Random;

/**
 */
public class RunTimeInjector
{
    @Inject
    @Random
    Instance<Integer> randomInt;
}
