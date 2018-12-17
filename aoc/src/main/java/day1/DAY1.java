package main.java.day1;

import java.util.ArrayList;
import java.util.List;

import static main.java.common.Util.readfile;

public class DAY1 {
    static int result = 0;
    static List<Integer> frequencies = new ArrayList<>();
    private static int reocurringFrequency = 0;

    public static void main(String[] args) {

        // same input for bot parts of the challenge
        final String path = "/Users/laszboga/learning/adventoOfCode/aoc/src/main/java/day1/input1_1.txt";
        final List<String> lines = readfile(path);
        lines.forEach(DAY1::calculateSumFromLine);
        while(reocurringFrequency == 0 )  {
            lines.forEach(l -> addAndCheckFrequency(calculateSumFromLine(l)));
        }
        System.out.println("reocurringFrequency = " + reocurringFrequency);
    }

    private static void addAndCheckFrequency(int frequency) {
        if (!frequencies.contains(frequency)) {
            frequencies.add(frequency);
        } else {
            System.out.println(frequency);
            reocurringFrequency = frequency;
        }
    }

    private static int calculateSumFromLine(String l) {
        result += Integer.parseInt(l);
        return result;
    }
}
