package get_requests;


import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class Get02 {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/1
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void get02(){
        //Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/1";
        Response response=given().when().get(url);
        response.prettyPrint();

        //Do Assertion
        response.then().
                assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");

        //Response body contains "Not Found"
        //assertTrue(x) method passes if x is true
        //If this one gives the true it will pass
        assertTrue(response.asString().contains("Not Found"));

        //Response body does not contain "TechProEd"
        //assertFalse(x) method passes if x is false
        assertFalse(response.asString().contains("TechProEd"));

        //Server is "Cowboy"
        //assertEquals(x,y) passes if x and y are equal.
        assertEquals("Cowboy",response.getHeader("Server"));


    }










}
