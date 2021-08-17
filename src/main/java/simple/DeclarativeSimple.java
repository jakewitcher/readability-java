package simple;

import models.Item;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class DeclarativeSimple {
    public Map<String, List<Item>> groupItemsByDescription(List<Item> items) {
        return items.stream()
                .collect(groupingBy(Item::getDescription));
    }
}
