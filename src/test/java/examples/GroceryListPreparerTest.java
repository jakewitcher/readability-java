package examples;

import models.GroceryList;
import models.Item;
import models.Meal;
import models.UnitOfMeasurement;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroceryListPreparerTest {
    @Test
    public void TestImperativeGroceryListPreparer() {
        GroceryListPreparer preparer = new Imperative();
        GroceryList expectedGroceryList = getExpectedGroceryList();
        GroceryList actualGroceryList = preparer.prepare("grocery list", getMeals());

        assertEquals(expectedGroceryList, actualGroceryList);
    }

    @Test
    public void TestDeclarativeGroceryListPreparer() {
        GroceryListPreparer preparer = new Declarative();
        GroceryList expectedGroceryList = getExpectedGroceryList();
        GroceryList actualGroceryList = preparer.prepare("grocery list", getMeals());

        assertEquals(expectedGroceryList, actualGroceryList);
    }

    @Test
    public void TestImperativeArrangedGroceryListPreparer() {
        GroceryListPreparer preparer = new ImperativeArranged();
        GroceryList expectedGroceryList = getExpectedGroceryList();
        GroceryList actualGroceryList = preparer.prepare("grocery list", getMeals());

        assertEquals(expectedGroceryList, actualGroceryList);
    }

    @Test
    public void TestDeclarativeArrangedGroceryListPreparer() {
        GroceryListPreparer preparer = new DeclarativeArranged();
        GroceryList expectedGroceryList = getExpectedGroceryList();
        GroceryList actualGroceryList = preparer.prepare("grocery list", getMeals());

        assertEquals(expectedGroceryList, actualGroceryList);
    }

    @Test
    public void TestImperativeArrangedNamedGroceryListPreparer() {
        GroceryListPreparer preparer = new ImperativeArrangedNamed();
        GroceryList expectedGroceryList = getExpectedGroceryList();
        GroceryList actualGroceryList = preparer.prepare("grocery list", getMeals());

        assertEquals(expectedGroceryList, actualGroceryList);
    }

    @Test
    public void TestDeclarativeArrangedNamedGroceryListPreparer() {
        GroceryListPreparer preparer = new DeclarativeArrangedNamed();
        GroceryList expectedGroceryList = getExpectedGroceryList();
        GroceryList actualGroceryList = preparer.prepare("grocery list", getMeals());

        assertEquals(expectedGroceryList, actualGroceryList);
    }

    @Test
    public void TestImperativeArrangedNamedSimplifiedGroceryListPreparer() {
        GroceryListPreparer preparer = new ImperativeArrangedNamedSimplified();
        GroceryList expectedGroceryList = getExpectedGroceryList();
        GroceryList actualGroceryList = preparer.prepare("grocery list", getMeals());

        assertEquals(expectedGroceryList, actualGroceryList);
    }

    @Test
    public void TestDeclarativeArrangedNamedSimplifiedGroceryListPreparer() {
        GroceryListPreparer preparer = new DeclarativeArrangedNamedSimplified();
        GroceryList expectedGroceryList = getExpectedGroceryList();
        GroceryList actualGroceryList = preparer.prepare("grocery list", getMeals());

        assertEquals(expectedGroceryList, actualGroceryList);
    }

    private List<Meal> getMeals() {
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal(URI.create("/test"), "meal 1", getItems()));
        meals.add(new Meal(URI.create("/test"), "meal 2", getItems()));
        meals.add(new Meal(URI.create("/test"), "meal 3", getItems()));

        return meals;
    }

    private List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("onion",2d,  UnitOfMeasurement.Item));
        items.add(new Item("parmesan cheese",10d,  UnitOfMeasurement.Ounces));
        items.add(new Item("pasta",8d,  UnitOfMeasurement.Ounces));
        items.add(new Item("sausage",1d,  UnitOfMeasurement.Pounds));
        items.add(new Item("tomato sauce",12d,  UnitOfMeasurement.Ounces));

        return items;
    }

    private GroceryList getExpectedGroceryList() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("onion",6d,  UnitOfMeasurement.Item));
        items.add(new Item("parmesan cheese",30d,  UnitOfMeasurement.Ounces));
        items.add(new Item("pasta",24d,  UnitOfMeasurement.Ounces));
        items.add(new Item("sausage",3d,  UnitOfMeasurement.Pounds));
        items.add(new Item("tomato sauce",36d,  UnitOfMeasurement.Ounces));

        return new GroceryList("grocery list", items);
    }
}
