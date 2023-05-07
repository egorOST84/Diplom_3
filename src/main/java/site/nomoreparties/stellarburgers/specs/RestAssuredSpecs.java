package site.nomoreparties.stellarburgers.specs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import site.nomoreparties.stellarburgers.config.AppConfig;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class RestAssuredSpecs {
    public static RequestSpecification requestSpec() {

        RestAssured.baseURI = AppConfig.BASE_URL;
        RestAssured.basePath = "api/";

        return new RequestSpecBuilder()
                .setBaseUri(RestAssured.baseURI)
                .setBasePath(RestAssured.basePath)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }

    public static ResponseSpecification successUserResponse(String email, String name) {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_OK)
                .expectBody("success", is(true))
                .expectBody("user.email", equalTo(email.toLowerCase()))
                .expectBody("user.name", equalTo(name))
                .expectBody("accessToken", notNullValue())
                .expectBody("refreshToken", notNullValue())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification successDeleteRegisteredUserResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_ACCEPTED)
                .expectBody("success", is(true))
                .expectBody("message", equalTo("User successfully removed"))
                .log(LogDetail.ALL)
                .build();
    }

    public static void setUpSpec(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}