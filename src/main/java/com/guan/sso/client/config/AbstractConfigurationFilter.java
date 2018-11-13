package com.guan.sso.client.config;

import com.guan.sso.client.util.CommonUtils;
import com.guan.sso.client.util.LoadProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

public abstract class  AbstractConfigurationFilter implements Filter{

    /**
     * The Properties File URL
     */
    private static final String CONFIG_FILE_LOCATION_KEY = "configFileLocation";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * The URL to the SSO Server : {protocol}:{hostName}:{port}
     */
    protected String ssoServerUrl;

    /**
     * The Top-Level Domain : google.com
     */
    protected String topLevelDomain;

    private String host;
    private String port;
    private String database;
    private String password;
    private String timeout;

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
        LoadProperties loadProperties = new LoadProperties(configFileLocationUrl);
        topLevelDomain = loadProperties.get("topLevelDomain");
        ssoServerUrl = loadProperties.get("ssoServerUrl");
        if(CommonUtils.isBlank(topLevelDomain) || CommonUtils.isBlank(ssoServerUrl)) {
            throw new IllegalArgumentException("[ERROR] illegal argument : topLevelDomain or ssoServerUrl");
        }
    }

    @Override
    public void destroy() {
        //nothing to do
    }
}
