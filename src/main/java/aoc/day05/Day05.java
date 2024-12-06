package aoc.day05;

import aoc.App;
import aoc.Day;
import aoc.input.Utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day05 implements Day {

    public static void main(String[] args) {
        App.runPart1ForDay(5);
    }

    @Override
    public String part1(String input) {
        String[] splitInput = input.split("\n\n");
        List<List<Integer>> pageOrderingRules = readOrderingRules(splitInput[0]);
        Map<Integer, Set<Integer>> orderInstructions = prepareOrderInstructions(pageOrderingRules);

        List<List<Integer>> parsedPagesToProduce = preparePagesToProduce(splitInput[1]);

        var result = parsedPagesToProduce.stream()
            .filter(updateInstruction -> isValidUpdate(updateInstruction, orderInstructions))
            .mapToInt(this::findMiddlePageNumber)
            .sum();

        return String.valueOf(result);
    }

    protected List<List<Integer>> readOrderingRules(String splitInput) {
        return Utils.splitLines(splitInput)
            .stream()
            .map(line -> Utils.splitLine(line, "\\|")
                .stream()
                .map(Integer::parseInt)
                .toList())
            .toList();
    }

    protected Map<Integer, Set<Integer>> prepareOrderInstructions(List<List<Integer>> pageOrderingRules) {

        Map<Integer, Set<Integer>> orderInstructions = new HashMap<>();

        for (var rule : pageOrderingRules) {
            var set = new HashSet<Integer>();
            if (orderInstructions.get(rule.get(0)) == null) {
                set.add(rule.get(1));
                orderInstructions.put(rule.get(0), set);
            } else {
                orderInstructions.get(rule.get(0))
                    .add(rule.get(1));
            }
        }

        return orderInstructions;
    }

    protected static List<List<Integer>> preparePagesToProduce(String splitInput) {
        return Utils.splitLines(splitInput)
            .stream()
            .map(line -> Utils.splitLine(line, ",")
                .stream()
                .map(Integer::parseInt)
                .toList())
            .toList();
    }

    protected boolean isValidUpdate(List<Integer> updateInstruction, Map<Integer, Set<Integer>> orderInstructions) {

        for (int i = 0; i < updateInstruction.size(); i++) {
            var current = updateInstruction.get(i);
            var hasToBeBefore = orderInstructions.get(current);

            if (hasToBeBefore == null || i == 0) {
                continue;
            }

            for (var page : hasToBeBefore) {
                if (updateInstruction.subList(0, i).contains(page)) {
                    return false;
                }
            }
        }

        return true;
    }

    protected int findMiddlePageNumber(List<Integer> integers) {
        return integers.get(integers.size() / 2);
    }


    @Override
    public String part2(String input) {
        return "";
    }
}
