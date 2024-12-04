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
        List<List<String>> memoryEntries = prepareInputs(input, regex);

        int result = memoryEntries.stream()
            .mapToInt( entry -> entry.stream().mapToInt(this::parseToIntegers).sum())
            .sum();

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        String regex = "don't\\(\\).*?(do\\(\\)|$)";
        List<List<String>> memoryEntries = cleanInputs(input, regex);

        int result = memoryEntries.stream()
            .mapToInt(this::calculateLineResult)
            .sum();

        return String.valueOf(result);
    }

    public int calculateLineResult(List<String> operations) {
        boolean doOperation = true;
        int lineResult = 0;
        int muls = 0;

        for (String operation : operations) {
            if (operation.equals("don't()")) {
                doOperation = false;
            } else if (operation.equals("do()")) {
                doOperation = true;
            }

            if (doOperation && !(operation.equals("do()"))){
                lineResult += parseToIntegers(operation);
                muls ++;
            }
        }

        System.out.println(muls);
        return lineResult;
    }

    public List<List<String>> prepareInputs(String input, String regex) {
        return Utils.splitLines(input)
            .stream()
            .map(value -> cleanMemory(value, regex))
            .toList();
    }

    public List<List<String>> cleanInputs(String input, String regex) {

        // Remove all unwanted segments between don't() and do() or end of line
        String cleanedInput = input.replaceAll(regex, "");

        // Extract valid operations using the given regex
        return Utils.splitLines(cleanedInput)
            .stream()
            .map(value -> cleanMemory(value, "mul\\(\\d+,\\d+\\)"))
            .toList();
    }

    public List<String> cleanMemory(String input, String regex) {

        Pattern pattern = Pattern.compile(regex);
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
