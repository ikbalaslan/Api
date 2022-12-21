package questions_witha_group;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Questions01 extends HerOkuAppBaseUrl {
    /*
    Q01-
When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking
And Accept type is "application/JSON"
Then HTTP Status Code should be 200
And Response format type should be "application/JSON"
     */
     @Test
    public void gett01(){
         spec.pathParam("first","booking");
         Response res = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}");
         res.prettyPrint();
         res.then().assertThat().statusCode(200).contentType(ContentType.JSON);

     }




    /*
    Q02
    When I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/5
    And Accept type is "application/JSON"
      Then HTTP Status Code should be 200
And first name should be "Der"
And total price should be 11111        */
    @Test
    public void gett02() {
    spec.pathParams("first","booking","second",5);
    Response res = given().spec(spec).accept("application/json").when().get("/{first}/{second}");
    res.prettyPrint();
    res.then().assertThat().body("firstname",equalTo("Der"),"totalprice",equalTo(11111));
    }

    /*
    Q03-
    When I send a GET request to REST API URL http://dummy.restapiexample.com/api/v1/employees
    And Accept type is "application/JSON"
    Then HTTP Status Code should be 200
    And "Garrett Winters" should be displayed among data     */

        /*
    Q04-
When I send a GET request to REST API URL http://dummy.restapiexample.com/api/v1/employee/1
And Accept type is "application/JSON"
Then HTTP Status Code should be 200
And employee name should be "Tiger Nixon"
And employee salary should be "320800"
And employee age should be "61"
     */












}
