import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourseValidation() {
        int sum = 0;
        JsonPath js = new JsonPath(payload.CoursePrice());
        int count = js.getInt("courses.size()");
        for (int i = 0; i < count; i++) {
            int prices = js.get("courses[" + i + "].price");

            int copies = js.get("courses[" + i + "].copies");

            int amount = prices * copies;
            System.out.println(amount);
            sum = sum + amount;

        }
        System.out.println(sum);
        int purchuseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchuseAmount);
        System.out.println("----Verify---------");
        Assert.assertEquals(sum, purchuseAmount);
    }

}
