package Tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class Practice2 {
    private static RequestSpecification requestSpecification;

    @BeforeClass
    public void setUp(){
        requestSpecification = new RequestSpecBuilder().setBaseUri("https://reqres.in/api/users?page=2").
                build();

    }
    private static ResponseSpecification responseSpecification;
    @BeforeClass
    public void setUpp(){
        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).
                build();}
    //assertions?????????????????????????????????????????????????????????
    @Test
    public void get(){
        given().spec(requestSpecification).when().get().then().spec(responseSpecification).log().body().
                body("x.data[1].id",equalTo(2));
    }
    @Test
    public void createUser(){
        String body= """ 
                  {
                  "name": "yourName",
                                   "job": "yourJob",
                                   "id": "594",
                                   "createdAt": "2022-12-26T09:24:55.845Z"
                   }
                """;

        given().spec(requestSpecification).when()
                .body(body).when()
                .post()
                .then().log().body().contentType(ContentType.JSON).statusCode(201);

    }

    @Test
    public void updateUser(){
        String body= """
                {
                    "name": "yourfriendName",
                    "job": "yourFriendJob",
                    "updatedAt": "2022-12-26T09:25:28.213Z"
                }
                """;
        given().spec(requestSpecification).when()
                .body(body).when()
                .put()
                .then().log().body().statusCode(201);

    }
    @Test
    public void delete(){
        given().spec(requestSpecification).when().delete().then().assertThat().statusCode(204);
    }

}
