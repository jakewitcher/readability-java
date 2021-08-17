package examples;

import models.GroceryList;
import models.Item;
import models.Meal;

import java.util.*;

public class ImperativeArrangedNamed implements GroceryListPreparer {
    public GroceryList prepare(String description, List<Meal> meals) {
        var itemGroups = groupItemsByDescription(meals);
        var groceryListItems = sumQuantityOfGroupedItems(itemGroups);
        sortItemsByDescriptionAscending(groceryListItems);

        return new GroceryList(description, groceryListItems);
    }

    private Map<String, List<Item>> groupItemsByDescription(List<Meal> meals) {
        Map<String, List<Item>> itemGroups = new HashMap<>();

        for (var meal : meals) {
            addMealItemsToItemGroups(itemGroups, meal);
        }

        return itemGroups;
    }

    private void addMealItemsToItemGroups(Map<String, List<Item>> itemGroups, Meal meal) {
        for (var item : meal.getItems()) {
            if (itemGroups.containsKey(item.getDescription())) {
                List<Item> items = itemGroups.get(item.getDescription());
                items.add(item);
            } else {
                List<Item> items = new ArrayList<>();
                items.add(item);
                itemGroups.put(item.getDescription(), items);
            }
        }
    }

    private List<Item> sumQuantityOfGroupedItems(Map<String, List<Item>> itemGroups) {
        List<Item> itemList = new ArrayList<>();

        for (var items : itemGroups.values()) {
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

    private void sortItemsByDescriptionAscending(List<Item> groceryListItems) {
        groceryListItems.sort(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.getDescription().compareTo(item2.getDescription());
            }
        });
    }
}
