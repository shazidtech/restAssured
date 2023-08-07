package GoogleMapAPI;

import files.payload;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import org.testng.annotations.Test;

public class sumValidation{
    int sum = 0;
    @Test
    public void sumOfCourses(){
        JsonPath js = new JsonPath(payload.coursePrice());
        int count = js.getInt("courses.size()");

        for(int i=0; i < count; i++ ){
            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int amount = price * copies;
            System.out.println(amount);
            sum = sum + amount;

        }
        System.out.println("sum of all the price si " + sum);
        int pamount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum, pamount);


    }
}
