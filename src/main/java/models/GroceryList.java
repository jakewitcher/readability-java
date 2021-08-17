package models;

import java.util.List;
import java.util.Objects;

public class GroceryList {
    private final String description;
    private final List<Item> items;

    public GroceryList(String description, List<Item> items) {
        this.description = description;
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "GroceryList{" +
                "description='" + description + '\'' +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroceryList that = (GroceryList) o;
        return Objects.equals(description, that.description) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, items);
    }
}
