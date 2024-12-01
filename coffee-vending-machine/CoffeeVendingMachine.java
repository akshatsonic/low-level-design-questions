import coffee.Coffee;
import coffee.CoffeeFactory;
import coffee.CoffeeType;
import coffee.RecipeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.lang.System.exit;

public class CoffeeVendingMachine {
    private NotificationService notificationService;
    private static final int ingredientThreshold = 5;
    private Map<String, Integer> ingredientInventory;
    private List<MenuItem> menu;
    private List<User> users;
    private static Integer orderSeq = 0;
    private List<Order> orders;

    public CoffeeVendingMachine(List<User> users, Map<String, Integer> ingredientInventory, List<MenuItem> menu) {
        this.orders = new ArrayList<>();
        this.notificationService = NotificationService.getInstance();
        this.users = users;
        this.ingredientInventory = ingredientInventory;
        this.menu = menu;
    }

    public void menu() {
        for (MenuItem item : menu) {
            System.out.println(item.getCoffeeType() + " - " + item.getPrice());
        }
    }

    public synchronized Coffee brewCoffee(CoffeeType coffeeType) {
        Coffee coffee = CoffeeFactory.createCoffee(coffeeType);
        assert coffee != null;
        for (RecipeItem recipeItem : coffee.getRecipe()) {
            if (ingredientInventory.get(recipeItem.getIngredient()) == null || ingredientInventory.get(recipeItem.getIngredient()) < recipeItem.getQuantity()) {
                notificationService.sendNotification("Out of stock: " + recipeItem.getIngredient());
                exit(1);
            }
            ingredientInventory.put(recipeItem.getIngredient(), ingredientInventory.get(recipeItem.getIngredient()) - recipeItem.getQuantity());
            CompletableFuture.supplyAsync(() -> {
                if (ingredientInventory.get(recipeItem.getIngredient()) <= ingredientThreshold) {
                    notificationService.sendNotification("Low stock: " + recipeItem.getIngredient());
                }
                return null;
            });
        }
        return coffee;
    }

    public synchronized int orderCoffee(int userId, CoffeeType coffeeType, double amount) {
        Coffee coffee = brewCoffee(coffeeType);
        double coffeePrice = menu.stream().filter(item -> item.getCoffeeType().equals(coffeeType)).findFirst().get().getPrice();
        if (coffee != null) {
            System.out.println("Coffee prepared: " + coffee.getType());
        }
        System.out.println("Initiating Payment");
        Payment payment = new Payment(userId, PaymentStatus.SUCCESS, amount, amount - coffeePrice);

        System.out.println("Payment status: " + payment.getPaymentStatus().name());
        Order order = new Order(++orderSeq, payment, coffeeType);
        User user = users.stream().filter(u -> u.getUserId() == userId).findFirst().get();
        user.addOrder(order);
        System.out.println("Order placed: " + order.getOrderId());
        this.orders.add(order);
        return order.getOrderId();
    }

    public void listOrders(int userId) {
        User user = users.stream().filter(u -> u.getUserId() == userId).findFirst().get();
        System.out.println("Orders for user: " + userId);
        for (Order order : user.getOrders()) {
            System.out.println(order.getOrderId() + " - " + order.getCoffeeType() + ", amount - " + order.getPayment().getAmount() + " change - " + order.getPayment().getChange());
        }
    }

}
