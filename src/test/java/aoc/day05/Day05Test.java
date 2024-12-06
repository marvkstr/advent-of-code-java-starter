package aoc.day05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
class Day05Test {

    final Day05 day = new Day05();

    public static final String input = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13
        
        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
        """;

    @Test
    public void part1() {

        String result = day.part1(input);

        assertEquals("143", result);
    }

    @Test
    public void part2() {

        String result = day.part2(input);

        assertEquals("9", result);
    }

    @Test
    void prepareOrderInstructions() {

        String[] splitInput = input.split("\n\n");
        List<List<Integer>> pageOrderingRules = day.readOrderingRules(splitInput[0]);
        Map<Integer, Set<Integer>> result = day.prepareOrderInstructions(pageOrderingRules);

        var expected = new HashMap<>();
        expected.put(97, Set.of(13, 61, 47, 29, 53, 75));
        expected.put(53, Set.of(29, 13));
        expected.put(75, Set.of(29, 53, 47, 61, 13));
        expected.put(61, Set.of(13, 53, 29));
        expected.put(29, Set.of(13));
        expected.put(47, Set.of(53, 13, 61, 29));

        for (var key : expected.keySet()) {
            assertEquals(expected.get(key), result.get(key));
        }
    }

    @Test
    void findMiddlePageNumber() {

        var numberInput = List.of(75,47,61,53,29);

        var result = day.findMiddlePageNumber(numberInput);

        assertEquals(61, result);
    }

    private static Stream<Arguments> provideOrderingRulesAndValidity() {
        return Stream.of(
            Arguments.of(List.of(75, 47, 61, 53, 29), true),
            Arguments.of(List.of(97, 61, 53, 29, 13), true),
            Arguments.of(List.of(75, 29, 13), true),
            Arguments.of(List.of(75, 97, 47, 61, 53), false),
            Arguments.of(List.of(61, 13, 29), false),
            Arguments.of(List.of(97, 13, 75, 29, 47), false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideOrderingRulesAndValidity")
    void isValidUpdate(List<Integer> updateInstructions, boolean expected) {
        Map<Integer, Set<Integer>> orderingInstructions = new HashMap<>();
        orderingInstructions.put(97, Set.of(13, 61, 47, 29, 53, 75));
        orderingInstructions.put(53, Set.of(29, 13));
        orderingInstructions.put(75, Set.of(29, 53, 47, 61, 13));
        orderingInstructions.put(61, Set.of(13, 53, 29));
        orderingInstructions.put(29, Set.of(13));
        orderingInstructions.put(47, Set.of(53, 13, 61, 29));

        var result = day.isValidUpdate(updateInstructions, orderingInstructions);

        assertEquals(expected, result);
    }

}