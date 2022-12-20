package put_request;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Put01 extends JsonPlaceHolderBaseUrl{
      /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Read the books",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Read the books",
									    "completed": false
									   }
     */
    @Test
    public void put01(){
        //Set the url
        spec.pathParams("first","todos","second",198);
        //Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
       Map<String,Object> expectedData =  obj.expectedDataJPH(21,"Read the books",false);
        System.out.println(expectedData);
        //Send the request get the response
        Response res = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        res.prettyPrint();
        //Do assertion
       Map<String,Object> actualData = res.as(HashMap.class);
        System.out.println(actualData);
        assertEquals(200,res.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));

    }
}
