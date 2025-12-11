package example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PATCHTest {

    @Test
    public void testPatchRequest() {
        String raw = "This is expected to be sent back as part of response body.";
        given()
                .contentType("text/plain")
                .body(raw)
        .when()
                .patch("https://postman-echo.com/patch")
        .then()
                .statusCode(200)
                .body("data", equalTo(raw));
    }
}
