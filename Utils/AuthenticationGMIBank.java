package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class AuthenticationGMIBank {


    public static String generateToken(){
        String url = "https://www.gmibank.com/api/authenticate";
        Map<String,Object> map = new HashMap<>();
        map.put("password","John.123");
        map.put("rememberMe",true);
        map.put("username","john_doe");
       Response res = given().contentType(ContentType.JSON).
               body(map).when().post(url);


   return res.jsonPath().getString("id_token");
    }
}
