package Tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class NewTests {
    @Test
    public void get(){
        given().spec(requestSpecification).when().get("/posts/1").then().spec(responseSpecification)
                .log().body().contentType(ContentType.JSON).assertThat().statusCode(200);
    }
    @Test
    public void post(){
        String body = """
                {
                                
                    "title": "foo",
                                
                    "body": "bar",
                                
                    "userId": 1
                                
                  }
                """;
        given().spec(requestSpecification).header("charset",ContentType.MULTIPART).
                body(body).when()
                .post("/posts")
                .then().log().body().statusCode(201);
    }
    @Test
    public void put(){
        String body = """
                 {
                    "id" : 1 ,            
                    "title": "foo",
                                
                    "body": "bar",
                             
                    "userId": 1
                                
                  }
                """;
        given().spec(requestSpecification).header("charset",ContentType.MULTIPART)
                .body(body).when()
                .put("/posts/1")
                .then().spec(responseSpecification).log().body().assertThat().statusCode(200);


    }
    @Test
    public void delete(){
        given().spec(requestSpecification).when().delete("/posts/1").then().spec(responseSpecification).assertThat().statusCode(200);
    }
    private static RequestSpecification requestSpecification;
    @BeforeClass
    public void setUp(){
        requestSpecification = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").
                setContentType(ContentType.JSON).build();

    }
    private static ResponseSpecification responseSpecification;
    @BeforeClass
    public void setUpp(){
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();

    }
}
