package com.thetestingacademy.DDT;

import com.thetestingacademy.utils.UtilsExcel;
import org.testng.annotations.Test;

public class LoginWithTestData {

    @Test(dataProvider = "getData", dataProviderClass = UtilsExcel.class)
    public void testLoginData(String username,String password){
        System.out.println("UserName - "+ username);
        System.out.println("Password - "+ password);
        // Rest Assured code
    }
}
