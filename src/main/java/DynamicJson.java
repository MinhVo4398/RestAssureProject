import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DynamicJson {
    @Test
    public void addBook() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payload.Addbook())
                .when()
                .post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }
}
