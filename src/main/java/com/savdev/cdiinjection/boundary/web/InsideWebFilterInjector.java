package com.savdev.cdiinjection.boundary.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * TODO
 */
@WebFilter(filterName="InsideWebFilterInjector", urlPatterns="*")
public class InsideWebFilterInjector implements Filter {

    /**************************************
     * Cannot inject resources START:
     * You'll get:
     * Caused by: java.lang.IllegalStateException: JBAS014558: No EjbContext available as no EJB invocation is active
     */
    //@javax.annotation.Resource
    //private javax.ejb.SessionContext sessionContext;
    /**
     * Cannot inject resources END
     **************************************/

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //empty
    }
}
