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

    //DICTIONARY
    public static final String DICTIONARY_API_ROOT = PRIVATE_API + "/dictionary";
    public static final String GET_DICTIONARY = "/";

    //VALIDATE
    public static final String VALIDATE_API_ROOT = PRIVATE_API + "/validate";
    public static final String VALIDATE = "/";

    //STAT

    public static final String STATS_API_ROOT = PRIVATE_API + "/stats";
    public static final String GET_STATS = "/";

}

