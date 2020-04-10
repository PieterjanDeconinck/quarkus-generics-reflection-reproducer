package org.test.vanilla;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class TestResourceTest {

    @Test
    public void testBaseEndpoint() {
        given()
                .when().get("/vanilla/base")
                .then()
                .statusCode(200);
        BaseClass response = get("/vanilla/base")
                .getBody()
                .as(BaseClass.class);
        assertEquals("myBaseVariable", response.getBaseVariable());
    }

    @Test
    public void testExtendedEndpoint() {
        given()
                .when().get("/vanilla/extended")
                .then()
                .statusCode(200);
        ExtendedClass response = get("/vanilla/extended")
                .getBody()
                .as(ExtendedClass.class);
        assertEquals("myBaseVariable", response.getBaseVariable());
        assertEquals("myExtendedVariable", response.getExtendedVariable());
    }
}