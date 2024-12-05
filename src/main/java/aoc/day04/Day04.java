package aoc.day04;

import aoc.App;
import aoc.Day;
import aoc.input.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 implements Day {

    public static void main(String[] args) {
        App.runPart1ForDay(4);
    }

    public static List<List<String>> extractDiagonals(List<List<String>> original) {
        List<List<String>> diagonals = new ArrayList<>();

        int rowCount = original.size();
        int colCount = original.stream()
            .mapToInt(List::size)
            .max()
            .orElse(0);

        // Diagonalen von oben links nach unten rechts
        for (int d = 0; d < rowCount + colCount - 1; d++) {
            List<String> diagonal = new ArrayList<>();

            for (int row = 0; row < rowCount; row++) {
                int col = d - row;

                // Wenn der Index innerhalb der Grenzen der Zeile und der Spalten liegt
                if (col >= 0 && col < original.get(row)
                    .size()) {
                    diagonal.add(original.get(row)
                        .get(col));
                }
            }

            // Nur nicht-leere Diagonalen hinzufügen
            if (!diagonal.isEmpty()) {
                diagonals.add(diagonal);
            }
        }

        return diagonals;
    }

    @Override
    public String part1(String input) {

        List<List<String>> wordSearch = prepareInputs(input);

        var result = getResult(wordSearch);

        return String.valueOf(result);
    }

    private int getResult(List<List<String>> wordSearch) {
        var result = countXmasInDirection(wordSearch);
        result += countXmasInDirection(pivot(wordSearch));
        result += countXmasInDirection(extractDiagonals(wordSearch));
        result += countXmasInDirection(extractReverseDiagonals(wordSearch));
        return result;
    }

    private int countXmasInDirection(List<List<String>> wordGrid) {
        return wordGrid.stream()
            .mapToInt(line -> {
                var temp = 0;
                temp += countXmas(line);
                temp += countXmas(line.reversed());
                return temp;
            })
            .sum();
    }

    @Override
    public String part2(String input) {
        return "";
    }

    public List<List<String>> prepareInputs(String input) {
        return Utils.splitLines(input)
            .stream()
            .map(line -> Utils.splitLine(line, ""))
            .toList();
    }

    public int countXmas(List<String> wordSearchLine) {
        StringBuilder builder = new StringBuilder();

        wordSearchLine.forEach(builder::append);

        var line = builder.toString();

        Pattern pattern = Pattern.compile("XMAS");
        Matcher matcher = pattern.matcher(line);

        int counter = 0;

        while (matcher.find()) {
            counter++;
        }

        return counter;
    }

    public List<List<String>> pivot(List<List<String>> original) {

        if (original == null || original.isEmpty()) {
            return new ArrayList<>();
        }

        List<List<String>> pivoted = new ArrayList<>();

        int rowCount = original.size();
        int colCount = original.getFirst()
            .size();

        for (int col = 0; col < colCount; col++) {

            List<String> newRow = new ArrayList<>();

            for (int row = 0; row < rowCount; row++) {

                newRow.add(original.get(row)
                    .get(col));

            }

            pivoted.add(newRow);
        }

        return pivoted;
    }

    public List<List<String>> extractReverseDiagonals(List<List<String>> original) {
        List<List<String>> diagonals = new ArrayList<>();
        int rowCount = original.size();
        int colCount = original.get(0).size();

        // Bottom-left to top-right diagonals
        for (int d = 0; d < rowCount + colCount - 1; d++) {
            List<String> diagonal = new ArrayList<>();
            for (int row = 0; row < rowCount; row++) {
                int col = d - (rowCount - 1 - row);
                if (col >= 0 && col < colCount) {
                    diagonal.add(original.get(row).get(col));
                }
            }
            if (!diagonal.isEmpty()) diagonals.add(diagonal);
        }
        return diagonals;
    }
}
