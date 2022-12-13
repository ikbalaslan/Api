package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get01 {
    /*
    1)Postman is used for manual API testing.
    2)We use Rest-Assured Library for API Automation Testing.
    3)To type automation script follow the given steps:
      a)Understand the requirement
      b)Type test cases
          To type test cases we use "Gherkin Language"
          The keywords are      i)Given: It is for pre-conditions //assert().than()
                                ii)When: It is for actions
                                iii)Then: It is for outputs
                                iv)And: It is for multiple given, when and then.
      c)Start to type Automation Script
          i)Set the URL
          ii)Set the expected Data(Post-Put-Patch)
          iii)Type code to send the request
          iv)Do Assertion
     */
     /*
   Given
       https://restful-booker.herokuapp.com/booking/101
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/
     @Test
     public void get01(){
         //Set the URL
         String url = "https://restful-booker.herokuapp.com/booking/101";
         //Set the expected data


         //Send the request and get the response
         Response res = given().when().get(url);
       res.prettyPrint();

         //Do Assertion
         //Content Type should be JSON
         //Status Line should be HTTP/1.1 200 OK
         res.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

         //How to print "Status Code" on the console
         System.out.println("Status Code: " + res.getStatusCode());

         //How to print "Content Type"
         System.out.println("Content type: " + res.contentType());

         //How to print "Status Line" on the console
         System.out.println("Status Line: " + res.statusLine());

         //How to print "Header" on the console
         System.out.println("Header: " + res.getHeader("Connection"));

         System.out.println("========");
         //How to print "Headers" on the console
         System.out.println("Headers: " + res.getHeaders());

         //How to print "time" on the console
         System.out.println("Time : " + res.getTime());


     }


}
