package coffee;

import java.util.List;

public class Espersso extends Coffee{
    public Espersso(List<RecipeItem> recipe) {
        super(recipe, CoffeeType.ESPRESSO);
    }
}
