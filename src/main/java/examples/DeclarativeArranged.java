package examples;

import models.GroceryList;
import models.Item;
import models.Meal;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DeclarativeArranged implements GroceryListPreparer {
    public GroceryList prepare(String description, List<Meal> meals) {
        var items = createItemMap(meals)
                .values().stream()
                .map(this::createItemListFromMapValues);

        var sortedItems = items
                .sorted(Comparator.comparing(Item::getDescription))
                .collect(Collectors.toList());

        return new GroceryList(description, sortedItems);
    }

    private Map<String, List<Item>> createItemMap(List<Meal> meals) {
        return meals.stream()
                .flatMap(meal -> meal.getItems().stream())
                .collect(groupingBy(Item::getDescription));
    }

    private Item createItemListFromMapValues(List<Item> items) {
        return items.stream()
                .reduce(this::combineItems)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Item combineItems(Item item1, Item item2) {
        return new Item(item1.getDescription(),
                item1.getQuantity() + item2.getQuantity(),
                item1.getUnit());
    }
}
