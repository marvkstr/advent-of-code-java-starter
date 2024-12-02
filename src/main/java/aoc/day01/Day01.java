package aoc.day01;

import aoc.Day;
import aoc.input.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day01 implements Day {

    @Override
    public String part1(String input) {
        List<List<Integer>> separatedLists = prepareInputs(input);

        int result = calculateAbsoluteDistances(separatedLists.get(0), separatedLists.get(1));

        return String.valueOf(result);
    }

    private static int calculateAbsoluteDistances(List<Integer> first, List<Integer> second) {

        return IntStream.range(0, first.size())
            .map(index -> Math.abs(first.get(index) - second.get(index)))
            .sum();
    }

    @Override
    public String part2(String input) {

        List<List<Integer>> separatedLists = prepareInputs(input);

        Map<Integer, Integer> occurences = countOccurrences(separatedLists.get(1));

        int result = calculateSimilarityScore(separatedLists.get(0), occurences);

        return String.valueOf(result);


    }

    private static Map<Integer, Integer> countOccurrences(List<Integer> list) {
        return list.stream()
            .collect(Collectors.toMap(
                number -> number,
                number -> 1,
                Integer::sum
            ));
    }

    private static int calculateSimilarityScore(List<Integer> locationIDs, Map<Integer, Integer> occurences) {

        return locationIDs.stream()
            .mapToInt(x -> x * occurences.getOrDefault(x, 0))
            .sum();
    }

    @Override
    public List<List<Integer>> prepareInputs(String input) {
        List<String> lines = Utils.splitLines(input);

        List<Integer> firsts = new ArrayList<>();
        List<Integer> seconds = new ArrayList<>();

        lines.stream()
            .map(Utils::splitLine)
            .forEach(line -> {
                firsts.add(line.get(0));
                seconds.add(line.get(1));
            });

        return List.of(
            firsts.stream().sorted().toList(),
            seconds.stream().sorted().toList());
    }
}
