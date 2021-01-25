import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Execution(ExecutionMode.CONCURRENT)
public class DeleteRequestTest extends BaseTest {
    public static Map<String, Object> jsonAsMap = new HashMap<>();

    @Test
    public void deletePostsById() {

        String postIdForDeleteRequest = "1";

        given()
                .header("Content-Type", "application/json")
                .when()
                .delete(BASE_URI + "/posts/" + postIdForDeleteRequest)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(Matchers.is("{}"));
    }
}
