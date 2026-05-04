package org.lfenergy.compas.validation.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import io.quarkus.test.security.TestSecurity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.lfenergy.compas.validation.utils.FileTestUtils.stringifyFile;

@QuarkusTest
@TestSecurity(user = "test-user", roles = {"SCL_VALIDATION"})
class XPathValidationResourceTest {

    @Test
    void shouldReturnValidResultForXPathValidationWithNoRules() {
        given()
            .multiPart("sclFile", "test.scd", "<SCL/>".getBytes(), "application/xml")
            .multiPart("rulesJson", "[]")
            .multiPart("sclFileExtension", "SCD")
        .when()
            .post("/api/v1/validate")
        .then()
            .statusCode(200)
            .body("valid", equalTo(true))
            .body("errors", empty());
    }

    @Test
    void shouldReturnBadRequestForXPathValidationWithUnknownFileType() {
        given()
            .multiPart("sclFile", "test.xml", "<SCL/>".getBytes(), "application/xml")
            .multiPart("rulesJson", "[]")
            .multiPart("sclFileExtension", "XML")
        .when()
            .post("/api/v1/validate")
        .then()
            .statusCode(400)
            .body("message", containsString("Invalid validation type or file extension"));
    }


    @Test
    void shouldReturnInvalidResultForXPathValidationWithNotContainsRule() throws IOException {

        String jsonRules = stringifyFile("/xpath/not_contains_rule.json");
        String sclFile = stringifyFile("/scl/test_substation.scd");

        given()
            .multiPart("sclFile", "test.scd", sclFile.getBytes(), "application/xml")
            .multiPart("rulesJson", jsonRules)
            .multiPart("sclFileExtension", "SCD")
        .when()
            .post("/api/v1/validate")
        .then()
            .statusCode(200)
            .body("valid", equalTo(false))
            .body("errors", hasSize(1));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "/scl/small.scd",
            "/scl/small.icd",
            "/scl/small.ssd",
            "/scl/medium.scd",
            "/scl/large.scd",
            "/scl/xlarge.scd",
    })
    void shouldReturnValidResultForXPathValidationForVariousFiles(String filePath) throws IOException {

        String jsonRules = stringifyFile("/xpath/25_rules.json");
        String sclFile = stringifyFile(filePath);

        given()
            .multiPart("sclFile", "test.scd", sclFile.getBytes(), "application/xml")
            .multiPart("rulesJson", jsonRules)
            .multiPart("sclFileExtension", "SCD")
        .when()
            .post("/api/v1/validate")
        .then()
            .statusCode(200)
            .body("valid", equalTo(true))
            .body("errors", empty());

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "/scl/small.scd",
            "/scl/medium.scd",
            "/scl/large.scd",
            "/scl/xlarge.scd",
    })
    void shouldReturnAtLeast3ViolationsOfXPathValidationForSCDFiles(String filePath) throws IOException {

        String jsonRules = stringifyFile("/xpath/25_rules_with_some_violated.json");
        String sclFile = stringifyFile(filePath);

        given()
            .multiPart("sclFile", "test.scd", sclFile.getBytes(), "application/xml")
            .multiPart("rulesJson", jsonRules)
            .multiPart("sclFileExtension", "SCD")
        .when()
            .post("/api/v1/validate")
        .then()
            .statusCode(200)
            .body("valid", equalTo(false))
            .body("errors", hasSize(greaterThanOrEqualTo(3)));
    }
}
