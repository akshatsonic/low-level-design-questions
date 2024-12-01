package coffee;

import java.util.List;

public class Coffee {
    private List<RecipeItem> recipe;
    private CoffeeType type;

    public CoffeeType getType() {
        return type;
    }

    public List<RecipeItem> getRecipe() {
        return recipe;
    }

    public Coffee(List<RecipeItem> recipe, CoffeeType type) {
        this.recipe = recipe;
        this.type = type;
    }
}
