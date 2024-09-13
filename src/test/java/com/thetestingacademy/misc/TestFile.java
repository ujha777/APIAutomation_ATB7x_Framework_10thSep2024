package com.thetestingacademy.misc;

import com.codoid.products.exception.FilloException;
import com.thetestingacademy.utils.FilloUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TestFile {
    @Test
    public void postRequest() {

        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .when().body(payload).post()

                .then().log().all().statusCode(200);


    }
   @Test
    public void testFillow() throws FilloException {

        String BASE_URL = FilloUtils.fetchDataFromXLSX("Sheet1","BaseUrl","Value");
        System.out.println(BASE_URL);


    }

}
