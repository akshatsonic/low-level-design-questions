package coffee;

import java.util.List;

public class Latte extends Coffee{
    public Latte(List<RecipeItem> recipe) {
        super(recipe, CoffeeType.LATTE);
    }
}
