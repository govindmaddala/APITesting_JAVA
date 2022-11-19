package practise;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class MockAPI {

    public static void main(String[] args) {
        String response = MockPayload.mockPayLoad();
        
        JsonPath jsonPath = new JsonPath(response);
        
        int totalAmount = jsonPath.getInt("dashboard.purchaseAmount"); //910
        
        int sizeOfCourse = jsonPath.getInt("courses.size()");  //3
       
        int countAmount = 0;
        
        for(int i=0; i<sizeOfCourse;i++) {
            
            String numString = Integer.toString(i);
            
            int coursePrice = jsonPath.getInt("courses["+numString+"].price");
            int copies = jsonPath.getInt("courses["+numString+"].copies");
            int total = coursePrice * copies;
            countAmount += total;
        }
        
        Assert.assertEquals(countAmount, totalAmount);

    }
    
    /*
    
    {
       "dashboard": {
                "purchaseAmount": 910,
                 "website": "rahulshettyacademy.com"
       },
       "courses": [
           {
               "title": "Selenium Python",
               "price": 50,
               "copies": 6
           },
           {
               "title": "Cypress",
               "price": 40,
               "copies": 4
           },
           {
               "title": "RPA",
               "price": 45,
               "copies": 10
           }
       ]
   }
    
    */

}
