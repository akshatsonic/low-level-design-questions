package coffee;

import java.util.List;

public class Cappucino extends Coffee{
    public Cappucino(List<RecipeItem> recipe) {
        super(recipe, CoffeeType.CAPPUCCINO);
    }
}
