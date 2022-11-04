import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class TestCrudApis2 {
    @Test
    public void GetListUnknown() {
        RestAssured.when().get("https://reqres.in/api/unknown").
                then().log().all();
    }

    @Test
    public void PostLoginUnknown() {
        String response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .extract().asString();

        System.out.println("La respuesta es:\n" + response);
    }

    @Test
    public void DeleteUnknown() {
        RestAssured.when().delete("https://reqres.in/api/users/2").then().log().all();
    }

    @Test
    public void PutUpdateUnknown() {
        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"JOSE ESAU LOPEZ\",\n" +
                        "    \"job\": \"QA Automation\"\n" +
                        "}")
                .put("https://reqres.in/api/users/7")
                .then()
                .log().all()
                .extract().asString();
    }
}


