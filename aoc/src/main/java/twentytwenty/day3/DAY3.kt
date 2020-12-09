package main.java.twentytwenty.day3

import main.java.common.Util

fun main() {
    // same input for bot parts of the challenge


    // same input for bot parts of the challenge
    val path = "/Users/laszboga/learning/adventoOfCode/aoc/src/main/java/twentytwenty/day3/input_day3_1.txt"
    val lines = Util.readfile(path)
    val moves1 = listOf(Slope(1, 3))
    val moves2 = listOf(
            Slope(1, 1),
            Slope(1, 3),
            Slope(1, 5),
            Slope(1, 7),
            Slope(2, 1)
   )
    val day3 = DAY3(lines)
    println(moves1.map { (down, right) -> day3.getResult(down, right) }.reduce { acc: Int, a:Int -> acc * a  })
    println(moves2.map { (down, right) -> day3.getResult(down, right) }.reduce { acc: Int, a:Int -> acc * a  })

}
data class Slope(val down: Int, val right: Int)

class DAY3(private val lines: List<String>) {
    val lineLength = lines.first().length
    fun getResult(moveDown: Int, moveRight: Int): Int {

        var numberOfTrees = 0
        var position = 0

        for (i in lines.indices step moveDown) {
            if (position >= lineLength) position -= lineLength
            if (lines[i][position] == '#') numberOfTrees++
            position += moveRight
        }
        return numberOfTrees;
    }

}