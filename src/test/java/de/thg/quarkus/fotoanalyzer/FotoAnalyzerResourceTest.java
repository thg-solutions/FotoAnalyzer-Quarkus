package de.thg.quarkus.fotoanalyzer;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class FotoAnalyzerResourceTest {
    @Test
    void testEndpoint() {
        given()
          .when().get("/")
          .then()
             .statusCode(200)
             .body(is("FotoAnalyzer Quarkus"));
    }

}