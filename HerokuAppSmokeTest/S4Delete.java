package herokuappsmoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class S4Delete extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        User sends Delete request
    Then
        Status code is 201
    And
        Response body is "Created"
     */
    @Test
    public void delete01(){
    //Set the url
        spec.pathParams("f","booking","s",S1Post.bookingid);
    //Set the expected Data
        String expectedData = "Created";
    //Send the req get the response
        Response res = given().spec(spec).headers("Cookie", "token=" + generateToken()).contentType(ContentType.JSON)
                .when().delete("/{f}/{s}");
        res.prettyPrint();
     //Do assertion
        assertEquals(201,res.statusCode());
        assertEquals(expectedData,res.asString());
    }
}
