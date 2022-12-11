import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;

public class GetOrdersTest {

    @Test
    @DisplayName("User can get personal orders when authenticated")
    public void canGetPersonalOrdersWhenAuthenticated() {
        String email = AuthUserDTO.createEmail();
        AuthUserDTO authUserDTO = new AuthUserDTO(email, "Victor", "password");
        AuthUtils.createUser(authUserDTO);
        AuthResponse authResponse = AuthUtils.loginUser(authUserDTO).body().as(AuthResponse.class);
        String accessToken = authResponse.getAccessToken();

        List<String> ingredientsCodes = OrderUtils.getIngredientsCodes();
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(List.of(ingredientsCodes.get(0)));
        OrderUtils.createOrder(createOrderDTO, accessToken);

        Response response = OrderUtils.getOrders(accessToken);
        response.then().statusCode(200);
        OrdersResponse ordersResponse = response.body().as(OrdersResponse.class);
        Assert.assertEquals(1, ordersResponse.getOrders().size());
        AuthUtils.removeUser(authUserDTO);
    }

    @Test
    @DisplayName("User cannot get personal orders when not authenticated")
    public void cannotGetPersonalOrdersWhenNotAuthenticated() {
        OrderUtils.getOrders("").then().statusCode(401);
    }

    @Test
    @DisplayName("One can get all orders when not authenticated")
    public void canGetAllOrdersWhenNotAuthenticated() {
        OrderUtils.getAllOrders().then().statusCode(200);
    }
}
