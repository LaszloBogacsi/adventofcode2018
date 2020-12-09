package main.java.twentytwenty.day1

import main.java.common.Util

fun main() {
    // same input for bot parts of the challenge


    // same input for bot parts of the challenge
//    val path = "/Users/laszboga/learning/adventoOfCode/aoc/src/main/java/day1/input1_1.txt"
    val path = "/Users/laszboga/learning/adventoOfCode/aoc/src/main/java/twentytwenty/day1/input_day1_1.txt"
    val lines = Util.readfile(path)
    val day1 = DAY1(lines.map { it.toInt() })
    println(day1.getResult(2020))
    println(day1.getResult2(2020))
}

class DAY1(private val inputs: List<Int>) {
    fun getResult(target: Int): Int {
        val valueSet: MutableSet<Int> = mutableSetOf()

        for (value in inputs) {
            if (valueSet.contains(target - value)) {
                return value * (target - value);
            }
            valueSet.add(value)
        }
        return 0;
    }

    fun getResult2(target: Int): Int {
        for ((i, a) in inputs.withIndex()) {
            for (j in i + 1 until inputs.size - 2) {
                for (k in j + 1 until inputs.size - 1) {
                    if (a + inputs[j] + inputs[k] == target) return a * inputs[j] * inputs[k]

                }
            }
        }
        return 0;

    }
}