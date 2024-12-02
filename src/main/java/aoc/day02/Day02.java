package aoc.day02;

import aoc.Day;
import aoc.input.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day02 implements Day {

    @Override
    public String part1(String input) {

        List<List<Integer>> readings = prepareInputs(input);

        long result = readings.stream()
            .filter(Day02::reportIsSafe)
            .count();

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        
        List<List<Integer>> readings = prepareInputs(input);

        long result = readings.stream()
            .filter(Day02::canBeMadeSafe)
            .count();

        return String.valueOf(result);

    }

    @Override
    public List<List<Integer>> prepareInputs(String input) {
        return Utils.splitLines(input).stream()
            .map(Utils::splitLine)
            .toList();
    }

    private static boolean reportIsSafe(List<Integer> levels) {
        return isMonotonous(levels) && isWithinSafeParameters(levels);
    }

    private static boolean isMonotonous(List<Integer> list) {

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(i - 1)) {
                increasing = false;
            } else if (list.get(i) < list.get(i - 1)) {
                decreasing = false;
            }
        }

        return increasing || decreasing;
    }

    private static boolean isWithinSafeParameters(List<Integer> list) {

        for (int i = 1; i < list.size(); i++) {
            int step = Math.abs(list.get(i - 1) - list.get(i));
            if (step > 3 || step == 0) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean canBeMadeSafe(List<Integer> list) {

        for (int i = 0; i < list.size(); i++) {
            List<Integer> tempList = new ArrayList<>(list);
            tempList.remove(i);

            if (reportIsSafe(tempList)) {
                return true;
            }
        }
        return false;
    }
}
