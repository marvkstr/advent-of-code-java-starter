package aoc.input;

import java.util.Arrays;
import java.util.List;

public class Utils {
    
    public static List<String> splitLines(String input) {
        return Arrays.asList(input.split(System.lineSeparator()));
    }

    public static List<String> splitLine(String input) {
        return Arrays.stream(input.trim().split("\\s+"))
            .toList();
    }
}
