package examples;

import models.GroceryList;
import models.Item;
import models.Meal;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Declarative implements GroceryListPreparer {
    public GroceryList prepare(String description, List<Meal> meals) {
        var items = meals.stream().flatMap(meal -> meal.getItems().stream()).collect(
                groupingBy(Item::getDescription)).values().stream().map(itemList -> itemList.stream()
                .reduce((item1, item2) -> new Item(item1.getDescription(),
                        item1.getQuantity() + item2.getQuantity(), item1.getUnit()))
                .orElseThrow(IllegalArgumentException::new)).sorted(Comparator.comparing(Item::getDescription))
                .collect(Collectors.toList());

        return new GroceryList(description, items);
    }
}
