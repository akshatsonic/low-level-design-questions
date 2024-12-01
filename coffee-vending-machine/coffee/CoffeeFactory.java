package coffee;

import java.util.List;

public class CoffeeFactory {

    public static Coffee createCoffee(CoffeeType coffeeType) {
        switch (coffeeType) {
            case ESPRESSO:
                return new Espersso(
                        List.of(
                                new RecipeItem("Espresso Beans", 1),
                                new RecipeItem("Water", 1)
                        )
                );
            case CAPPUCCINO:
                return new Cappucino(
                        List.of(
                                new RecipeItem("Espresso Beans", 1),
                                new RecipeItem("Water", 1),
                                new RecipeItem("Milk", 1)
                        )
                );
            case LATTE:
                return new Latte(
                        List.of(
                                new RecipeItem("Espresso Beans", 1),
                                new RecipeItem("Water", 1),
                                new RecipeItem("Milk", 1),
                                new RecipeItem("Foam", 1)
                        )
                );
            default:
                return null;
        }
    }
}
