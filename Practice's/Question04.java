package questions_witha_group;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Question04 extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,

                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */
    @Test
    public void get000(){
        spec.pathParams("f","todos","s",2);

        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedHashMap = obj.expectedDataJPH(1,"quis ut nam facilis et officia qui",false);

        Response res = given().spec(spec).when().get("/{f}/{s}");
        Map<String,Object> actualHashMap = res.as(HashMap.class);

        assertEquals(expectedHashMap.get("userId"),actualHashMap.get("userId"));
        assertEquals(expectedHashMap.get("title"),actualHashMap.get("title"));
        assertEquals(expectedHashMap.get("completed"),actualHashMap.get("completed"));
        assertEquals("1.1 vegur",res.header("Via"));
        assertEquals("Cloudflare",res.header("Server"));






    }


}
