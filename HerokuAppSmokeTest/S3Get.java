package herokuappsmoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingsPojo;
import utils.JsonUtils;

import static herokuappsmoketest.S1Post.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S3Get extends HerOkuAppBaseUrl {
    /*
      Given
          https://restful-booker.herokuapp.com/booking/:id
      When
          User sends Get request
      Then
          Status code is 200
      And
          Response body is like {
                              "firstname" : "James",
                              "lastname" : "Brown",
                              "totalprice" : 500,
                              "depositpaid" : false,
                              "bookingdates" : {
                                  "checkin" : "2022-11-27",
                                  "checkout" : "2022-11-29"
                              },
                              "additionalneeds" : "Breakfast"
                              }
       */
    @Test
    public void get01() {
        //Set the URL
        spec.pathParams("first","booking","second",bookingid);
        //Set the expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingsPojo expectedData = new BookingsPojo("James","Brown",111,true,bookingDatesPojo,"Breakfast");
        //Send the request get the response
        Response res = given().spec(spec).when().get("/{first}/{second}");
        res.prettyPrint();
        //Do assertion
        BookingsPojo actualData = JsonUtils.convertJsonToJavaObject(res.asString(),BookingsPojo.class);
        assertEquals(200,res.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());

    }
}