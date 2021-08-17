package examples;

import models.GroceryList;
import models.Item;
import models.Meal;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DeclarativeArrangedNamed implements GroceryListPreparer {
    public GroceryList prepare(String description, List<Meal> meals) {
        var items = groupItemsByDescription(meals)
                .values().stream()
                .map(this::sumQuantityOfGroupedItems);

        var sortedItems = items
                .sorted(Comparator.comparing(Item::getDescription))
                .collect(Collectors.toList());

        return new GroceryList(description, sortedItems);
    }

    private Map<String, List<Item>> groupItemsByDescription(List<Meal> meals) {
        return meals.stream()
                .flatMap(meal -> meal.getItems().stream())
                .collect(groupingBy(Item::getDescription));
    }

    private Item sumQuantityOfGroupedItems(List<Item> items) {
        return items.stream()
                .reduce(this::sumQuantity)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Item sumQuantity(Item item1, Item item2) {
        return new Item(item1.getDescription(),
                item1.getQuantity() + item2.getQuantity(),
                item1.getUnit());
    }
}
