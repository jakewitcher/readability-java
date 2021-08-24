package simple;

import models.Meal;

import java.util.List;
import java.util.Objects;

public class SimplifiedSimple {
    public void printMealDescriptions1(List<Meal> meals) {
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

    public void printMealDescriptions2(List<Meal> meals) {
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
}
