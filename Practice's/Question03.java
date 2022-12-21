package questions_witha_group;

import base_urls.DummyBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;


public class Question03 extends DummyBaseURL {
      /*
        Given
            http://dummy.restapiexample.com/api/v1/employees
        When
          I send a GET request to the Url
       And
           Accept type is "application/json"
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           There should be 24 employees
       And
           "Garrett Winters" should be one of the employee name
       And
           43, 61, and 23 should be among the ages
     */
    @Test
    public void quest(){
        spec.pathParams("f","api","s","v1","t","employees");

        //Get response
        Response res = given().spec(spec).accept("application/json").when().get("/{f}/{s}/{t}");
        res.prettyPrint();

        //Assert
        res.then().assertThat().contentType(ContentType.JSON).statusCode(200).body("data.id", hasItems(23),
                "data.employee_age",hasItems(43,61,23),"data.employee_name",hasItem("Garrett Winters"));


    }





}
