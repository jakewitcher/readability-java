package examples;

import org.junit.jupiter.api.Test;
import simple.SimplifiedSimple;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimplifiedSimpleTest {
    @Test
    public void itShouldFindLargestSumOfConsecutiveIntegers1() {
        var testClass = new SimplifiedSimple();
        var nums = asList(10,2,7,12,8,3,5,14,9,4,1);

        double actual = testClass.findLargestSumOfFiveConsecutiveInts1(nums);

        assertEquals(42, actual);
    }

    @Test
    public void itShouldFindLargestSumOfConsecutiveIntegers2() {
        var testClass = new SimplifiedSimple();
        var nums = asList(10,2,7,12,8,3,5,14,9,4,1);

        double actual = testClass.findLargestSumOfFiveConsecutiveInts2(nums);

        assertEquals(42, actual);
    }

    @Test
    public void itShouldFindLargestSumOfConsecutiveIntegers3() {
        var testClass = new SimplifiedSimple();
        var nums = asList(10,2,7,12,8,3,5,14,9,4,1);

        double actual = testClass.findLargestSumOfFiveConsecutiveInts3(nums);

        assertEquals(42, actual);
    }
}
