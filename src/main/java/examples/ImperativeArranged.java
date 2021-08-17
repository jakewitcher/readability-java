package examples;

import models.GroceryList;
import models.Item;
import models.Meal;

import java.util.*;

public class ImperativeArranged implements GroceryListPreparer {
    public GroceryList prepare(String description, List<Meal> meals) {
        var itemMap = createItemMap(meals);
        var itemList = createItemListFromMap(itemMap);
        sortItemList(itemList);

        return new GroceryList(description, itemList);
    }

    private Map<String, List<Item>> createItemMap(List<Meal> meals) {
        Map<String, List<Item>> itemMap = new HashMap<>();

        for (var meal : meals) {
            addMealItemsToMap(itemMap, meal);
        }

        return itemMap;
    }

    private void addMealItemsToMap(Map<String, List<Item>> itemMap, Meal meal) {
        for (var item : meal.getItems()) {
            if (itemMap.containsKey(item.getDescription())) {
                var current = itemMap.get(item.getDescription());
                current.add(item);
            } else {
                List<Item> items = new ArrayList<>();
                items.add(item);
                itemMap.put(item.getDescription(), items);
            }
        }
    }

    private List<Item> createItemListFromMap(Map<String, List<Item>> itemsMap) {
        List<Item> itemList = new ArrayList<>();

        for (var items : itemsMap.values()) {
            Item groceryListItem = null;

            for (var item : items) {
                if (groceryListItem == null) {
                    groceryListItem = item;
                } else {
                    groceryListItem = new Item(
                            groceryListItem.getDescription(),
                            groceryListItem.getQuantity() + item.getQuantity(),
                            groceryListItem.getUnit()
                    );
                }
            }
            itemList.add(groceryListItem);
        }

        return itemList;
    }

    private void sortItemList(List<Item> groceryListItems) {
        groceryListItems.sort(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.getDescription().compareTo(item2.getDescription());
            }
        });
    }
}
