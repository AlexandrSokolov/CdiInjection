package com.savdev.cdiinjection.producer;

import javax.enterprise.inject.Produces;

import com.savdev.cdiinjection.qualifier.MaxNumberQualifier;
import com.savdev.cdiinjection.qualifier.RandomQualifier;

/**
 */
public class ProducerService
{
    private int maxNumber = 100;

    private java.util.Random random = new java.util.Random(System.currentTimeMillis());

    @Produces
    @MaxNumberQualifier
    int getMaxNumber()
    {
        return maxNumber;
    }

    @Produces
    @RandomQualifier
    int next()
    {
        return random.nextInt(maxNumber);
    }
}
