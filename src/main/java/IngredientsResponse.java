import java.util.List;

public class IngredientsResponse {
    private List<Ingredient> data;

    public IngredientsResponse(List<Ingredient> data) {
        this.data = data;
    }

    public IngredientsResponse() {
    }

    public List<Ingredient> getData() {
        return data;
    }

    public void setData(List<Ingredient> data) {
        this.data = data;
    }

    public static class Ingredient {
        private String _id;

        public Ingredient() {
        }

        public Ingredient(String _id) {
            this._id = _id;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }
    }
}
