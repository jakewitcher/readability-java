package simple;

import models.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImperativeSimple {
    public Map<String, List<Item>> groupItemsByDescription(List<Item> items) {
        Map<String, List<Item>> groupedItems = new HashMap<>();

        for (var item : items) {
            if (groupedItems.containsKey(item.getDescription())) {
                List<Item> itemGroup = groupedItems.get(item.getDescription());
                itemGroup.add(item);
            } else {
                List<Item> itemGroup = new ArrayList<>();
                itemGroup.add(item);
                groupedItems.put(item.getDescription(), itemGroup);
            }
        }

        return groupedItems;
    }
}
