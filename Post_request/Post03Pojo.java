package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {
         /*
         Given
            1) https://jsonplaceholder.typicode.com/todos
            2)  {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */
    @Test
    public void post03(){
        //Set the URL
        spec.pathParam("first","todos");
        //Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println(expectedData);
        //Send the request get the response
        Response res = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}");
        res.prettyPrint();

        //Do Assertion
        JsonPlaceHolderPojo actualData = res.as(JsonPlaceHolderPojo.class);
        System.out.println("actuldata = " + actualData);
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());



    }
}
