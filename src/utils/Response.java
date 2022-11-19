package utils;

import io.restassured.path.json.JsonPath;

public class Response {
    
    public static String keyName(String payload,String key) {
        JsonPath jsonPath = new JsonPath(payload);
        return jsonPath.getString(key);
    }

}
