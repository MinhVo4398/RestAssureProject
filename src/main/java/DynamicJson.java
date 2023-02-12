import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DynamicJson {
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payload.Addbook(isbn, aisle))
                .when()
                .post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);

        // delete Book

    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        // array - collection of elements
        // mutidimensional array - collection of arrays
        return new Object[][]{{"ojftwys", "9396"}, {"ctweee", "4235"}, {"orkjhjjs", "533"}};

    }

}
