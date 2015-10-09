package com.savdev.cdiinjection.boundary.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 */
@WebFilter(filterName="WebFilterPassDataFromUiLayerToBusinessComponent", urlPatterns="*")
public class WebFilterPassDataFromUiLayerToBusinessComponent implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final String header = httpServletRequest.getHeader(ThreadLocalContextHolder.UI_DATA_KEY);
        ThreadLocalContextHolder.put(ThreadLocalContextHolder.UI_DATA_KEY, header);
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
