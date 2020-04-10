package org.test.generics;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class TestResourceTest {

    @Test
    public void testExtendedEndpoint() {
        given()
                .when().get("/generics")
                .then()
                .statusCode(200);
        ExtendedClass response = get("/generics")
                .getBody()
                .as(ExtendedClass.class);
        assertEquals("myBaseVariable", response.getBaseVariable());
        assertEquals("myExtendedVariable", response.getExtendedVariable());
        assertEquals("myData", response.getData().getDataVariable());
    }
}