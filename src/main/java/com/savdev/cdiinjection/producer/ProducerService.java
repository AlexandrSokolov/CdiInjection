package com.savdev.cdiinjection.producer;

import javax.enterprise.inject.Produces;

import com.savdev.cdiinjection.qualifier.MaxNumber;
import com.savdev.cdiinjection.qualifier.Random;

/**
 */
public class ProducerService
{
    private int maxNumber = 100;

    private java.util.Random random = new java.util.Random(System.currentTimeMillis());

    @Produces
    @MaxNumber
    int getMaxNumber()
    {
        return maxNumber;
    }

    @Produces
    @Random
    int next()
    {
        return random.nextInt(maxNumber);
    }
}
