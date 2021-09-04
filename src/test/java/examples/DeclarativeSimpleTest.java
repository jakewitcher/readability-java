package examples;

import models.Item;
import models.UnitOfMeasurement;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import simple.DeclarativeSimple;

import static java.util.Arrays.asList;

public class DeclarativeSimpleTest {
    @Test
    public void itShouldReturnFluxWithCouponCodes() {
        var testClass = new DeclarativeSimple();
        StepVerifier
                .create(testClass.getCouponCodesForValidItems(getItemsFlux()))
                .expectNext("coupon code")
                .expectComplete()
                .verify();
        ;
    }

    private Flux<Item> getItemsFlux() {
        return Flux.fromIterable(asList(
                new Item("a", 2d, UnitOfMeasurement.Ounces),
                new Item("b", 2d, UnitOfMeasurement.Ounces),
                new Item("c", 2d, UnitOfMeasurement.Ounces),
                new Item("d", 2d, UnitOfMeasurement.Ounces),
                new Item("e", 2d, UnitOfMeasurement.Ounces),
                new Item("f", 2d, UnitOfMeasurement.Ounces),
                new Item("g", 2d, UnitOfMeasurement.Ounces),
                new Item("h", 2d, UnitOfMeasurement.Ounces),
                new Item("i", 2d, UnitOfMeasurement.Ounces)
        ));
    }
}
