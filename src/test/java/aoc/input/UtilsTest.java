package aoc.input;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {

    @Test
    public void testSplittingDay01() {
        String input = "3   4";

        List<String> result = Utils.splitLine(input);

        assertEquals(List.of("3", "4"), result);
    }

    @Test
    public void testSplittingDay02() {
        String input = "7 6 4 2 1";

        List<String> result = Utils.splitLine(input);

        assertEquals(List.of("7", "6", "4", "2", "1"), result);
    }
}
