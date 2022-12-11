import org.junit.Test;

import io.qameta.allure.junit4.DisplayName;

public class UpdateUserDataTest {
    @Test
    @DisplayName("User can update username when authenticated")
    public void canUpdateUserNameDataWhenAuthenticated() {
        String email = AuthUserDTO.createEmail();
        AuthUserDTO authUserDTO = new AuthUserDTO(email, "Victor", "password");
        AuthUtils.createUser(authUserDTO);
        AuthResponse authResponse = AuthUtils.loginUser(authUserDTO).body().as(AuthResponse.class);
        String accessToken = authResponse.getAccessToken();
        authUserDTO.setName("12345678");
        AuthUtils.updateUser(authUserDTO, accessToken).then().statusCode(200);
        AuthUtils.removeUser(authUserDTO);
    }

    @Test
    @DisplayName("User can update user email when authenticated")
    public void canUpdateUserEmailDataWhenAuthenticated() {
        String email = AuthUserDTO.createEmail();
        AuthUserDTO authUserDTO = new AuthUserDTO(email, "Victor", "password");
        AuthUtils.createUser(authUserDTO);
        AuthResponse authResponse = AuthUtils.loginUser(authUserDTO).body().as(AuthResponse.class);
        String accessToken = authResponse.getAccessToken();
        email = AuthUserDTO.createEmail();
        authUserDTO.setEmail(email);
        AuthUtils.updateUser(authUserDTO, accessToken).then().statusCode(200);
        AuthUtils.removeUser(authUserDTO);
    }

    @Test
    @DisplayName("User can update password when authenticated")
    public void canUpdateUserPasswordDataWhenAuthenticated() {
        String email = AuthUserDTO.createEmail();
        AuthUserDTO authUserDTO = new AuthUserDTO(email, "Victor", "password");
        AuthUtils.createUser(authUserDTO);
        AuthResponse authResponse = AuthUtils.loginUser(authUserDTO).body().as(AuthResponse.class);
        String accessToken = authResponse.getAccessToken();
        authUserDTO.setPassword("12345678");
        AuthUtils.updateUser(authUserDTO, accessToken).then().statusCode(200);
        AuthUtils.removeUser(authUserDTO);
    }

    @Test
    @DisplayName("User cannot update user data email when not authenticated")
    public void cannotUpdateUserDataWhenNotAuthenticated() {
        String email = AuthUserDTO.createEmail();
        AuthUserDTO authUserDTO = new AuthUserDTO(email, "Victor", "password");
        AuthUtils.createUser(authUserDTO);
        authUserDTO.setName("12345678");
        AuthUtils.updateUser(authUserDTO, "").then().statusCode(401);
        AuthUtils.removeUser(authUserDTO);
    }
}
