package com.dictionary.common.constant;

public final class RouteMapping {
    public static final String VERSION = "v1";
    public static final String ROOT = "/" + VERSION;
    public static final String PUBLIC_API = ROOT + "/public";

    //AUTH

    public static final String AUTH_API_ROOT = PUBLIC_API + "/auth";
    public static final String LOGIN_API = "/login";

}
