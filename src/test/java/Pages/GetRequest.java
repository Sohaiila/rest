package Pages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {
    String uri;
    String endpoint;
    RequestSpecification requestSpecification;

    public GetRequest(String uri, String endpoint) {
        this.uri = uri;
        this.endpoint=endpoint;
        requestSpecification = RestAssured.given().baseUri(uri).contentType(ContentType.JSON);
    }
    public void addQueryParameters(String key , String value){
        requestSpecification.param(key,value);
    }
    public Response send(){
        return requestSpecification.get(endpoint);
    }

}
