package aoc.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    public final String input = """
        xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
        """;

    @Test
    public void part1() {

        String result = new Day03().part1(input);

        assertEquals("161", result);
    }

    @Test
    public void part2() {

        String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";

        String result = new Day03().part2(input);

        assertEquals("48", result);
    }

    @Test
    public void cleanMemory() {

        String regex = "mul\\(\\d+,\\d+\\)";

        List<String> result = new Day03().extractMatches(input, regex);

        assertEquals(List.of("mul(2,4)", "mul(5,5)", "mul(11,8)", "mul(8,5)"), result);
    }

    @Test
    public void calculateLineResult() {

        List<String> input = List.of("mul(2,4)", "don't()", "mul(5,5)", "mul(11,8)", "do()", "mul(8,5))");

        int result = new Day03().calculateLineResult(input);

        assertEquals(48, result);
    }
}
