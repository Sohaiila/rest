package Pages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest {
    String uri;
    String endpoint;
    RequestSpecification requestSpecification;

    public PostRequest(String endpoint , String uri) {
        this.endpoint = endpoint;
        this.uri= uri;
        requestSpecification = RestAssured.given().baseUri(uri).contentType(ContentType.JSON);
    }

   public void addBody(String body){
        requestSpecification.body(body);
   }
   public Response send(){
        return requestSpecification.post(endpoint);
   }
}
