package aoc.day04;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {

    final Day04 day = new Day04();

    public static final String input = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
        """;

    public static final String inputSmall = """
        ---SXXMASM
        --AMXMSMSA
        -MXSXMAAM-
        MSAMASMS--
        XMASAMX---
        """;

    @Test
    public void part1() {

        String result = day.part1(input);

        assertEquals("18", result);
    }

    @Test
    public void part2() {

        String result = day.part2(input);

        assertEquals("9", result);
    }

    @Test
    public void prepareInputs() {

        List<List<String>> result = day.prepareInputs(input);

        List<String> expected = List.of("M", "M", "M", "S", "X", "X", "M", "A", "S", "M");

        assertEquals(result.getFirst(), expected);
    }

    @Test
    public void countOccurrencesInLine() {

        int result = day.countOccurrencesInLine(Arrays.stream("XMASAMXAMM".split("")).toList());
        assertEquals(1, result);

        result = day.countOccurrencesInLine(Arrays.stream("XMASAMXAMM".split("")).toList().reversed());
        assertEquals(1, result);
    }

    @Test
    public void transposeGrid() {

        List<List<String>> original = new ArrayList<>();
        original.add(List.of("A", "B", "C"));
        original.add(List.of("D", "E", "F"));
        original.add(List.of("G", "H", "I"));

        List<List<String>> result = day.transposeGrid(original);

        List<List<String>> expected = new ArrayList<>();
        expected.add(List.of("A", "D", "G"));
        expected.add(List.of("B", "E", "H"));
        expected.add(List.of("C", "F", "I"));

        assertEquals(expected, result);
    }

    @Test
    public void extractDiagonals() {

        List<List<String>> result = day.extractDiagonals(day.prepareInputs(inputSmall));

        List<List<String>> expected = new ArrayList<>();
        expected.add(List.of("-"));
        expected.add(List.of("-", "-"));
        expected.add(List.of("-", "-", "-"));
        expected.add(List.of("M", "M", "A", "S").reversed());
        expected.add(List.of("X", "S", "X", "M", "X").reversed());
        expected.add(List.of("M", "A", "S", "X", "X").reversed());
        expected.add(List.of("A", "M", "X", "M", "M").reversed());
        expected.add(List.of("S", "A", "M", "S", "A").reversed());
        expected.add(List.of("A", "S", "A", "M", "S").reversed());
        expected.add(List.of("M", "M", "A", "S", "M").reversed());
        expected.add(List.of("X", "S", "M", "A").reversed());
        expected.add(List.of("-", "-", "-"));
        expected.add(List.of("-", "-"));
        expected.add(List.of("-"));

        assertEquals(expected, result);
    }

    @Test
    public void extractReverseDiagonals() {

        List<List<String>> result = day.extractReverseDiagonals(day.prepareInputs(inputSmall));

        List<List<String>> expected = new ArrayList<>();
        expected.add(List.of("-"));
        expected.add(List.of("-", "-"));
        expected.add(List.of("-", "-", "-"));
        expected.add(List.of("M", "M", "A", "S"));
        expected.add(List.of("X", "S", "X", "M", "X"));
        expected.add(List.of("M", "A", "S", "X", "X"));
        expected.add(List.of("A", "M", "X", "M", "M"));
        expected.add(List.of("S", "A", "M", "S", "A"));
        expected.add(List.of("A", "S", "A", "M", "S"));
        expected.add(List.of("M", "M", "A", "S", "M"));
        expected.add(List.of("X", "S", "M", "A"));
        expected.add(List.of("-", "-", "-"));
        expected.add(List.of("-", "-"));
        expected.add(List.of("-"));

        assertEquals(expected, result);
    }
}