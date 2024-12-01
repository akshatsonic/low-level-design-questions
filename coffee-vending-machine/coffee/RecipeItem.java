package coffee;

public class RecipeItem {
    private String ingredient;
    private int quantity;

    public RecipeItem(String ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }
}
