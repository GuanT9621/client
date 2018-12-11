package com.guan.sso.client.filter;

import com.guan.sso.client.config.AbstractConfigurationFilter;
import com.guan.sso.client.data.SsoData;
import com.guan.sso.client.data.SsoDataHolder;
import com.guan.sso.client.grpc.stub.TokenRequest;
import com.guan.sso.client.grpc.stub.UserReply;
import com.guan.sso.client.grpc.stub.VerifyReply;
import com.guan.sso.client.util.CommonUtils;
import com.guan.sso.client.util.CookiesUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static com.guan.sso.client.util.CookiesUtils.SSO_COOKIE_NAME;

/**
 * 判断用户是否登录,如果并将检查结果放在SsoData中
 * 该filter是必须的，且放置在第一位；
 * 请在该filter之后自行通过SsoDataHolder获取SsoData然后自行判断如何处理。
 */
public class LoginFilter extends AbstractConfigurationFilter {

    private boolean status;

    private long uid;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = CookiesUtils.readCookie(httpServletRequest, SSO_COOKIE_NAME);
        if(CommonUtils.isBlank(token)) {
            status = false;
        } else {
            TokenRequest tokenRequest = TokenRequest.newBuilder().setToken(token).build();
            VerifyReply verifyReply = ssoServiceBlockingStub.verifyToken(tokenRequest);
            status = verifyReply.getReal();
            if (status) {
                UserReply userReply = ssoServiceBlockingStub.getUserByToken(tokenRequest);
                uid = userReply.getUid();
            }
        }
        SsoData ssoData = new SsoData(status, String.valueOf(uid));
        SsoDataHolder.setAssertion(ssoData);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
