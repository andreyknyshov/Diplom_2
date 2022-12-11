import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.stream.Collectors;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class OrderUtils {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/";

    @Step("Create an order")
    public static Response createOrder(CreateOrderDTO orderDTO, String accessToken) {
        return given().header("Content-Type","application/json")
                .header("Authorization", accessToken)
                .body(orderDTO).post(BASE_URL + "orders");
    }

    @Step("Get ingredients")
    private static IngredientsResponse getIngredients() {
        return given().get(BASE_URL + "ingredients").body().as(IngredientsResponse.class);
    }

    @Step("Get ingredients' codes")
    public static List<String> getIngredientsCodes() {
        IngredientsResponse ingredientsResponse = getIngredients();
        return ingredientsResponse.getData().stream()
                .map(ingredient -> ingredient.get_id())
                .collect(Collectors.toList());
    }

    @Step("Get orders list for user")
    public static Response getOrders(String accessToken) {
        return given().header("Content-Type","application/json")
                .header("Authorization", accessToken)
                .get(BASE_URL + "orders");
    }

    @Step("Get all orders list")
    public static Response getAllOrders() {
        return given().header("Content-Type","application/json")
                .get(BASE_URL + "orders/all");
    }
}
