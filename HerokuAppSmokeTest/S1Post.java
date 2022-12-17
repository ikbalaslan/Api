package herokuappsmoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BiggerBookings;
import pojos.BookingDatesPojo;
import pojos.BookingsPojo;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S1Post extends HerOkuAppBaseUrl {
    /*Type an automation smoke test by using "https://restful-booker.herokuapp.com/apidoc/index.html" documentation.
     Create a booking then update, read and delete the booking you created.
    */
    /*
    Given
          https://restful-booker.herokuapp.com/booking
     And
{
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
  When
    User send Post request
  Then
    Status code must be 200
  And
        Response body is like
                    {
    "bookingid": 18169,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}

     */
    static int bookingid;
    @Test
    public void post01(){
           //Set the URL
        spec.pathParam("f","booking");
         //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingsPojo expectedData = new BookingsPojo("Jim","Brown",111,true,bookingDatesPojo,"Breakfast");
        System.out.println(expectedData);
        //Send the request get the response
        Response res = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{f}");
        res.prettyPrint();
        //Do assertion
       BiggerBookings actualData = JsonUtils.convertJsonToJavaObject(res.asString(),BiggerBookings.class);
        System.out.println(actualData);
        assertEquals(200,res.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

        bookingid=actualData.getBookingid();
        System.out.println(bookingid);
    }
}
