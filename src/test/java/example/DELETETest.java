package example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DELETETest {

    @Test
    public void testDeleteRequest() {
        String raw = "This is expected to be sent back as part of response body.";
        given()
                .contentType("text/plain")
                .body(raw)
        .when()
                .delete("https://postman-echo.com/delete")
        .then()
                .statusCode(200)
                .body("data", equalTo(raw));
    }
}
