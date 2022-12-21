package test_data;

import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {
     public Map<String,String> goRestDataMapSetUp(String name,String email,String gender,String status){
         Map<String,String> ikmap = new HashMap<>();
         ikmap.put("name",name);
         ikmap.put("email",email);
         ikmap.put("gender",gender);
         ikmap.put("status",status);
         return ikmap;
     }
     public Map<String,Object> expectedDataMapSetUp(Object meta,Map<String,String> data){
         Map<String,Object> expectedDataMap = new HashMap<>();
         expectedDataMap.put("meta",meta);
         expectedDataMap.put("data",data);
         return expectedDataMap;
     }


}
/*
 {
        "meta": null,
        "data": {

            "name": "Suresh Johar",
            "email": "suresh_johar@von-damore.biz",
            "gender": "female",
            "status": "active"
         }
        }
 */