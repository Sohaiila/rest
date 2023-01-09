package Pages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {
    String uri;
    String endpoint;
    RequestSpecification requestSpecification;

    public DeleteRequest(String endpoint, String uri) {
        this.endpoint = endpoint;
        this.uri = uri;
        requestSpecification = RestAssured.given().baseUri(uri).contentType(ContentType.JSON);
    }
    public Response send() {
        return requestSpecification.delete(endpoint);
    }
}