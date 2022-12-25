package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get04 extends HerokuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/71926
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                     {
                        "firstname": "Josh",
                        "lastname": "Allen",
                        "totalprice": 111,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2018-01-01",
                            "checkout": "2019-01-01"
                        },
                        "additionalneeds": "super bowls"
                    }*/
    @Test
    public void get04(){
        //Set the url
        spec.pathParams("f","booking","s",8065);
        //Set the expected data

        //Send the request and get the response
        Response res = given().spec(spec).when().get("/{f}/{s}");
        res.prettyPrint();
        //Do assertion
        JsonPath jsonPath = res.jsonPath();
        assertEquals(200,res.statusCode());
        assertEquals("Jane",jsonPath.getString("firstname"));
        assertEquals("application/json; charset=utf-8",res.contentType());
        assertEquals("Doe",jsonPath.getString("lastname"));
        assertEquals(111,jsonPath.getInt("totalprice"));
        assertEquals(true,jsonPath.getBoolean("depositpaid"));

    }
}

