package com.savdev.cdiinjection.boundary.web;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 */
public final class ThreadLocalContextHolder {
    public static final String UI_DATA_KEY = "ui_data";

    private static final ThreadLocal<Map<String, Object>> THREAD_WITH_CONTEXT = new ThreadLocal<>();

    static {
        THREAD_WITH_CONTEXT.set(Maps.newHashMap());
    }

    private ThreadLocalContextHolder() {
        throw new AssertionError("Class cannot be constructed");
    }

    public static void put(final String key, final Object value) {
        THREAD_WITH_CONTEXT.get().put(key, value);
    }

    public static Object get(final String key) {
        return THREAD_WITH_CONTEXT.get().get(key);
    }

    public static void clear() {
        THREAD_WITH_CONTEXT.get().clear();
    }
}
