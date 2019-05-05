/*
 * Created by IntelliJ IDEA.
 * @author rabbani.mohammed@sella.it
 * Date: Jan 3, 2002
 * Time: 12:10:00 PM
 *
 */
package com.util.log4debug.impl;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class reads Key value pairs from Log4Debug.properties
 */
public class Log4DebugProperties {
public static final String AUTHOR="$Author: gbs00324 $";
public static final String REVISION="$Revision: 1.1 $";
public static final String DATE="$Date: 2008/03/19 12:12:24 $";
    private  static ResourceBundle resourceBundle;
    static {
        try {
            resourceBundle =ResourceBundle.getBundle("it.sella.util.log4debug.impl.Log4Debug");
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){

        try {
             if(resourceBundle!=null){
                return resourceBundle.getString(key).trim();
             }
            else{
                 return null;
             }
        } catch (MissingResourceException e) {
            return null;
        }
    }
    public static String getProperty(String key, String defaultValue){
        try {
            if(resourceBundle!=null) {
                return resourceBundle.getString(key).trim();
            }
            else{
                return defaultValue;
            }
        } catch (MissingResourceException e) {
            return defaultValue;
        }
    }
}
