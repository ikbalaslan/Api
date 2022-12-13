package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {
    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */
    @Test
    public void get07(){


        //Set the url
        spec.pathParam("first","todos");
        //Set the expected data

        //Send the request and get the response
        Response res = given().spec(spec).when().get("/{first}");
        res.prettyPrint();

        //Do Assertion
        // Status code is 200
        assertEquals(200,res.statusCode());

       //2)Print all ids greater than 190 on the console
        //Assert that there are 10 ids greater than 190
       JsonPath jsonObj = res.jsonPath();

       List<Integer> ids = jsonObj.getList("id");
       List<Integer> ids2 = new ArrayList<>();


        for (Integer w : ids){
            if (w>190){
                ids2.add(w);
            }
        }
        System.out.println(ids + "This is Just List");
        System.out.println(ids2.size());
        assertEquals(10,ids2.size());

        //2nd Way:
       List<Integer> idsGreaterThan190Groovy = jsonObj.getList("findAll{it.id>190}.id");//Groovy Language ('it' is like 't->' in lambda )
        System.out.println(idsGreaterThan190Groovy + "This is GROOVY");
        assertEquals(10,idsGreaterThan190Groovy.size());

        // 3)Print all userIds whose ids are less than 5 on the console
        // Assert that the number of userIds whose ids are less than 5 is 4

        List<Integer> userIdsGroovy = jsonObj.getList("findAll{it.id<5}.userId");
        System.out.println("userIds :" + userIdsGroovy);
        assertEquals(4,userIdsGroovy.size());

        // 4)Print all titles whose ids are less than 5
        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
  List<String> list1 = jsonObj.getList("findAll{it.id<5}.title");
       //1st WAY
        assertTrue(list1.contains("delectus aut autem"));
       //2nd WAY
        assertTrue("'delectus aut autem' does not exists",list1.contains("delectus aut autem"));
       //3rd WAY
        assertTrue("'delectus aut autem' does not exists",list1.stream().anyMatch(t->t.equals("delectus aut autem")));
    }










}
