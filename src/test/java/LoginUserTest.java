import org.junit.Test;

import io.qameta.allure.junit4.DisplayName;

public class LoginUserTest {
    @Test
    @DisplayName("User can login")
    public void canLogin() {
        String email = AuthUserDTO.createEmail();
        AuthUserDTO authUserDTO = new AuthUserDTO(email, "Victor", "password");
        AuthUtils.createUser(authUserDTO);
        AuthUtils.loginUser(authUserDTO).then().statusCode(200);
        AuthUtils.removeUser(authUserDTO);
    }

    @Test
    @DisplayName("User cannot login with wrong password")
    public void cannotLoginWithWrongPassword() {
        String email = AuthUserDTO.createEmail();
        AuthUserDTO authUserDTO = new AuthUserDTO(email, "Victor", "password");
        AuthUtils.createUser(authUserDTO);
        authUserDTO.setPassword("wrongpassword");
        AuthUtils.loginUser(authUserDTO).then().statusCode(401);
        authUserDTO.setPassword("password");
        AuthUtils.removeUser(authUserDTO);
    }
}
