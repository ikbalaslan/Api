package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */
@Test
    public void delete01(){
    //Set the URL
    spec.pathParams("f","todos","s",198);
    //Set the expected data
    Map<String,Object> expectedData = new HashMap<>();


    //Send the request and get the response
    Response res = given().spec(spec).when().delete("/{f}/{s}");
        res.prettyPrint();

        //Do assertion
          Map<String,Object> actualData = res.as(HashMap.class);
    System.out.println(actualData);
    assertEquals(200,res.statusCode());
    assertEquals(expectedData,actualData);
    //or
    assertEquals(expectedData.size(),actualData.size());
    //or
    assertTrue(actualData.isEmpty());
    //or
    res.then().assertThat().body("",hasSize(0));

    /*
    How to automate Delete Request in Api Testing?
      i)Create a new data by using "Post Requst"
      ii)Use "Delete Request" to delete new data

      //Note: Do not delete existing data, create a data to delete.
     */
}

}
