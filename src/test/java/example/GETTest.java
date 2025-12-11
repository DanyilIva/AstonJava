package example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GETTest {

    @Test
    public void testGetRequest() {
        given()
                .param("foo1","bar1")
                .param("foo2","bar2")
        .when()
                .get("https://postman-echo.com/get?foo1=bar1&foo2=bar2")
        .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }
}
