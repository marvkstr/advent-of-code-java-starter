package aoc.day01;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    private final String input = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
        """;

    @Test
    public void testPart1() {
        // When
        String result = new Day01().part1(input);

        // Then
        assertEquals("11", result);
    }

    @Test
    public void testPart2() {

        // When
        String result = new Day01().part2(input);

        // Then
        assertEquals("31", result);
    }

    @Test
    public void testPrepareInputs() {
        List<List<Integer>> result = new Day01().prepareInputs(input);

        assertArrayEquals(Arrays.asList(1, 2, 3, 3, 3, 4)
            .toArray(), result.get(0)
            .toArray());
        assertArrayEquals(Arrays.asList(3, 3, 3, 4, 5, 9)
            .toArray(), result.get(1)
            .toArray());

    }
}
