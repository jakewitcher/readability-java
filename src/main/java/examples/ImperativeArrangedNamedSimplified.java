package examples;

import models.GroceryList;
import models.Item;
import models.Meal;

import java.util.*;

public class ImperativeArrangedNamedSimplified implements GroceryListPreparer {
    private SortedMap<String, Item> itemAccumulators;

    public GroceryList prepare(String description, List<Meal> meals) {
        itemAccumulators = new TreeMap<>();
        var items = sumQuantityOfItemsWithMatchingDescription(meals);

        return new GroceryList(description, items);
    }

    private List<Item> sumQuantityOfItemsWithMatchingDescription(List<Meal> meals) {
        for (var meal : meals) {
            for (var item : meal.getItems()) {
                accumulateItemQuantityIfPresent(item);
                putNewItemAccumulatorIfAbsent(item);
            }
        }

        return new ArrayList<>(itemAccumulators.values());
    }

    private void accumulateItemQuantityIfPresent(Item item) {
        itemAccumulators.computeIfPresent(item.getDescription(), (key, accumulatorItem) ->
                new Item(
                        accumulatorItem.getDescription(),
                        accumulatorItem.getQuantity() + item.getQuantity(),
                        accumulatorItem.getUnit()
                )
        );
    }

    private void putNewItemAccumulatorIfAbsent(Item item) {
        itemAccumulators.computeIfAbsent(item.getDescription(), key ->
                new Item(
                        item.getDescription(),
                        item.getQuantity(),
                        item.getUnit()
                )
        );
    }
}