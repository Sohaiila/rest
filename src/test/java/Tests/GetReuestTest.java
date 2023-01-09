package Tests;

import Pages.DeleteRequest;
import Pages.GetRequest;
import Pages.PostRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GetReuestTest {
    String requestBody;

    @Test
    public void getUser() {
        GetRequest getRequest = new GetRequest("https://reqres.in/api/", "users/2");
        Response response = getRequest.send();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void getWrongUser() {
        GetRequest getRequest = new GetRequest("https://reqres.in/api/", "users/a");
        Response response = getRequest.send();
        assertEquals(200, response.statusCode());
    }

    @Test
    public void createUser() {
        PostRequest postRequest = new PostRequest("", "");
         postRequest.addBody(requestBody);
        Response response = postRequest.send();
    }

    @Test
    public void deleteUser() {
        DeleteRequest deleteRequest = new DeleteRequest("https://reqres.in/api/", "users/2");
        Response response = deleteRequest.send();

    }
}