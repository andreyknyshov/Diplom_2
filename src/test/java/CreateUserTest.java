
import org.junit.After;
import org.junit.Test;

import io.qameta.allure.junit4.DisplayName;


public class CreateUserTest {

    @Test
    @DisplayName("User can register")
    public void canCreateUniqueUser() {
        String email = AuthUserDTO.createEmail();
        AuthUserDTO authUserDTO = new AuthUserDTO(email, "Victor", "password");
        AuthUtils.createUser(authUserDTO).then().statusCode(200);
        AuthUtils.removeUser(authUserDTO);
    }

    @Test
    @DisplayName("User can not register again")
    public void cannotCreateAlreadyRegisteredUser() {
        String email = AuthUserDTO.createEmail();
        AuthUserDTO authUserDTO = new AuthUserDTO(email, "Victor", "password");
        AuthUtils.createUser(authUserDTO);
        AuthUtils.createUser(authUserDTO).then().statusCode(403);
        AuthUtils.removeUser(authUserDTO);
    }

    @Test
    @DisplayName("User can not register without required fields")
    public void cannotCreateUserWithoutRequiredFields() {
        String email = AuthUserDTO.createEmail();
        AuthUserDTO authUserDTO = new AuthUserDTO(email, "Victor", null);
        AuthUtils.createUser(authUserDTO).then().statusCode(403);
        AuthUtils.removeUser(authUserDTO);
    }
}
