package com.thetestingacademy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReaderUtil {

    public static String readyKey(String key) throws Exception {
        System.out.println(System.getProperty("user.dir"));
        FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/com/thetestingacademy/resource/TDP.properties"));
        Properties p = new Properties();
        p.load(fileInputStream);

        if(p.getProperty(key) == null){
            throw new Exception("Not able to find the key");
        }else{
            return p.getProperty(key);
        }

    }


}
