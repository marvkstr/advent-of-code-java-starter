package aoc.day02;

import aoc.day01.Day01;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    public final String input = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
        """;

    @Test
    public void part1() {

        String result = new Day02().part1(input);

        assertEquals("2", result);
    }

    @Test
    public void part2() {

        String result = new Day02().part2(input);

        assertEquals("4", result);
    }

    @Test
    public void testPrepareInputs() {

        List<List<Integer>> result = new Day02().prepareInputs(input);

        assertArrayEquals(
            Arrays.asList(7, 6, 4, 2, 1).toArray(),
            result.get(0).toArray());

        assertArrayEquals(
            Arrays.asList(1, 2, 7, 8, 9).toArray(),
            result.get(1).toArray());

    }
}
