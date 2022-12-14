package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Mine_Get02 extends HerOkuAppBaseUrl {
        /*
    Given
        "https://restful-booker.herokuapp.com/booking/5 "
    When
      I send a GET request to the Url
   And
       Accept type is "application/json"
   Then
       HTTP Status Code should be 200
   And
       Response format should be "application/json"
   And
       first name should be "Der"
   And
       And total price should be 11111
   And
       And checkin date should be "2020-11-02"

    "firstname": "Nick",
    "lastname": "Smith",
    "totalprice": 100,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2021-11-11",
        "checkout": "2022-12-12"
    },
    "additionalneeds": "Breakfast"
}
 */
    @Test
    public void get02(){
        spec.pathParams("f","booking","s",5);
        //expected data
        HerOkuAppTestData obj = new HerOkuAppTestData();
       Map<String,String> data = obj.bookingdatesMapSetUp("2021-11-11","2022-12-12");
      Map<String,Object> expectedData =  obj.expectedDataSetUp("Nick","Smith",100,true,data,"Breakfast");

      //send the req
        Response res =   given().spec(spec).contentType(ContentType.JSON).accept("application/json").when().get("/{f}/{s}");
        //assert
        res.prettyPrint();
        res.then().assertThat().statusCode(200).body("firstname",equalTo("Nick"),"totalprice",equalTo(100)
                ,"bookingdates.checkin",equalTo("2021-11-11"));
  Map<String,Object> actualData = res.as(HashMap.class);
        System.out.println(actualData);
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));





    }

}
