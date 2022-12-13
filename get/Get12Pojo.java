package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingsPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;



public class Get12Pojo extends HerOkuAppBaseUrl {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/12
       When
            I send GET Request to the URL
        Then
            Status code is 200
        And
            Response body is like {
                                   "firstname": "Fabio",
                                   "lastname": "Dominguez",
                                   "totalprice": 111,
                                   "depositpaid": true,
                                   "bookingdates": {
                                       "checkin": "2018-01-01",
                                       "checkout": "2019-01-01"
                                   },
                                   "additionalneeds": "Breakfast"
    */
    @Test
    public void get12(){
        //Set the URL
        spec.pathParams("first","booking","second",12);


        //Set the expected data
BookingDatesPojo obj = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingsPojo expectedData = new BookingsPojo("Fabio","Dominguez",111,true, obj,"Breakfast");
        //Set the request get the response
        Response response = given().spec(spec).when().get("/{first}/{second}}");
        //Do the assertion
        BookingsPojo actualData =response.as(BookingsPojo.class);
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
        assertEquals(obj.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(obj.getCheckout(),actualData.getBookingdates().getCheckout());

         /*
    How do we handle different key-values in response?
    We use @JsonIgnoreProperties(ignoreUnknown = true) annotation at the top of the pojo class.
    It comes from "org.codehaus.jackson.annotate.JsonIgnoreProperties"
     */

        //1st Way:
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());

        //2nd Way:Recommended
        assertEquals(obj.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(obj.getCheckout(),actualData.getBookingdates().getCheckout());

    }
}
