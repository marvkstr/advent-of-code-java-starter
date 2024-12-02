package aoc.day01;

import aoc.Day;
import aoc.input.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day01 implements Day {

    @Override
    public String part1(String input) {
        List<List<Integer>> separatedLists = prepareInputs(input);

        int result = 0;

        for (int i = 0; i < separatedLists.get(0)
            .size(); i++) {

            result += Math.abs(separatedLists.get(0)
                .get(i) - separatedLists.get(1)
                .get(i));
        }

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {

        List<List<Integer>> separatedLists = prepareInputs(input);

        Map<Integer, Integer> occurences = separatedLists.get(1)
            .stream()
            .collect(Collectors.toMap(
                number -> number,
                number -> 1,
                Integer::sum
            ));

        int result = separatedLists.get(0)
            .stream()
            .mapToInt(x -> x * occurences.getOrDefault(x, 0))
            .sum();

        return String.valueOf(result);


    }

    protected List<List<Integer>> prepareInputs(String input) {
        List<String> lines = Utils.splitLines(input);

        List<Integer> firsts = new ArrayList<>();
        List<Integer> seconds = new ArrayList<>();

        for (String entry : lines) {
            List<Integer> parts = splitLine(entry);
            firsts.add(parts.get(0));
            seconds.add(parts.get(1));
        }

        return Arrays.asList(
            firsts.stream()
                .sorted()
                .toList(),
            seconds.stream()
                .sorted()
                .toList());
    }


    public List<Integer> splitLine(String input) {
        return Arrays.stream(input.split("[ \\t\\r\\f]+"))
            .map(Integer::parseInt)
            .toList();
    }
}
