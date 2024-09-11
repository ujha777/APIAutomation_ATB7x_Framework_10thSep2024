package com.thetestingacademy.modules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thetestingacademy.payloads.request.Booking;
import com.thetestingacademy.payloads.request.Bookingdates;
import com.thetestingacademy.payloads.response.BookingRespons;
import com.thetestingacademy.utils.FakerUtils;

public class PayloadManager {
    ObjectMapper objectMapper;
    //Serialization
    public  String createPayload() throws JsonProcessingException {
            objectMapper=new ObjectMapper();
        //Json to String
        Booking booking = new Booking();
        booking.setFirstname(FakerUtils.getUsername());
        booking.setLastname("Dutta");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("BreakFast");

        Bookingdates bookingdates=new Bookingdates();
        bookingdates.setCheckin("2022-01-01");
        bookingdates.setCheckout("2022-01-10");

        booking.setBookingdates(bookingdates);

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);

        return payload;
    }
//Desrialization
    public BookingRespons JsonToObject(String jsonString) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        BookingRespons bookingRespons = objectMapper.readValue(jsonString, BookingRespons.class);
        return bookingRespons;
    }
}
