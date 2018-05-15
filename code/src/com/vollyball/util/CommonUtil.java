/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author nishant.vibhute
 */
public class CommonUtil {

    public static String propertyUrl = System.getProperty("user.dir");
    static InputStream input = null;
    static Properties prop = new Properties();

    public CommonUtil() {

        try {

//            input = new FileInputStream(path + "WEB-INF\\classes\\com\\pritient\\properties\\resource.properties");
            input = new FileInputStream(propertyUrl + "\\src\\com\\vollyball\\properties\\resource.properties");
            prop.load(input);

        } catch (Exception ex) {

        }
    }

    public static String getResourceProperty(String name) {
        String value = prop.getProperty(name);
        return value;

    }

}
