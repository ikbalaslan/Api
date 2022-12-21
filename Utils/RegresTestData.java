package test_data;

import java.util.HashMap;

public class RegresTestData {
    public HashMap<String,String> goRestTestData(String name,String job){
        HashMap<String,String> ik = new HashMap<>();
        ik.put("name",name);
        ik.put("job",job);
        return ik;
    }

}
