import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Execution(ExecutionMode.CONCURRENT)
public class PutRequestTest extends BaseTest {
    public static Map<String, Object> jsonAsMap = new HashMap<>();

    @BeforeEach
    public void preparePayload() {
        jsonAsMap.put("userId", 11);
        jsonAsMap.put("id", 1);
        jsonAsMap.put("title", "bip-bip");
        jsonAsMap.put("body", "lorem ipsum");
    }

    @Test
    public void putPostsById() {

        String postIdForPutRequest = jsonAsMap.get("id").toString();

        given()
                .header("Content-Type", "application/json")
                .body(jsonAsMap)
                .when()
                .put(BASE_URI + "/posts/" + postIdForPutRequest)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("userId", equalTo(jsonAsMap.get("userId")))
                .body("id", notNullValue())
                .body("title", equalTo(jsonAsMap.get("title")))
                .body("body", equalTo(jsonAsMap.get("body")));
    }
}
