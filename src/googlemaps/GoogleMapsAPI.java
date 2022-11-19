package googlemaps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import io.restassured.RestAssured;
import utils.Response;

public class GoogleMapsAPI {
    
    public static String addPlace() {
        String postEndpoint = "/maps/api/place/add/json";
        String response = given()
//        .log().all()
        .queryParam("key", "qaclick123")
        .headers("Content-Type","application/json")
        .body(Payloads.postPayload())
        
        .when()
        .post(postEndpoint)
        .then()
//        .log().all()
        .assertThat().statusCode(200)
        .body("scope", equalTo("APP"))
        .header("Server", "Apache/2.4.41 (Ubuntu)")
        .extract().response().asString();
        
        String placeID = Response.keyName(response, "place_id");
        
        return placeID;
    }
    
    public static void updatePlace(String placeID,String address) {
        String putEndPoint = "/maps/api/place/update/json";
        given()
        .queryParam("key", "qaclick123")
        .headers("Content-Type","application/json")
        .body(Payloads.putPayLoad(placeID,address))
        .when()
        .put(putEndPoint)
        .then().assertThat().statusCode(200);
    }
    
    public static String getPlace(String placeID){
        String getEndPoint = "/maps/api/place/get/json";
        
        String response = given()
        .queryParam("key", "qaclick123")
        .queryParam("place_id", placeID)
        
        .when()
        .get(getEndPoint)
        
        .then()
        .assertThat()
        .statusCode(200)
        .extract().response().asString();
        return response;
    }

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        
        String placeID =  GoogleMapsAPI.addPlace();
        String address = "Indiana";
        
        GoogleMapsAPI.updatePlace(placeID,address);
        
        String getResponse = GoogleMapsAPI.getPlace(placeID);
        
        String addressResponse = Response.keyName(getResponse, "address");
        
        Assert.assertEquals(addressResponse, address);
        System.out.println("Success");
    }

}
