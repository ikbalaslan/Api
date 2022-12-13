package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.path.json.JsonPath.given;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/2
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
              {
                "firstname": "James",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                Header should be
                "additionalneeds": "Breakfast"
                }
     */
    @Test
    public void get06(){
        spec.pathParams("first","booking","second",2);


        //Send the request and get the response
        Response res = given().spec(spec).when().get("/{first}/{second}");
        res.prettyPrint();

        //Do Assertion
        // Status code is 200
        //1st Way:
//        res.then().statusCode(200).
//                contentType(ContentType.JSON).
//                body("firstname",equalTo("Mary"),
//                        "lastname",equalTo("Jackson"),
//                        "totalprice",equalTo(757),
//                        "depositpaid",equalTo(true),
//                        "bookingdates.checkin",equalTo("2015-08-18"),
//                        "bookingdates.checkout",equalTo("2021-05-17"),
//                        "additionalneeds",equalTo("Breakfast"));

        //2nd Way:We will use JsonPath Class

        JsonPath jsonObj = res.jsonPath();
        //Hard Assertion
        assertEquals("MARY",jsonObj.getString("firstname").toUpperCase());
        assertEquals("Jackson",jsonObj.getString("lastname"));
        assertEquals(757,jsonObj.getInt("totalprice"));
        assertEquals(true,jsonObj.getBoolean("depositpaid"));
        assertEquals("2015-08-18",jsonObj.getString("bookingdates.checkin"));
        assertEquals("2021-05-17",jsonObj.getString("bookingdates.checkout"));

        //How to Do Soft Assertion
        //1: Create soft Assertion Object
        SoftAssert softObj = new SoftAssert();

        //2: Do Assertion

        softObj.assertEquals(jsonObj.getString("firstname"),"Mary","firstname did not match!");
        softObj.assertEquals(jsonObj.getString("lastname"),"Jackson","lastname did not match!");
        softObj.assertEquals(jsonObj.getInt("totalprice"),757,"firstname did not match!");
        softObj.assertEquals(jsonObj.getBoolean("depositpaid"),true,"firstname did not match!");
        softObj.assertEquals(jsonObj.getString("bookingdates.checkin"),"2015-08-18","firstname did not match!");
        softObj.assertEquals(jsonObj.getString("bookingdates.checkout"),"2015-08-18","firstname did not match!");
        //3: Use assertAll()method (Otherwise soft assertion will pass in every scenerio)
        softObj.assertAll();










    }

}
