package com.guan.sso.client.util;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class LoadProperties {

    private Properties properties = new Properties();

    public LoadProperties(String filePath) throws ServletException{
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            properties.load(bufferedReader);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("[ERROR] illegal file url : " + e);
        } catch (IOException e) {
            throw new ServletException("[ERROR] IOException : " + e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

}
