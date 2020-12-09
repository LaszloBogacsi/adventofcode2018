package main.java.twentytwenty.day2

import main.java.common.Util

fun main() {
    // same input for bot parts of the challenge


    // same input for bot parts of the challenge
    val path = "/Users/laszboga/learning/adventoOfCode/aoc/src/main/java/twentytwenty/day2/input_day2_1.txt"
    val lines = Util.readfile(path)
    val day2 = DAY2(lines.map {
        PasswordLine(
                it.substring(0, it.indexOf('-')).toInt(),
                it.substring(it.indexOf('-') + 1, it.indexOfFirst { c -> c == ' '}).toInt(),
                it.substring((it.indexOfFirst { c -> c == ' '} + 1), it.indexOf(':')).single(),
                it.substringAfter(':').trim()
        )
    })
    println(day2.getResult())
    println(day2.getResult2())
}
class PasswordLine(val min: Int, val max: Int, val letter: Char, val password: String )
class DAY2(private val inputs: List<PasswordLine>) {
    fun getResult(): Int {
        var valid = 0
        for (passwordLine in inputs) {

            val numOfCheckChars = passwordLine.password.toCharArray().filter { c -> c == passwordLine.letter }.size
            if (numOfCheckChars >= passwordLine.min && numOfCheckChars <= passwordLine.max) valid++
        }
        return valid;
    }
    fun getResult2(): Int {
        var valid = 0
        for (passwordLine in inputs) {

            val pos1 = passwordLine.password[passwordLine.min - 1]
            val pos2 = passwordLine.password[passwordLine.max - 1]
            val letter = passwordLine.letter
            if ((pos1 != pos2 && (pos1 == letter || pos2 == letter))) valid++
        }
        return valid;
    }
}