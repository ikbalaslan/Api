package herokuappsmoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingsPojo;
import utils.JsonUtils;

import static herokuappsmoketest.S1Post.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

/*
Given
   https://restful-booker.herokuapp.com/booking/:id
And
    {
    "firstname" : "James",
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
    "firstname": "James",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}


 */
public class S2Put extends HerOkuAppBaseUrl {
@Test
    public void put(){
    spec.pathParams("f","booking","s",bookingid);
    //Set expected data
    BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");
    BookingsPojo expectedData = new BookingsPojo("James","Brown",111,true,bookingDatesPojo,"Breakfast");
    //Send the request get the response
    Response res = given().spec(spec).contentType(ContentType.JSON).
            headers("Cookie", "token="+ generateToken())
            .body(expectedData).when()
            .put("/{f}/{s}");
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
