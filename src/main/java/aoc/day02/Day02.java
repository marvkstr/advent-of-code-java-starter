package aoc.day02;

import aoc.Day;
import aoc.input.Utils;

import java.util.List;

public class Day02 implements Day {

    @Override
    public String part1(String input) {

        var readings = prepareInputs(input);

        int result = readings.stream()
            .mapToInt(reading -> reportIsSafe(reading) ? 1 : 0)
            .sum();

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        var readings = prepareInputs(input);

        int result = readings.stream()
            .mapToInt(reading -> reportIsSafe(reading) ? 1 : 0)
            .sum();

        return String.valueOf(result);

    }

    @Override
    public List<List<Integer>> prepareInputs(String input) {
        List<String> lines = Utils.splitLines(input);

        return lines.stream()
            .map(Utils::splitLine)
            .toList();
    }

    private static boolean reportIsSafe(List<Integer> levels) {
        return isMonotonous(levels) && isWithinSafeParameters(levels);
    }

    private static boolean isMonotonous(List<Integer> list) {

        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(i - 1)) {
                    isIncreasing = false;
            } else if (list.get(i) < list.get(i - 1)) {
                    isDecreasing = false;
            }
        }

        return isIncreasing || isDecreasing;
    }

    private static boolean isWithinSafeParameters(List<Integer> list) {

        boolean isInSafeParameters = true;

        for (int i = 1; i < list.size(); i++) {
            int step = Math.abs(list.get(i - 1) - list.get(i));
            if (step > 3 || step == 0) {
                return false;
            }
        }

        return isInSafeParameters;
    }
}
