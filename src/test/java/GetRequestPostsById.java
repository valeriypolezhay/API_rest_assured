import io.restassured.response.Response;
import model.Comment;
import model.Post;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Arrays;
import java.util.List;

import static core.CustomApiClient.doGetResonse;

@Execution(ExecutionMode.CONCURRENT)
public class GetRequestPostsById extends BaseTest {

    @Test
    public void getAllPostsTest() {
        int numberOfPostExpected = 100;
        String requestAddress = "/posts";

        Response response = doGetResonse(BASE_URI + requestAddress);
        response.then().assertThat().statusCode(HttpStatus.SC_OK);

        List<Post> postList = Arrays.asList(response.getBody().as(Post[].class));
        int numberOfPostActual = postList.size();

        Assertions.assertEquals(numberOfPostExpected, numberOfPostActual);
    }

    @Test
    public void getPostByIdTest() {

        int postIdForTest = 2;

        Post expectedPost = new Post();
        expectedPost.setUserId(1);
        expectedPost.setId(2);
        expectedPost.setTitle("qui est esse");
        expectedPost.setBody("est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla");

        String requestAddress = String.format("/posts/%d", postIdForTest);

        Response response = doGetResonse(BASE_URI + requestAddress);
        response.then().assertThat().statusCode(HttpStatus.SC_OK);

        Post actualPost = response.getBody().as(Post.class);

        Assertions.assertEquals(expectedPost, actualPost);
    }

    @Test
    public void getAllCommentsForPost() {

        int postIdForTest = 2;
        int numberOfCommentsExpected = 5;

        String requestAddress = String.format("/posts/%d/comments", postIdForTest);

        Response response = doGetResonse(BASE_URI + requestAddress);
        response.then().assertThat().statusCode(HttpStatus.SC_OK);

        List<Comment> commentsList = Arrays.asList(response.getBody().as(Comment[].class));
        int numberOfPostActual = commentsList.size();

        Assertions.assertEquals(numberOfCommentsExpected, numberOfPostActual);
    }

    @Test
    public void getCommentsByPostIdTest() {
        int postIdForTest = 2;
        int numberOfCommentsExpected = 5;

        String requestAddress = String.format("/comments?postId=%s", postIdForTest);

        Response response = doGetResonse(BASE_URI + requestAddress);
        response.then().assertThat().statusCode(HttpStatus.SC_OK);

        List<Comment> commentsList = Arrays.asList(response.getBody().as(Comment[].class));
        int numberOfPostActual = commentsList.size();

        Assertions.assertEquals(numberOfCommentsExpected, numberOfPostActual);
    }
}
