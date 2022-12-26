package post_requests;

import base_urls.MedunnaBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojos.MedunnaPojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static utils.MedunnaAuthentication.generateToken;

public class Post03 extends MedunnaBaseUrl {
    /*
         Given
           1) https://medunna.com/api/rooms
           2)   {
                    "createdBy": "john_doe",
                    "createdDate": "2022-12-04T13:40:13.537985Z",
                    "roomNumber": 879396,
                    "roomType": "TWIN",
                    "status": true,
                    "price": 111,
                    "description": "My Room"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                               {
                                    "createdBy": "john_doe",
                                    "createdDate": "2022-12-04T13:40:13.537985Z",
                                    "id": 369858,
                                    "roomNumber": 879396,
                                    "roomType": "TWIN",
                                    "status": true,
                                    "price": 111,
                                    "description": "My Room"
                                }
     */
    @Test
    public void post03() throws IOException {
        //Set the url
        spec.pathParam("f","rooms");
        //Set the expected data
        MedunnaPojo expectedData = new MedunnaPojo("john_doe","2022-12-04T13:40:13.537985Z",876396,"TWIN",true,111,"MyRoom");
        System.out.println(expectedData);
        //Send the req get the response
        Response response = given().spec(spec).headers("Authorization","Bearer "+generateToken()).contentType(ContentType.JSON).body(expectedData).post("/{f}");
        response.prettyPrint();
        //Do the assertion
        MedunnaPojo actualData = new ObjectMapper().readValue(response.asString(),MedunnaPojo.class);
        System.out.println(actualData);




    }
}
