package questions_witha_group;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Question02 extends HerOkuAppBaseUrl {
    /*    https://restful-booker.herokuapp.com/booking/7
    url'ineGET request'i yolladigimdagelen response'unstatus kodunun
    200ve content type'inin "application/json"ve firstname'in
    "Sally"ve lastname'in "Ericsson"ve checkin date'in 2018-10-07"ve
    checkout date'in 2020-09-30 oldugunu test edin
    */
    @Test
    public void get00(){
        //URL
        spec.pathParams("first","booking","second",7).
                queryParams("firstname","Sally",
                        "lastname","Ericsson");
        //Send the request get the response
        Response res = given().spec(spec).when().get("/{first}/{second}");
        res.prettyPrint();

        //Assertion
        res.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("bookingdates.checkin",equalTo("2018-10-07"),
                "bookingdates.checkin","2020-09-30");
  // Serialization JAVA-JSON
  // De-Serialization JSON-JAVA
        JsonPath jsonObj = res.jsonPath();
        //Crete a softAssert obj
        SoftAssert softObj = new SoftAssert();

        //

        softObj.assertEquals(jsonObj.getString("bookingdates.checking"),"2018-10-23");
        softObj.assertEquals(jsonObj.getInt("bookingdates.checking"),4);
        softObj.assertEquals(jsonObj.getBoolean("bookingdates.checking"),false);



        //assertAll
        softObj.assertAll();


    }







}
