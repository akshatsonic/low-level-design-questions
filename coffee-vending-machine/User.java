import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private List<Order> orders;

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public int getUserId() {
        return userId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public User(int userId) {
        this.userId = userId;
        this.orders = new ArrayList<>();
    }
}
