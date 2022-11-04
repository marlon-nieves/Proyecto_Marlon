import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class RafaelReto2 {
    @Test
    public void helloTestingWorld(){
        System.out.printf("Hello Testing World");
    }

    @BeforeEach
    public void setup(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="/api";
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }

    @Test
    public void LoginTest(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("login")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public  void GetListUser(){
        when().get("https://reqres.in/api/users?page=2");

    }

    @Test
    public void deleteUser(){
        when().delete("https://reqres.in/api/users/2");
    }

    @Test
    public void UpdateUser(){
        given()

                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"Luis Carlos\",\n" +
                        "    \"job\": \"QA Automation \"\n" +
                        "}")
                .put("https://reqres.in/api/users/2");

    }
    @Test
    public  void patchUseTest(){
        String nameUpdate= given().
                when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .patch("user/2").
                then().
                statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().get("name");
    }

}

