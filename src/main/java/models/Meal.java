package models;

import java.net.URI;
import java.util.List;
import java.util.Objects;

public class Meal {
    private final URI recipeUri;
    private final String description;
    private final List<Item> items;

    public Meal(URI recipeUri, String description, List<Item> items) {
        this.recipeUri = recipeUri;
        this.description = description;
        this.items = items;
    }

    public URI getRecipeUri() {
        return recipeUri;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "recipeUri=" + recipeUri +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(recipeUri, meal.recipeUri) && Objects.equals(description, meal.description) && Objects.equals(items, meal.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeUri, description, items);
    }
}
