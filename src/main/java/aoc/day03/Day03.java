package aoc.day03;

import aoc.App;
import aoc.Day;
import aoc.input.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements Day {

    public static void main(String[] args) {
        App.runPart2ForDay(3);
    }

    @Override
    public String part1(String input) {
        String regex = "mul\\(\\d+,\\d+\\)";
        List<List<String>> memoryEntries = extractMatchingOperations(input, regex);

        int result = memoryEntries.stream()
            .mapToInt( entry -> entry.stream().mapToInt(this::parseToInteger).sum())
            .sum();

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        String regex = "don't\\(\\).*?(do\\(\\)|$)";
        List<List<String>> memoryEntries = filterValidOperations(input, regex);

        int result = memoryEntries.stream()
            .mapToInt(this::calculateLineResult)
            .sum();

        return String.valueOf(result);
    }

    /**
     * Calculate the result of a line by summing only valid operations.
     */
    public int calculateLineResult(List<String> operations) {
        boolean allowOperation = true;
        int lineResult = 0;

        for (String operation : operations) {
            if (operation.equals("don't()")) {
                allowOperation = false;
            } else if (operation.equals("do()")) {
                allowOperation = true;
            } else if (allowOperation){
                lineResult += parseToInteger(operation);
            }
        }

        return lineResult;
    }

    /**
     * Extract matching operations using a regex from each line.
     */
    public List<List<String>> extractMatchingOperations(String input, String regex) {
        return Utils.splitLines(input)
            .stream()
            .map(line -> extractMatches(line, regex))
            .toList();
    }

    /**
     * Filter out invalid operations based on the removal regex.
     */
    public List<List<String>> filterValidOperations(String input, String removalRegex) {
        String cleanedInput = input.replaceAll(removalRegex, "");

        // Extract valid operations using the given regex
        return Utils.splitLines(cleanedInput)
            .stream()
            .map(line -> extractMatches(line, "mul\\(\\d+,\\d+\\)"))
            .toList();
    }

    /**
     * Extract all matches for the given regex in a string.
     */
    public List<String> extractMatches(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }

        return matches;
    }

    /**
     * Parse a "mul(x, y)" string into the product of x and y.
     */
    public Integer parseToInteger(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        List<Integer> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }

        // Ensure we only work with valid "mul(x, y)" patterns
        if (numbers.size() == 2) {
            return numbers.get(0) * numbers.get(1);
        }

        throw new IllegalArgumentException("Invalid operation format: " + input);
    }
}
