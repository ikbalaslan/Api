package get_requests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get02 {
// Print all "available" pets on console by using https://petstore.swagger.io
    /*
    Given
       https://petstore.swagger.io/v2/pet/findByStatus?status=available

    When
       User send get request to URL

    Then
       HTTP Status Code is 200
    And
       Print all "available" pets on console
     */


    @Test
    public void get02(){
        //Set the URL
        String url = "https://petstore.swagger.io/v2/pet/findByStatus?status=available";
        //Set the expected Data
        //Send the req get the response
        Response res = given().when().get(url);
      res.prettyPrint();
      //Assertion
        assertEquals(200,res.statusCode());




    }
}
