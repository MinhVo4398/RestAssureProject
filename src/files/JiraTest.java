import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class JiraTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://localhost:8080";
        // Login Scenario
        SessionFilter session = new SessionFilter();

        String response = given().header("Content-Type", "application/json").body("{\n" +
                        "    \"username\": \"minhvo4398\",\n" +
                        "    \"password\": \"thienthan040398\"\n" +
                        "}").log().all().filter(session).when().post("/rest/auth/1/session")
                .then().log().all().extract().response().asString();

        given().pathParam("key", "10200").log().all().header("Content-Type", "application/json").body("{\n" +
                        "    \"body\": \"This is my first comment\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").filter(session).when().post("/rest/api/2/issue/{key}/comment")
                .then().log().all().assertThat().statusCode(201);


    }
}
