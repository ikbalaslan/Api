package get_requests;

import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Mine_Get03 extends DummyApiBaseUrl {
      /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
      5. Çalışan isminin "Airi Satou" olduğunu ,
      çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
    {
            "id":"11"
             "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
        } gibi olduğunu test edin.
*/
    @Test
    public void get03(){
        spec.pathParam("f","employees");
        //Send the req
        Response res = given().spec(spec).when().get("/{f}");
        res.prettyPrint();
        List<String> name =  res.jsonPath().getList("data.findAll{it.id==5}.employee_name");
        List<Object> workerId5 =  res.jsonPath().getList("data.findAll{it.id==11}");
        //Map<Object,Object> ids =  res.jsonPath().getMap("data.findAll{it.employee_age==(40||21||19)}.employee_name");
       // System.out.println(ids);

        JsonPath json = res.jsonPath();
        assertTrue(name.contains("Airi Satou"));

       //assertTrue(workerId5.contains("employee_name=Jena Gaines"));

       res.then().assertThat().body("data.employee_age",hasItems(40,21,19));
        System.out.println("workerId5 : " + workerId5);


        assertEquals(11, json.getInt("data.id[10]"));
        assertEquals("Jena Gaines", json.getString("data.employee_name[10]"));
        assertEquals(90560, json.getInt("data.employee_salary[10]"));
        assertEquals(30, json.getInt("data.employee_age[10]"));
        assertEquals("", json.getString("data.profile_image[10]"));

        assertEquals(106450, json.getInt("data.employee_salary[-2]"));

        List<Object> list2 = new ArrayList<>();
        list2.add("id=11, employee_name=Jena Gaines, employee_salary=90560, employee_age=30, profile_image=");
        System.out.println(workerId5.containsAll(list2));
        System.out.println(res.jsonPath().getString("data.employee_name"));


    }
}
