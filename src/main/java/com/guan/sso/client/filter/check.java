package com.guan.sso.client.filter;

import com.guan.sso.client.config.AbstractConfigurationFilter;
import com.guan.sso.client.util.CommonUtils;
import com.guan.sso.client.util.CookiesUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Check if the user is logged in, Set the return result in session
 */
public final class check extends AbstractConfigurationFilter {

    private static final String AUTH_TOKEN = "gat";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = CookiesUtils.readCookie(httpServletRequest, topLevelDomain, AUTH_TOKEN);
        boolean status;
        if(CommonUtils.isBlank(token)) {
            status = false;
        } else {
            //TODO get check result from SSO server by http or https
            status = true;
        }


    }


}
