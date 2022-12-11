import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthUtils {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/auth/";

    @Step("Create user")
    public static Response createUser(AuthUserDTO authUserDTO) {
        return given().header("Content-Type","application/json")
                .body(authUserDTO)
                .post(BASE_URL + "register");
    }

    @Step("Login user")
    public static Response loginUser(AuthUserDTO authUserDTO) {
        return given().header("Content-Type","application/json")
                .body(authUserDTO)
                .post(BASE_URL + "login");
    }

    @Step("Remove user")
    public static Response removeUser(AuthUserDTO authUserDTO) {
        return given().header("Content-Type","application/json")
                .body(authUserDTO)
                .delete(BASE_URL + "user");
    }

    @Step("Update user fields")
    public static Response updateUser(AuthUserDTO authUserDTO, String accessToken) {
        return given().header("Content-Type","application/json")
                .header("Authorization", accessToken)
                .body(authUserDTO)
                .patch(BASE_URL + "user");
    }
}
