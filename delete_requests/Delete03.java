package delete_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import pojos.DummyApiDeleteResponsePojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Delete01 extends DummyRestApiBaseUrl {

    /*
Given
    https://dummy.restapiexample.com/api/v1/delete/2
When
    User sends Delete Request
Then
    Status code is 200
And
    "status" is "success"
And
    "data" is "2"
And
    "message" is "Successfully! Record has been deleted"
 */
@Test
    public void delete01() throws IOException {
    //Set the URL
    spec.pathParams("f","delete","s","2");
    //Set the expected data
    DummyApiDeleteResponsePojo expectedData = new DummyApiDeleteResponsePojo("success","2","Successfully! Record has been deleted");
    System.out.println(expectedData);

    //Send the req get the response
    Response response = given().spec(spec).when().delete("/{f}/{s}");
    response.prettyPrint();

    //Do assertion
   DummyApiDeleteResponsePojo actualData = new ObjectMapper().readValue(response.asString(),DummyApiDeleteResponsePojo.class);

   assertEquals(200,response.statusCode());
   assertEquals(expectedData.getStatus(),actualData.getStatus());
   assertEquals(expectedData.getData(),actualData.getData());
   assertEquals(expectedData.getMessage(),actualData.getMessage());




}

}
