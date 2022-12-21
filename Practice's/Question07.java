package questionsbyme;

import base_urls.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RegresTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Question07 extends RegresBaseUrl {

  /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
    @Test
    public void q07(){
        spec.pathParam("first","users");
        //Send the request get the response
            RegresTestData obj = new RegresTestData();
           HashMap<String,String> expectedMap = obj.goRestTestData("morpheus","leader");
          Response res = given().spec(spec).contentType(ContentType.JSON).body(expectedMap).post("/{first}");
          res.prettyPrint();
         HashMap<String,String> actualData = res.as(HashMap.class);
         assertEquals(expectedMap.get("name"),actualData.get("name"));
         assertEquals(expectedMap.get("job"),actualData.get("job"));
    }






}
