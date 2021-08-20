package simple;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import models.Item;
import models.Meal;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class DeclarativeSimple {
    public Map<String, List<Item>> groupItemsByDescription(List<Item> items) {
        return items.stream()
                .collect(groupingBy(Item::getDescription));
    }

    public Set<String> collectMealItemDescriptions(List<Meal> meals) {
        return meals.stream()
                .map(Meal::getItems)
                .flatMap(Collection::stream)
                .map(Item::getDescription)
                .collect(Collectors.toSet());
    }

    public Double sumItemQuantityWithDescription(String description, List<Item> items) {
        return items.stream()
                .filter(item -> Objects.equals(description, item.getDescription()))
                .mapToDouble(item -> item.getQuantity())
                .sum();
    }

    public List<Item> getMealItems(Meal meal) {
        return Optional.ofNullable(meal)
                .map(Meal::getItems)
                .orElse(Collections.emptyList());
    }

    public Double sumItemQuantity(List<Item> items) {
        return items.stream()
                .map(Optional::ofNullable)
                .flatMap(Optional::stream)
                .mapToDouble(Item::getQuantity)
                .sum();
    }

    public Mono<String> getItemDescription(Supplier<Mono<Item>> itemSupplier) {
        return itemSupplier.get()
                .map(Item::getDescription)
                .onErrorReturn("something has gone terribly wrong");
    }

    public List<String> getDescriptionsOfLargestQtyItems(List<Meal> itemDescriptions) {
        return Lists.transform(itemDescriptions,
                Functions.compose(this::getItemDescription, this::findLargestQtyItem));
    }

    private Item findLargestQtyItem(Meal meal) {
        return meal.getItems().stream()
                .max(Comparator.comparing(Item::getQuantity))
                .orElseThrow();
    }

    private String getItemDescription(Item item) {
        return item.getDescription();
    }
}
