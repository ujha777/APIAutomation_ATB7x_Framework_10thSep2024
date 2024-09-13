package com.thetestingacademy.endpoints;

import com.codoid.products.exception.FilloException;
import com.thetestingacademy.utils.FilloUtils;
import com.thetestingacademy.utils.PropertyReaderUtil;

public class APIConstants {
    public static String BASE_URL;
    public static String USERNAME;
    public static String PASSWORD;
    public static String CREATE_BOOKING;
    public static String UPDATE_BOOKING;

    static {
        try {
            BASE_URL = FilloUtils.fetchDataFromXLSX("Sheet1","BaseUrl","Value");
            USERNAME= FilloUtils.fetchDataFromXLSX("Sheet1","UserName","Value");
            PASSWORD=FilloUtils.fetchDataFromXLSX("Sheet1","Password","Value");
        } catch (FilloException e) {
            e.printStackTrace();
        }
    }
    static{
       try {
          // BASE_URL=PropertyReaderUtil.readyKey("url");
          // System.out.println(BASE_URL);
         //  USERNAME=PropertyReaderUtil.readyKey("username");
         //  PASSWORD=PropertyReaderUtil.readyKey("password");
           CREATE_BOOKING=PropertyReaderUtil.readyKey("BASE_PATH_BOOKING");
           UPDATE_BOOKING=PropertyReaderUtil.readyKey("BASE_PATH_BOOKING");
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }

}



