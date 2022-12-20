package post_requests;

import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.DummyApiDataPojo;
import pojos.DummyApiResponsePojo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper extends DummyApiBaseUrl {/*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */
    /*
    Given
          https://dummy.restapiexample.com/api/v1/create
    And
    {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
     When(for action)
           User sends the Post request
     Then
         Status code is 200
     And
         Response body should be like the following
          {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */
    @Test
    public void post05() throws IOException {

        //Set the url
        spec.pathParam("first","create");
        //Set the expected data
        DummyApiDataPojo expectedData = new DummyApiDataPojo("Ali Can",111111,23,"Perfect image");
        //Send the request and get the response
        Response res = given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}");
        res.prettyPrint();
        //Do Assertion
      DummyApiResponsePojo actualData = new ObjectMapper().readValue(res.asString(),DummyApiResponsePojo.class);
        System.out.println(actualData);

        assertEquals(200,res.statusCode());
        assertEquals("success",actualData.getStatus());
        assertEquals("Successfully! Record has been added.",actualData.getMessage());
        assertEquals(expectedData.getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(),actualData.getData().getProfile_image());
    }
}
