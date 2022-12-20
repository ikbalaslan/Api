package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
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
    public void post01(){


        //Set the url
        spec.pathParam("first","todos");
       //Set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        //Send the request and get the response
        Response res = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        res.prettyPrint();

        //Do Assertion
       Map<String,Object> actualData = res.as(HashMap.class);
       assertEquals(201,res.statusCode());
       assertEquals(expectedData.get("completed"),actualData.get("completed"));
       assertEquals(expectedData.get("title"),actualData.get("title"));
       assertEquals(expectedData.get("userId"),actualData.get("userId"));


    }
    @Test
    public void post01b(){
        //Set the url
        spec.pathParam("first","todos");
        //Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataJPH(55,"Tidy your room",false);

        //Send the request and get the response
        Response res = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        res.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData = res.as(HashMap.class);
        assertEquals(201,res.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));


    }






}
