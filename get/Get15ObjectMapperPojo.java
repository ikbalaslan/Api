package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;

public class Get15ObjectMapperPojo extends JsonPlaceHolderBaseUrl {
    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */
    @Test
    public void get15(){
        //Set the url
        spec.pathParams("f","todos","s",198);
        //Set the expected data
        String expectedDataString = new JsonPlaceHolderTestData().expectedDataInString(10,"quis eius est sint explicabo",true);

      JsonPlaceHolderPojo expectedData =  JsonUtils.convertJsonToJavaObject(expectedDataString, JsonPlaceHolderPojo.class);
     //Send the request get the response
      Response res =  given().spec(spec).when().get("/{f}/{s}");
      res.prettyPrint();
      //Do assertion
      JsonPlaceHolderPojo actualData =JsonUtils.convertJsonToJavaObject(res.asString(),JsonPlaceHolderPojo.class);
        System.out.println(actualData);
        Assert.assertEquals(expectedData.getUserId(),actualData.getUserId());
        Assert.assertEquals(expectedData.getTitle(),actualData.getTitle());
        Assert.assertEquals(expectedData.getCompleted(),actualData.getCompleted());




    }
}
