package com.savdev.cdiinjection.bean.cdi;

import com.savdev.cdiinjection.boundary.web.ThreadLocalContextHolder;

/**
 */
public class PojoBeanWithAccessToUi {


    public static final String GREETING = "Hello, PojoBeanWithAccessToUi, data from UI layer = ";

    public String greetFromUi()
    {
        return GREETING + ThreadLocalContextHolder.get(ThreadLocalContextHolder.UI_DATA_KEY);
    }
}
