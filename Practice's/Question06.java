package questionsbyme;

import base_urls.RegresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Question06 extends RegresBaseUrl {

   /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
    @Test
    public void get06(){
        spec.pathParam("first","unknown");
        //Send the request and get response
       Response res= given().spec(spec).when().get("/{first}");
       res.prettyPrint();
       //Assertion
        res.then().assertThat().statusCode(200).contentType(ContentType.JSON);
        //2)Print all pantone_values
        JsonPath jsonObj = res.jsonPath();
        List<String> pantone = jsonObj.getList("data.pantone_value");
        System.out.println(pantone);

        // 3)Print all ids greater than 3 on the console
        //Assert that there are 3 ids greater than 3
       List<Integer> idsGreater3 = jsonObj.getList("data.findAll{it.id>3}.id");
        System.out.println(idsGreater3);
        assertEquals(3,idsGreater3.size());

        // 4)Print all names whose ids are less than 3 on the console
        // Assert that the number of names whose ids are less than 3 is 2
       List<String> names = jsonObj.getList("data.findAll{it.id<3}.name");
        System.out.println(names);

        assertEquals(2,names.size());





    }
}
