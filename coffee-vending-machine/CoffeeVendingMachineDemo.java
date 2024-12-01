import coffee.CoffeeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeVendingMachineDemo {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        List<MenuItem> menu = new ArrayList<>();
        Map<String, Integer> ingredientInventory = new HashMap<>();

        users.add(new User(1));
        users.add(new User(2));
        users.add(new User(3));

        ingredientInventory.put("Espresso Beans", 2);
        ingredientInventory.put("Milk", 2);
        ingredientInventory.put("Water", 2);
        ingredientInventory.put("Foam", 0);

        menu.add(new MenuItem(CoffeeType.ESPRESSO, 2.0));
        menu.add(new MenuItem(CoffeeType.CAPPUCCINO, 3.0));
        menu.add(new MenuItem(CoffeeType.LATTE, 4.0));

        CoffeeVendingMachine coffeeVendingMachine = new CoffeeVendingMachine(users, ingredientInventory, menu);
        coffeeVendingMachine.menu();

        coffeeVendingMachine.orderCoffee(1, CoffeeType.ESPRESSO, 2.0);
        coffeeVendingMachine.orderCoffee(2, CoffeeType.CAPPUCCINO, 5.0);

//        coffeeVendingMachine.orderCoffee(3, CoffeeType.LATTE, 4.0);
        coffeeVendingMachine.listOrders(2);
        coffeeVendingMachine.orderCoffee(1, CoffeeType.ESPRESSO, 5.0);
    }
}
