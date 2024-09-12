package com.thetestingacademy.misc.CREATE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thetestingacademy.actions.AssertActions;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.PayloadManager;
import com.thetestingacademy.payloads.response.BookingRespons;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

    @Test(groups = {"stage","P0"})
    @Description("TC#2 -  Verify that Create Booking with No payload")
    public void testCreateBooking2_Negative() throws JsonProcessingException {
        payloadManager = new PayloadManager();
        actions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON).log().all();
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response = requestSpecification.when().body("").post();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);
        //new YAMLReader().readKey().get("username");
        //new ProeprReader().readKey().get("username");
        //new ExcelReader().readKey().get("username");
        //new JSONReader().readKey().get("username");
        //new TETXReader().readKey().get("username");
        //new ENVReader().readKey().get("username");
        //new XMLReader.readKey().get("username");
    }
}
