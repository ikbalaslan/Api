package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
     /*
       Given
           https://gorest.co.in/public/v1/users
       When
           User send GET Request
       Then
           The value of "pagination limit" is 10
       And
           The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
       And
           The number of users should  be 10
       And
           We have at least one "active" status
       And
           "Bhaaswar Achari", "Abhirath Kocchar", "Sher Dutta" are among the users
       And
           The female users are less than or equal to male users
    */
    @Test
    public void get11(){
        //Set the URL
        spec.pathParam("f","users");
        //Set the expected data

        //Send the request get the response
        Response res = given().spec(spec).when().get("/{f}");
        res.prettyPrint();

        //Assertion
        res.then().
                assertThat().
                statusCode(200)
                .body("meta.pagination.limit",equalTo(10),
                        "meta.pagination.links.current",
                        equalTo("https://gorest.co.in/public/v1/users?page=1"),"data.id",hasSize(10),
                        "data.status",hasItem("inactive"),
                        "data.name",hasItems("Tej Bhattacharya","Pres. Harit Chaturvedi"));


        //The female users are less than or equal to male users
        //I will compare number of female and male users
        //1st:Way I will get all the genders in a list then calculate the number of females then compare it with list .
        JsonPath jsonObj = res.jsonPath();
       List<String> genders = jsonObj.getList("data.gender");
        System.out.println("genders" + genders);

        int numOfFemale=0;
        for (String w : genders){
            if (w.equals("female")){
                numOfFemale++;
            }

        }
       int numOfMale = genders.size()-numOfFemale;
        assertTrue(numOfFemale>=numOfMale);

        //2nd Way: Groovy language First come data then reach list

       List<String> femaleList = jsonObj.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleList);

        List<String> maleList = jsonObj.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(maleList);



        assertTrue(femaleList.size()>=maleList.size());

















    }








}
