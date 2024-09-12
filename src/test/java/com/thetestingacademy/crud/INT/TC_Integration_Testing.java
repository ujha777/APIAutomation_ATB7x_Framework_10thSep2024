package com.thetestingacademy.crud.INT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.payloads.request.Booking;
import com.thetestingacademy.payloads.response.BookingRespons;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TC_Integration_Testing extends BaseTest {
    // Create a Booking
    // Update the Booking with Token and Booking ID - How to pass the variales from one test to another.
    // 1. Auth - API Key
    // Cookie Based Auth Side.
    // OAuth 2.0 - Method how you can use the OAuth 2.0
    // Delete Also
    String token;
    String bookingId;
    String bookingIdPojo;
    private static final Logger log = LogManager.getLogger(TC_Integration_Testing.class);
    //Get token-Extract token and create booking

    @Test(groups = "P0")
    @Owner("Gangesh")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC001:Verify that create booking works and ID is generated!!!!")
    public void testCreateBooking() throws JsonProcessingException {

        token=getToken();

        assertThat(token).isNotNull().isNotEmpty();
        //if token is null or fail then below code will not come to picture
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response=requestSpecification.when().body(payloadManager.createPayload()).post();
        validatableResponse = response.then().log().all();
        jsonPath=JsonPath.from(response.asString());
        validatableResponse.statusCode(200);
        // Direct Extraction from json Path
        bookingId = jsonPath.getString("bookingid");
        // Booking Response Class
        BookingRespons bookingRespons = payloadManager.JsonToObject(response.asString());
        bookingIdPojo = bookingRespons.getBookingid().toString();
        log.info("This is My Booking ID"+bookingIdPojo);
        assertThat(bookingId).isNotNull().isNotEmpty();
    }

    //update booking
    @Test(groups = "P0", dependsOnMethods = {"testCreateBooking"})
    @Owner("Gangesh")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC002:Verify that  booking updated successfully")
    public void testCreateAndUpdateBooking() throws JsonProcessingException {
       // requestSpecification.contentType(ContentType.JSON);
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING+"/"+ bookingId);
        response= RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().body(payloadManager.updatedPayload()).put();
        validatableResponse = response.then().log().all();

       // validatableResponse.body("firstname", Matchers.is("Lucky"));

        Booking bookingRespons = payloadManager.JsonToObjectPUT(response.asString());
        assertThat(bookingRespons.getFirstname().toString()).isEqualTo("Lucky").isNotNull();
        assertThat(bookingRespons.getLastname()).isNotNull();
        assertThat(bookingRespons.getDepositpaid()).isNotNull();
        assertThat(bookingRespons.getBookingdates().getCheckin()).isNotNull();
        assertThat(bookingRespons.getBookingdates().getCheckout()).isNotEmpty();

    }
    //Patch Booking
    @Test(groups = "P0", dependsOnMethods = {"testCreateBooking"})
    @Owner("Gangesh")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC003:Verify that  lastname updated successfully")
    public void testCreatedPatchBooking() throws JsonProcessingException {
      //  requestSpecification.contentType(ContentType.JSON);
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING+"/"+ bookingId);
        response= RestAssured.given().spec(requestSpecification).cookie("token",token)
                .when().body(payloadManager.patchUpload()).patch();
        validatableResponse = response.then().log().all();

        // validatableResponse.body("firstname", Matchers.is("Lucky"));

        Booking bookingRespons = payloadManager.JsonToObjectPUT(response.asString());
        assertThat(bookingRespons.getFirstname()).isNotNull();
        assertThat(bookingRespons.getLastname().toString()).isEqualTo("Dunki").isNotNull();
        assertThat(bookingRespons.getDepositpaid()).isNotNull();
        assertThat(bookingRespons.getBookingdates().getCheckin()).isNotNull();
        assertThat(bookingRespons.getBookingdates().getCheckout()).isNotEmpty();

    }

    //delete booking
    @Test(groups = "P0",dependsOnMethods = { "testCreateAndUpdateBooking"})
    @Owner("Gangesh")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC004:Verify that  booking deleted  successfully")
    public void testDeleteCreatedBooking(){
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING+"/"+ bookingId).cookie("token",token);
        validatableResponse=RestAssured.given().spec(requestSpecification).auth().basic(APIConstants.USERNAME, APIConstants.PASSWORD)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);

    }
    @Test(groups = "P0",dependsOnMethods = { "testDeleteCreatedBooking"})
    @Owner("Gangesh")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC004:Verify that  booking details NOT FOUND after  deleting specific booking id")
    public void testDeleteBookingByGet(){
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING+"/"+ bookingId);
        response=RestAssured.given().spec(requestSpecification)
                        .when().get();
       validatableResponse= response.then().log().all();
        validatableResponse.statusCode(404);
    }
}
