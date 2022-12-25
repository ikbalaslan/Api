package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Get01 {
    /*
  Given
      https://petstore.swagger.io/v2/pet/12
  When
      User sends a GET Request to the url
  Then
      HTTP Status Code should be 200
  And
      Content Type should be JSON
  And
      Status Line should be HTTP/1.1 200 OK
*/
    public static void main(String[] args) {
        //Set the URL
        String url = "https://petstore.swagger.io/v2/pet/12";
        //Set the expected data

        //Send the request get the response

        Response response = given().when().get(url);
        response.prettyPrint();
        if (response.statusCode()==200){
            System.out.println("Status code passed");
        }else {
            System.out.println("Test failed Status code : " + response.statusCode());

        }

        //Content Type should be JSon
        if (response.contentType().equals("application/json")){
            System.out.println("Content type passed.");
        }else{
            System.out.println("Content type failed ");
        }

//Status Line should be HTTP/1.1 200 OK
        if (response.statusLine().equals("HTTP/1.1 200 OK")) {
            System.out.println("Test Passed: Status Line is HTTP/1.1 200 OK");

        }else {
            System.out.println("Test Failed:  Status Line is " + response.statusLine());
        }



    }

}
