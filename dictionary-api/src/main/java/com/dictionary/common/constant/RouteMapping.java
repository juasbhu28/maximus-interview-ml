package com.dictionary.common.constant;

public final class RouteMapping {

    private RouteMapping() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ROOT_VERSION = "/v1";
    public static final String PUBLIC_API = ROOT_VERSION + "/public";
    public static final String PRIVATE_API = ROOT_VERSION + "/private";

    //AUTH

    public static final String AUTH_API_ROOT = PUBLIC_API + "/auth";
    public static final String LOGIN_API = "/login";

}

