package simple;

import models.Item;
import models.Meal;
import org.apache.commons.lang3.RandomUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
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

    public Mono<String> getItemDescription(Mono<Item> item) {
        return item
                .map(Item::getDescription)
                .onErrorReturn("something has gone terribly wrong");
    }

    public Flux<String> getCouponCodesForValidItems(Flux<Item> items) {
        return items
                .map(Item::getUpc)
                .flatMap(this::getCouponCodeByUpcIfExists)
                .onErrorContinue(this::logCouponCodeError)
                .distinct();
    }

    private void logCouponCodeError(Throwable ex, Object obj) {
        System.out.println(ex.getMessage());
    }

    private Mono<String> getCouponCodeByUpcIfExists(String upc) {
        if (RandomUtils.nextInt(1, 11) < 8) {
            return Mono.just("coupon code");
        }

        return Mono.empty();
    }
}
