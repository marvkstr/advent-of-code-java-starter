package aoc.day03;

import aoc.Day;
import aoc.input.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements Day {
    @Override
    public String part1(String input) {
        List<List<String>> memoryEntries = prepareInputs(input);

        int result = memoryEntries.stream()
            .mapToInt( entry -> entry.stream().mapToInt(this::parseToIntegers).sum())
            .sum();

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        return "";
    }

    public List<List<String>> prepareInputs(String input) {
        return Utils.splitLines(input)
            .stream()
            .map(this::cleanMemory)
            .toList();
    }

    public List<String> cleanMemory(String input) {

        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher matcher = pattern.matcher(input);

        List<String> memory = new ArrayList<>();

        while (matcher.find()) {
            memory.add(matcher.group());
        }

        return memory;
    }

    public Integer parseToIntegers(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        List<Integer> numbers = new ArrayList<>();

        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }

        return numbers.getFirst() * numbers.getLast();
    }
}
