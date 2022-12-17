package herokuappsmoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class S5Get extends HerOkuAppBaseUrl {
     /*
      Given
          https://restful-booker.herokuapp.com/booking/:id
      When
          User sends Get request
      Then
          Status code is 404
      And
          Response body is like "Not Found"

       */
    @Test
    public void get02(){
        //Set the url
        spec.pathParams("f","booking","s",S1Post.bookingid);
        //Expected Data
        String expectedData = "Not Found";
        //Send the request get the response
        Response res = given().spec(spec).when().get("/{f}/{s}");
        res.prettyPrint();
        //Assertion
        assertEquals(expectedData,res.asString());
        assertEquals(404,res.statusCode());
    }


}
