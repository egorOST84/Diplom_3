package site.nomoreparties.stellarburgers.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import site.nomoreparties.stellarburgers.config.AppConfig;
import site.nomoreparties.stellarburgers.pojo.User;


import static io.restassured.RestAssured.given;
import static site.nomoreparties.stellarburgers.specs.RestAssuredSpecs.successDeleteRegisteredUserResponse;
import static site.nomoreparties.stellarburgers.specs.RestAssuredSpecs.successUserResponse;

public class UserApiHelper {
    private static final String API_BASE_PATH = "api/";
    private static final String LOGIN_PATH = "auth/login";
    private static final String REGISTER_PATH = "auth/register";
    private static final String USER_PATH = "auth/user";

    public static String loginUserAndGetToken(String email, String password) {
        RestAssured.baseURI = AppConfig.BASE_URL;
        RestAssured.basePath = API_BASE_PATH;

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(LOGIN_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("accessToken");
    }

    public static void createUser(String name, String email, String password) {
        RestAssured.baseURI = AppConfig.BASE_URL;
        RestAssured.basePath = API_BASE_PATH;

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(REGISTER_PATH)
                .then()
                .spec(successUserResponse(email, name));
    }

    public static void deleteUser(String accessToken) {
        RestAssured.baseURI = AppConfig.BASE_URL;
        RestAssured.basePath = API_BASE_PATH;

        given()
                .header("Authorization", accessToken)
                .delete(USER_PATH)
                .then()
                .spec(successDeleteRegisteredUserResponse());
    }
}