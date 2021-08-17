package examples;

import models.GroceryList;
import models.Meal;

import java.util.List;

public interface GroceryListPreparer {
    public GroceryList prepare(String description, List<Meal> meals);
}
