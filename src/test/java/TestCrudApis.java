import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class TestCrudApis {
    @BeforeEach
    public void setup(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="/api";
        RestAssured.filters(new RequestLoggingFilter(),new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }
    @Test
    public void TestPostLogin(){
        given()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .post("login")
                .then().statusCode(HttpStatus.SC_OK);
    }
    @Test
    public void TestGet(){
        RestAssured.when().get("https://reqres.in/api/unknown").
                then()
                .body("page",equalTo(1));
    }
    @Test
    public void TestPutUpdate(){
        given()
                .log().all()
                .body("{\n" +
                        "    \"name\": \"Wilber Perez\",\n" +
                        "    \"job\": \"QA Automation\"\n" +
                        "}")
                .put("https://reqres.in/api/users/7");
    }
    @Test
    public void TestDelete(){
        RestAssured.when().delete("https://reqres.in/api/users/2");
    }
    @Test
    public void patch(){
        String nameUpdate = given().
                when()
                .body("{\n" +
                        "    \"name\": \"Andres\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .patch("user/2").then().statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("name");
        assertThat(nameUpdate,equalTo("Andres"));

    }

}