import coffee.CoffeeType;

import java.util.List;

public class MenuItem {
    private CoffeeType coffeeType;
    private double price;

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public double getPrice() {
        return price;
    }

    public MenuItem(CoffeeType coffeeType, double price) {
        this.coffeeType = coffeeType;
        this.price = price;
    }
}
