package get_requests;

import base_urls.DummyApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;

public class Mine_Get01 extends DummyApiBaseUrl {
    /*
    When I send a GET request to REST API URL http://dummy.restapiexample.com/api/v1/employees
    And Accept type is “application/JSON”
    Then HTTP Status Code should be 200
    And “Garrett Winters” should be displayed among data     */
@Test
    public void get01(){
    spec.pathParam("f","employees").queryParam("data.employee_name","Garrett Winters");
    //Set expected
    //Send req
   Response res = given().spec(spec).accept("application/JSON").when().get("/{f}");
   res.prettyPrint();
    //assert
    assertEquals(200,res.statusCode());
    res.then().assertThat().body("data.employee_name",hasItems("Garrett Winters"));
    List<String> names =  res.jsonPath().getList("data.employee_name");
    names.stream().filter(t-> t.equals("Garrett Winters")).forEach(System.out::println);

    System.out.println(res.jsonPath().getList("data.findAll{it.employee_name=='Garrett Winters'}.id"));
    System.out.println(res.jsonPath().getList("data.findAll{it.id==2}"));


}
}
