import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class TestCrudApis {
    @Test
    public void getTest(){
        RestAssured.when().get("https://regres.in/api/unknown").then().log().all();
    }
    @Test
    public void postTest(){
        String response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body("{\n"+
                        "\"email\": \"sydney@fife\""+
                        "}")
                .post("https://regres.in/api/register")
                .then().log().all()
                .extract().asString();
        System.out.println("La respuesta es:\n" + response);
    }
    @Test
    public void deleteTest(){
        RestAssured.when().delete("https://regres.in/api/user/2").then().log().all();
    }
    @Test
    public void putTest(){
        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("\n"+
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n"+
                        "}")
                .put("https://regres.in/api/users/7")
                .then()
                .log().all()
                .extract().asString();
    }
}
