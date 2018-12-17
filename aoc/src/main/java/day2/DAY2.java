package main.java.day2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static main.java.common.Util.readfile;

public class DAY2 {
    private static int twiceCounter = 0;
    private static int thriceCounter = 0;

    public static void main(String[] args) {
        final String path = "/Users/laszboga/learning/adventoOfCode/aoc/src/main/java/day2/input_day2.txt";
        final List<String> lines = readfile(path);
        lines.forEach(DAY2::countLettersTwice);
        System.out.println("twiceCounter = " + twiceCounter);
        System.out.println("thriceCounter = " + thriceCounter);
        System.out.println("checksum = " + twiceCounter * thriceCounter);

        hammingDistanceOfOne(lines);
    }

    private static void hammingDistanceOfOne(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = i + 1; j < lines.size() - 1; j++) {
                String otherLine = lines.get(j);
                if (hamming(line, otherLine) == 1) {
                    System.out.println("line = " + line);
                    System.out.println("otherLine = " + otherLine);
                }

            }

        }
    }

    private static int hamming(String line, String otherLine) {
        if (line.length() != otherLine.length()) {
            throw new IllegalArgumentException();
        }
        int distance = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != otherLine.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    private static void countLettersTwice(String line) {
        String[] chars = line.split("");

//        final Map<String, Integer> collect = Arrays.stream(chars)
//                .collect(groupingBy(Function.identity()))
//                .entrySet().stream()
//                .collect(toMap(Map.Entry::getKey, e -> e.getValue().size()));
        final List<Integer> collect2 = Arrays.stream(chars)
                .collect(groupingBy(Function.identity()))
                .values().stream().map(List::size)
                .collect(toList());
        if (collect2.contains(2)) {
            twiceCounter++;
        }
        if (collect2.contains(3)) {
            thriceCounter++;
        }
    }

}
