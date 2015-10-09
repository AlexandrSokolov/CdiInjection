package com.savdev.cdiinjection.boundary.rest;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.google.common.collect.Sets;

/**
 */
@ApplicationPath("/")
public class JAXRSConfiguration extends Application
{
    public static final String JAX_RS_ENDPOINT = "/cdiendpoint";

    @Override
    @SuppressWarnings("unchecked")
    public Set<Class<?>> getClasses()
    {
        return Sets.newHashSet(InsideJaxRsInjector.class);
    }
}
