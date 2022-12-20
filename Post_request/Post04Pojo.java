package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BiggerBookings;
import pojos.BookingDatesPojo;
import pojos.BookingsPojo;

import java.awt.print.Book;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends HerOkuAppBaseUrl {
     /*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                     },
                 "additionalneeds": "Breakfast"

             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */
    @Test
    public void post04(){
//Set the url
        spec.pathParams("first","booking");
        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingsPojo expectedData = new BookingsPojo("Ali","Can",999,true,bookingDatesPojo,"Breakfast");

        System.out.println("expectedData" + expectedData);
        //Send the request and get the response
        Response res = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}");
        res.prettyPrint();

        //Do Assertion
        BiggerBookings actualData =res.as(BiggerBookings.class); // Bigger Booking is for response
        System.out.println("actual data :" + actualData);

        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());


    }
}
