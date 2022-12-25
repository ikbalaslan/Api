package get_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Get02b extends PetStoreBaseUrl {
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
       spec.pathParams("f","pet","s","findByStatus").queryParams("status","available");
        //Set the expected Data
        //Send the req get the response
        Response res = given().spec(spec).when().get("/{f}/{s}");
      res.prettyPrint();
      //Assertion
        assertEquals(200,res.statusCode());




    }
}
