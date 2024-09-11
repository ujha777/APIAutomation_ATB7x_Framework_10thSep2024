package com.thetestingacademy.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.payloads.response.BookingRespons;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TC_CreateBooking extends BaseTest {

    @Test(groups = "P0")
    //Allure report Annonataion
    @Owner("Gangesh")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that create booking works and ID is generated!!!!")
    public  void testCreateBooking() throws JsonProcessingException {
        // Call the Request Block
        // Call the Payload
        // Call the Assertion Block

        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response=requestSpecification.when().body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

       // jsonPath= JsonPath.from(response.asString());
        // String bookingID = jsonPath.getString("bookingid");
       // assertThat(bookingID).isNotNull().isNotEmpty();
        BookingRespons bookingresponse = payloadManager.JsonToObject(response.asString());
        assertThat(bookingresponse.getBookingid().toString()).isNotNull().isNotEmpty();



    }
}
