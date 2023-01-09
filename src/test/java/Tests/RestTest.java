package Tests;


import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


import io.restassured.http.ContentType;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.response.Response;
import org.hamcrest.core.IsEqual;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestTest {
    String body = """
            {
                        
                "title": "foo",
                        
                "body": "bar",
                        
                "userId": 1
                        
              }
            """;


    @Test
    public void example1(){
      when().get("http://api.zippopotam.us/us/90210").then().
              log().body().contentType(ContentType.JSON).assertThat().statusCode(200);

    }
    @Test
    public void example2(){
        when().get("https://jsonplaceholder.typicode.com/posts ").then().log().
                body().contentType(ContentType.JSON).assertThat().statusCode(200);
    }
    @Test
    public void example(){
      given().contentType(ContentType.JSON).header("charset",ContentType.MULTIPART).baseUri("https://jsonplaceholder.typicode.com")
              .body(body).when()
              .post("/posts")
              .then().log().body().statusCode(201);


    }
    @Test
    public void example3(){
        given().get("https://jsonplaceholder.typicode.com/posts ")
                .then().assertThat().body("",hasSize(100)).log();
    }

    @Test
    public void example4(){
        given().get("http://api.zippopotam.us/us/90210")
                .then().body("places",hasSize(1)).log();
    }

    @Test
    public void example5(){
        when().get("http://api.zippopotam.us/us/90210")
                .then().body("places[0].state",equalTo("California"));
    }
    @Test
    public void example6(){
        Response res = given().when().get("http://api.zippopotam.us/us/90210");
        Assert.assertTrue(res.getBody().asString().contains("21"));

    }
    @Test
    public void example7(){
        Response res = given().when().get("http://api.zippopotam.us/us/90210");
        Assert.assertEquals(res.jsonPath().get("country"),"United States");
    }
}
