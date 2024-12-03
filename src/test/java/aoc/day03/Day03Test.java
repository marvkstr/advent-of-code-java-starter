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

        String result = new Day03().part2(input);

        assertEquals("4", result);
    }

    @Test
    public void cleanMemory() {

        List<String> result = new Day03().cleanMemory(input);

        assertEquals(List.of("mul(2,4)", "mul(5,5)", "mul(11,8)", "mul(8,5)"), result);
    }
}
