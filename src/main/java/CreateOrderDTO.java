import java.util.List;

public class CreateOrderDTO {
    private List<String> ingredients;

    public CreateOrderDTO(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public CreateOrderDTO() {
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
