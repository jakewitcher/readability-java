package simple;

import models.Item;
import models.Meal;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SimplifiedSimple {
    public int findLargestSumOfFiveConsecutiveInts1(List<Integer> numbers) {
        var maxSum = 0;
        var currentSum = 0;

        for (var i = 0; i < numbers.size() - 5; i++) {
            for (var j = i; j < i+5; j++) {
                currentSum += numbers.get(j);
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            currentSum = 0;
        }

        return maxSum;
    }

    public int findLargestSumOfFiveConsecutiveInts2(List<Integer> numbers) {
        var maxSum = 0;
        var currentSum = 0;

        if (Objects.nonNull(numbers) && numbers.size() >= 5) {
            for (var i = 0; i < numbers.size() - 5; i++) {
                for (var j = i; j < i+5; j++) {
                    var currentInt = numbers.get(j);
                    if (Objects.isNull(currentInt) || currentInt < 0) {
                        throw new IllegalArgumentException("integer cannot be null or negative");
                    }
                    currentSum += currentInt;
                }

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
                currentSum = 0;
            }
        } else {
            throw new IllegalArgumentException("list cannot be null or have fewer than 5 elements");
        }

        return maxSum;
    }

    public int findLargestSumOfFiveConsecutiveInts3(List<Integer> xs) {
        int c, m = 0;

        c = xs.get(0) + xs.get(1) + xs.get(2) + xs.get(3) + xs.get(4);
        m = c;

        for (var i = 5; i < xs.size(); i++) {
            int n = c - xs.get(i - 5) + xs.get(i);
            if (n > m) {
                m = n;
            }
            c = n;
        }
        return m;
    }

    public void printDescriptions1(List<Meal> meals) {
        if (Objects.nonNull(meals)) {
            if (meals.isEmpty()) {
                System.out.println("list was empty");
            } else {
                for (var meal : meals) {
                    System.out.println(meal.getDescription());
                }
            }
        } else {
            System.out.println("list was null");
        }
    }

    public void printDescriptions2(List<Meal> meals) {
        if (Objects.isNull(meals)) {
            System.out.println("list was null");
            return;
        }

        if (meals.isEmpty()) {
            System.out.println("list was empty");
            return;
        }

        for (var meal : meals) {
            System.out.println(meal.getDescription());
        }
    }

    public void printMealDescriptions3(List<Meal> meals) {
        var maybeMealDescriptions = Optional.ofNullable(meals)
                .filter(CollectionUtils::isNotEmpty)
                .map(ms -> ms.stream().map(Meal::getDescription));

        maybeMealDescriptions.ifPresentOrElse(
                        ms -> ms.forEach(System.out::println),
                        () -> System.out.println("list was null or empty"));
    }

    public void printFirstItemDescription1(Meal meal) {
        if (Objects.nonNull(meal) && CollectionUtils.isNotEmpty(meal.getItems())) {
            var firstItem = meal.getItems().get(0);
            if (Objects.nonNull(firstItem) && Objects.nonNull(firstItem.getDescription())) {
                System.out.println(firstItem.getDescription());
            }
        }
    }

    public void printFirstItemDescription2(Meal meal) {
        Optional.ofNullable(meal)
                .map(Meal::getItems)
                .flatMap(items -> items.stream().findFirst())
                .map(Item::getDescription)
                .ifPresent(System.out::println);
    }
}
