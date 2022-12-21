package questions_witha_group;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Question05 extends JsonPlaceHolderBaseUrl {
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
    public void post0000(){
        spec.pathParam("f","todos");
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
       Map<String,Object> expectedHashMap = obj.expectedDataJPH(55,"Tidy your room",false);

        Response res = given().spec(spec).contentType(ContentType.JSON).body(expectedHashMap).when().post("/{f}");
        res.then().assertThat().statusCode(201);

        Map<String,Object> actualHashMap = res.as(HashMap.class);

        //Assert
        assertEquals(expectedHashMap.get("userId"),actualHashMap.get("userId"));
        assertEquals(expectedHashMap.get("title"),actualHashMap.get("title"));
        assertEquals(expectedHashMap.get("completed"),actualHashMap.get("completed"));
        assertEquals(201,actualHashMap.get("id"));
















    }
}
