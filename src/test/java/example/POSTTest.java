package example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class POSTTest {

    @Test
    public void testPostRawTaxi() {
        String raw = "This is expected to be sent back as part of response body.";
        given()
                .contentType("text/plain")
                .body(raw)
        .when()
                .post("https://postman-echo.com/post")
        .then()
                .statusCode(200)
                .body("data", equalTo(raw));
    }

    @Test
    public void testPostFormData() {
        given()
                .formParam("foo1","bar1")
                .formParam("foo2","bar2")
        .when()
                .post("https://postman-echo.com/post")
        .then()
                .statusCode(200) // выдаёт 500 ответ
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }
}
