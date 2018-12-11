package com.guan.sso.client.config;

import com.guan.sso.client.grpc.client.SsoClient;
import com.guan.sso.client.grpc.stub.SsoServiceGrpc;
import com.guan.sso.client.util.CommonUtils;
import com.guan.sso.client.util.LoadProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * load properties and instantiate ssoClient ssoServiceBlockingStub
 */
public abstract class  AbstractConfigurationFilter implements Filter{

    /**
     * The Properties File URL
     */
    private static final String CONFIG_FILE_LOCATION_KEY = "configFileLocation";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected SsoClient ssoClient;

    protected SsoServiceGrpc.SsoServiceBlockingStub ssoServiceBlockingStub;

    /**
     * properties file
     */
    protected LoadProperties loadProperties;

    /**
     * The URL to the SSO Server : {hostName}:{port}
     */
    protected String ssoServerUrl;

    /**
     * The Top-Level Domain : domain.com
     */
    protected String topLevelDomain;

    /**
     * load ssoServerUrl & topLevelDomain value
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        final String configFileLocationUrl = filterConfig.getServletContext().getInitParameter(CONFIG_FILE_LOCATION_KEY);
        if(CommonUtils.isBlank(configFileLocationUrl)) {
            throw new IllegalArgumentException("[ERROR] unable to find file in path : " + configFileLocationUrl);
        }
        loadProperties = new LoadProperties(configFileLocationUrl);
        topLevelDomain = loadProperties.get("topLevelDomain");
        ssoServerUrl = loadProperties.get("ssoServerUrl");
        if(CommonUtils.isBlank(topLevelDomain) || CommonUtils.isBlank(ssoServerUrl)) {
            logger.error("ERROR illegal argument : topLevelDomain or ssoServerUrl ");
            throw new IllegalArgumentException("[ERROR] illegal argument : topLevelDomain or ssoServerUrl ");
        }
        String[] strings = ssoServerUrl.split(":");
        try {
            ssoClient = new SsoClient(strings[0], Integer.valueOf(strings[1]));
            ssoServiceBlockingStub = ssoClient.getSsoServiceBlockingStub();
        } catch (Exception e) {
            logger.error("ERROR illegal ssoServerUrl : must be match {hostName}:{port} like 127.0.0.2:3652 ");
            throw new IllegalArgumentException("ERROR illegal ssoServerUrl : must be match {hostName}:{port} like 127.0.0.2:3652 ");
        }
    }

    @Override
    public void destroy() {
        //nothing to do
    }
}
