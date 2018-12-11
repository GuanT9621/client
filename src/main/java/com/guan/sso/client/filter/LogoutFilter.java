package com.guan.sso.client.filter;

import com.guan.sso.client.config.AbstractConfigurationFilter;
import com.guan.sso.client.data.SsoDataHolder;
import com.guan.sso.client.util.CookiesUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.guan.sso.client.util.CookiesUtils.SSO_COOKIE_NAME;

/**
 * 退出单点登录，不是必须的
 * 这个filter并不会退出当前用户的Session，
 * 注意需要自己清除当前用户的Session
 */
public class LogoutFilter extends AbstractConfigurationFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SsoDataHolder.clear();
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        CookiesUtils.clearCookie(httpServletResponse, topLevelDomain, SSO_COOKIE_NAME);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
