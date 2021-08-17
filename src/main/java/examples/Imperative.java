package examples;

import models.GroceryList;
import models.Item;
import models.Meal;

import java.util.*;

public class Imperative implements GroceryListPreparer {
    public GroceryList prepare(String description, List<Meal> meals) {
        Map<String, List<Item>> itemsMap = new HashMap<>();
        for (Meal meal : meals) {
            for (Item item : meal.getItems()) {
                if (itemsMap.containsKey(item.getDescription())) {
                    List<Item> current = itemsMap.get(item.getDescription());
                    current.add(item);
                } else {
                    List<Item> items = new ArrayList<>();
                    items.add(item);
                    itemsMap.put(item.getDescription(), items);
                }
            }
        }
        List<Item> groceryListItems = new ArrayList<>();
        for (List<Item> items : itemsMap.values()) {
            Item groceryListItem = null;
            for (Item item : items) {
                if (groceryListItem == null) {
                    groceryListItem = item;
                } else {
                    groceryListItem = new Item(
                            groceryListItem.getDescription(),
                            groceryListItem.getQuantity() + item.getQuantity(),
                            groceryListItem.getUnit());
                }
            }
            groceryListItems.add(groceryListItem);
        }
        groceryListItems.sort(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.getDescription().compareTo(item2.getDescription());
            }
        });
        return new GroceryList(description, groceryListItems);
    }
}
