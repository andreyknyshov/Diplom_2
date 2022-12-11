import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.qameta.allure.junit4.DisplayName;

public class CreateOrderTest {
    private String accessToken;
    private AuthUserDTO authUserDTO;

    @Before
    public void setUp() {
        String email = AuthUserDTO.createEmail();
        authUserDTO = new AuthUserDTO(email, "Victor", "password");
        AuthUtils.createUser(authUserDTO);
        AuthResponse authResponse = AuthUtils.loginUser(authUserDTO).body().as(AuthResponse.class);
        accessToken = authResponse.getAccessToken();
    }

    @Test
    @DisplayName("One can create order when authenticated")
    public void canCreateOrderWhenAuthenticated() {
        List<String> ingredientsCodes = OrderUtils.getIngredientsCodes();
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(List.of(ingredientsCodes.get(0)));
        OrderUtils.createOrder(createOrderDTO, accessToken).then().statusCode(200);
    }

    @Test
    @DisplayName("One can create order when not authenticated")
    public void canCreateOrderWhenNotAuthenticated() {
        List<String> ingredientsCodes = OrderUtils.getIngredientsCodes();
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(List.of(ingredientsCodes.get(0)));
        OrderUtils.createOrder(createOrderDTO, "").then().statusCode(200);
    }

    @Test
    @DisplayName("One cannot create an order without ingredients when authenticated")
    public void cannotCreateOrderWhenAuthenticatedWithoutIngredients() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(List.of());
        OrderUtils.createOrder(createOrderDTO, accessToken).then().statusCode(400);
    }

    @Test
    @DisplayName("One cannot create an order with wrong ingredients when authenticated ")
    public void cannotCreateOrderWhenAuthenticatedWithWrongIngredients() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(List.of("123"));
        OrderUtils.createOrder(createOrderDTO, accessToken).then().statusCode(500);
    }

    @After
    public void tearDown() {
        AuthUtils.removeUser(authUserDTO);
    }
}
