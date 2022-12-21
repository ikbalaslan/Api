package questionsbyme;

import base_urls.HerOkuAppBaseUrl;
import com.sun.xml.bind.api.TypeReference;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Question04 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"

 */




    @Test
    public void get04(){
        spec.pathParams("f","booking").queryParams("firstname","Brandon","lastname","Wilson");


        Response res = given().spec(spec).when().get("/{f}");
        res.prettyPrint();

        res.then().assertThat().statusCode(200);
       assertTrue(res.asString().contains("bookingid"));




    }









}
