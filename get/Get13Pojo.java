package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                          {
                            "meta": null,
                            "data": {
                                "id": 13,
                                "name": "Rahul Jha",
                                "email": "jha_rahul@beahan.co",
                                "gender": "male",
                                "status": "active"
                            }
                         }
    */
    @Test
    public void get13() {

        spec.pathParams("first","users","second",13);
        //Set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo("Rahul Jha","jha_rahul@beahan.co","male","active");
        GoRestPojo expectedData = new GoRestPojo(null,goRestDataPojo);
        System.out.println(expectedData);
        //Send the request get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
           response.prettyPrint();
           //DO assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println(actualData);
        assertEquals(200,response.statusCode());
       //1.Way
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(expectedData.getData().getEmail(),actualData.getData().getEmail());
        assertEquals(expectedData.getData().getGender(),actualData.getData().getGender());
        assertEquals(expectedData.getData().getName(),actualData.getData().getName());
        assertEquals(expectedData.getData().getStatus(),actualData.getData().getStatus());

        //2.Way
        assertEquals(goRestDataPojo.getStatus(),actualData.getData().getStatus());
        assertEquals(goRestDataPojo.getName(),actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualData.getData().getGender());
    }
}
