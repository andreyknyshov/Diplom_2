import java.util.List;

public class OrdersResponse {
    private List<Order> orders;

    public OrdersResponse() {
    }

    public OrdersResponse(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public static class Order {
        private String _id;

        public Order() {
        }

        public Order(String _id) {
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
