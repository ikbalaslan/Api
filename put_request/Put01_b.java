package put_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojos.PetStoreIUserPojo;
import pojos.PetStoreUserResponsePojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Put01 extends PetStoreBaseUrl {
    /*
       Given
         1) https://petstore.swagger.io/v2/user/JohnDoe
         2)  {
                "id": 11234,
                "username": "JohnnyFoe",
                "firstName": "John",
                "lastName": "Doe",
                "email": "j@d.com",
                "password": "1234",
                "phone": "1234",
                "userStatus": 3
              }
      When
          I send POST Request to the Url
      Then
          Status code is 200
      And
          response body is like {
                              {
                                  "code": 200,
                                  "type": "unknown",
                                  "message": "11234"
                              }
   */
    @Test
    public void put01() throws IOException {
        //Set the URL
        spec.pathParams("f","user","s","JohnDoe");
        //Set the expected Data
        PetStoreIUserPojo expectedData = new PetStoreIUserPojo(11234,"JohnnyFoe","John","Foe","j@d.com","1234","1234",3);
        System.out.println(expectedData);
        //Send the request get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{f}/{s}");
        response.prettyPrint();
        //Do assertion
        PetStoreUserResponsePojo actualDatA = new ObjectMapper().readValue(response.asString(), PetStoreUserResponsePojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(200,actualDatA.getCode());
        assertEquals("unknown",actualDatA.getType());
        assertEquals("11234",actualDatA.getMessage());



    }
}
