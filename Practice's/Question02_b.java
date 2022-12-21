package questionsbyme;

import base_urls.RegresBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class Question02 extends RegresBaseUrl {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty

     */
    @Test
    public void get02(){
        spec.pathParams("f","users","s",23);

        Response res = given().spec(spec).when().get("/{f}/{s}");
        res.prettyPrint();

        res.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found").header("Server","cloudflare").body("isEmpty()", is(true));


    }

}
