package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingsPojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get16 extends HerOkuAppBaseUrl {
    /*
        Given
                https://restful-booker.herokuapp.com/booking/55
        When
                I send GET Request to the URL
        Then
                Status code is 200
                         {
                            "firstname": "Edgar",
                            "lastname": "Dominguez",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Breakfast"
                        }
     */
    @Test
    public void get16(){
        //Set the URL
        spec.pathParams("f","booking","s",555);
        //Set the expected data
        String expectedDataInString = new HerOkuAppTestData().expectedDataInString("Edgar","Dominguez",111,true,"2018-01-01","2019-01-01","Breakfast");
        BookingsPojo expectedData = JsonUtils.convertJsonToJavaObject(expectedDataInString,BookingsPojo.class);
        System.out.println(expectedData);

        //Send the request and get the response
        Response res = given().spec(spec).when().get("/{f}/{s}");
        res.prettyPrint();

        //Do assertion
        assertEquals(200,res.statusCode());
      BookingsPojo actualData = JsonUtils.convertJsonToJavaObject(res.asString(),BookingsPojo.class); // To use Pojo class with objectmapper is the best !!!
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());










    }
}
