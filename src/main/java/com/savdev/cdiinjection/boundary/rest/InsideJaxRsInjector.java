package com.savdev.cdiinjection.boundary.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.savdev.cdiinjection.bean.cdi.PojoBeanWithAccessToUi;

/**
 */
@Path(JAXRSConfiguration.JAX_RS_ENDPOINT)
public class InsideJaxRsInjector {

    @Inject
    PojoBeanWithAccessToUi pojoBeanWithAccessToUi;

    @GET
    public String get()
    {
        return pojoBeanWithAccessToUi.greetFromUi();
    }
}
