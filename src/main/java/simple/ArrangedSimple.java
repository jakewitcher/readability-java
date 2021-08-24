package simple;

import models.GroceryList;
import models.Item;
import models.Meal;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class ArrangedSimple {
    public GroceryList createNewGroceryList1(String description, List<Meal> meals) {
        var items = meals.stream().flatMap(meal -> meal.getItems().stream()).collect(
                        groupingBy(Item::getDescription)).values().stream().map(itemList -> itemList.stream()
                        .reduce((item1, item2) -> new Item(item1.getDescription(),
                                item1.getQuantity() + item2.getQuantity(), item1.getUnit()))
                        .orElseThrow(IllegalArgumentException::new)).sorted(Comparator.comparing(Item::getDescription))
                .collect(Collectors.toList());

        return new GroceryList(description, items);
    }

    public GroceryList createNewGroceryList2(String description, List<Meal> meals) {
        var itemsMap = meals.stream().flatMap(meal -> meal.getItems().stream()).collect(
                        groupingBy(Item::getDescription));

        var accumulatedQtyItemsList = itemsMap.values().stream().map(itemList -> itemList.stream()
                        .reduce((item1, item2) -> new Item(item1.getDescription(),
                                item1.getQuantity() + item2.getQuantity(), item1.getUnit()))
                        .orElseThrow(IllegalArgumentException::new));

        var sortedItemsList = accumulatedQtyItemsList.sorted(Comparator.comparing(Item::getDescription))
                .collect(Collectors.toList());

        return new GroceryList(description, sortedItemsList);
    }

    public GroceryList createNewGroceryList3(String description, List<Meal> meals) {
        var itemsMap = meals.stream()
                .flatMap(meal -> meal.getItems().stream())
                .collect(groupingBy(Item::getDescription));

        var accumulatedQtyItemsList = itemsMap.values().stream()
                .map(itemList -> itemList.stream()
                .reduce(this::combineItems)
                .orElseThrow(IllegalArgumentException::new));

        var sortedItemsList = accumulatedQtyItemsList
                .sorted(Comparator.comparing(Item::getDescription))
                .collect(Collectors.toList());

        return new GroceryList(description, sortedItemsList);
    }

    private Item combineItems(Item item1, Item item2) {
        return new Item(
                item1.getDescription(),
                item1.getQuantity() + item2.getQuantity(),
                item1.getUnit()
        );
    }
}
