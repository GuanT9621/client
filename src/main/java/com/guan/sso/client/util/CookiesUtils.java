package com.guan.sso.client.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesUtils {

    public static final String SSO_COOKIE_NAME = "SID";

    public static String readCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void writeCookie(HttpServletResponse response, String domain, String cookieName, String value){
        Cookie cookie = new Cookie(cookieName,value);
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    public static void clearCookie(HttpServletResponse response, String domain, String cookieName) {
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
