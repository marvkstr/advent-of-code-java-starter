package aoc.day04;

import aoc.App;
import aoc.Day;
import aoc.input.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 implements Day {

    public static void main(String[] args) {
        App.runPart2ForDay(4);
    }

    @Override
    public String part1(String input) {

        List<List<String>> wordSearch = prepareInputs(input);

        var result = countOccurrencesInAllDirections(wordSearch);

        return String.valueOf(result);
    }

    private int countOccurrencesInAllDirections(List<List<String>> wordSearch) {
        var result = 0;
        result += countOccurrencesInLines(wordSearch);
        result += countOccurrencesInLines(transposeGrid(wordSearch));
        result += countOccurrencesInLines(extractDiagonals(wordSearch));
        result += countOccurrencesInLines(extractReverseDiagonals(wordSearch));
        return result;
    }

    private int countOccurrencesInLines(List<List<String>> wordGrid) {
        return wordGrid.stream()
            .mapToInt(line -> countOccurrencesInLine(line) + countOccurrencesInLine(line.reversed()))
            .sum();
    }

    @Override
    public String part2(String input) {
        List<List<String>> wordSearch = prepareInputs(input);
        List<Pair> centerPieces = findCenterPieces(wordSearch);
        Map<Pair, String> wordGrid = prepareWordGrid(wordSearch);

        return String.valueOf(centerPieces.stream()
            .filter(pair -> isMasCross(wordGrid, pair))
            .count());

    }

    private boolean isMasCross(Map<Pair, String> wordGrid, Pair pair) {

        try {
            var topLeftBottomRight =
                wordGrid.get(new Pair(pair.row - 1, pair.col - 1)) + wordGrid.get(new Pair(pair.row + 1, pair.col + 1));
            var topRightToBottomLeft =
                wordGrid.get(new Pair(pair.row + 1, pair.col - 1)) + wordGrid.get(new Pair(pair.row - 1, pair.col + 1));

            Pattern regex = Pattern.compile("^(SM|MS)$");
            return regex.matcher(topLeftBottomRight)
                .matches() && regex.matcher(topRightToBottomLeft)
                .matches();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds, this is fine");
        }

        return false;
    }

    private List<Pair> findCenterPieces(List<List<String>> input) {
        List<Pair> centerPieces = new ArrayList<>();

        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(row)
                .size(); col++) {
                if (input.get(row)
                    .get(col)
                    .equals("A")) {
                    centerPieces.add(new Pair(row, col));
                }
            }
        }
        return centerPieces;
    }

    private Map<Pair, String> prepareWordGrid(List<List<String>> wordSearch) {

        Map<Pair, String> wordGrid = new HashMap<>();

        for (int row = 0; row < wordSearch.size(); row++) {
            for (int col = 0; col < wordSearch.get(row)
                .size(); col++) {
                wordGrid.put(new Pair(row, col), wordSearch.get(row)
                    .get(col));
            }

        }
        return wordGrid;
    }

    public List<List<String>> prepareInputs(String input) {
        return Utils.splitLines(input)
            .stream()
            .map(line -> Utils.splitLine(line, ""))
            .toList();
    }

    private int countPatternOccurrences(String line, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(line);

        int counter = 0;
        while (matcher.find()) {
            counter++;
        }
        return counter;
    }


    protected int countOccurrencesInLine(List<String> wordSearchLine) {
        String line = String.join("", wordSearchLine);
        return countPatternOccurrences(line, "XMAS");
    }

    protected List<List<String>> transposeGrid(List<List<String>> original) {

        int rowCount = original.size();
        int colCount = original.getFirst()
            .size();

        List<List<String>> transposed = new ArrayList<>();
        for (int col = 0; col < colCount; col++) {
            List<String> newRow = new ArrayList<>();
            for (int row = 0; row < rowCount; row++) {
                newRow.add(original.get(row)
                    .get(col));
            }
            transposed.add(newRow);
        }

        return transposed;
    }

    /**
     * Extract diagonals from the grid (top-left to bottom-right).
     */
    protected List<List<String>> extractDiagonals(List<List<String>> grid) {
        int rowCount = grid.size();
        int colCount = grid.get(0)
            .size();

        List<List<String>> diagonals = new ArrayList<>();
        for (int d = 0; d < rowCount + colCount - 1; d++) {
            List<String> diagonal = new ArrayList<>();
            for (int row = 0; row < rowCount; row++) {
                int col = d - row;
                if (col >= 0 && col < colCount) {
                    diagonal.add(grid.get(row)
                        .get(col));
                }
            }
            if (!diagonal.isEmpty()) {
                diagonals.add(diagonal);
            }
        }
        return diagonals;
    }

    /**
     * Extract reverse diagonals from the grid (bottom-left to top-right).
     */
    protected List<List<String>> extractReverseDiagonals(List<List<String>> grid) {
        int rowCount = grid.size();
        int colCount = grid.get(0)
            .size();

        List<List<String>> reverseDiagonals = new ArrayList<>();
        for (int d = 0; d < rowCount + colCount - 1; d++) {
            List<String> diagonal = new ArrayList<>();
            for (int row = 0; row < rowCount; row++) {
                int col = d - (rowCount - 1 - row);
                if (col >= 0 && col < colCount) {
                    diagonal.add(grid.get(row)
                        .get(col));
                }
            }
            if (!diagonal.isEmpty()) {
                reverseDiagonals.add(diagonal);
            }
        }
        return reverseDiagonals;
    }

    private record Pair(int row, int col) {
    }
}