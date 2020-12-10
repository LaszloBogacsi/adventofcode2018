package main.java.twentytwenty.day4

import main.java.common.Util

fun main() {
//    ---- Passport Processing ----
    val path = "/Users/laszboga/learning/adventoOfCode/aoc/src/main/java/twentytwenty/day4/input_day4_1.txt"
    val lines = Util.readfile(path)
    val passport = makePassport(lines)
    val day4 = DAY4(passport)
    println(day4.getResult())

}

fun makePassport(lines: List<String>): List<Passport> {
    val passportBuilder: StringBuilder = StringBuilder()
    val passports: MutableList<Passport> = mutableListOf()
    var isvalidCounter = 0
    for ((index, line) in lines.withIndex()) {
        var validCounter = 0
        if (line.isEmpty() || index == lines.size-1) {
            if (index == lines.size-1) passportBuilder.append(line)
            val parameterPairs = passportBuilder.toString()
                    .trim()
                    .split(" ")
                    .map {
                        val split = it.split(":")
                        split[0] to split[1]
                    }.toMap()
            passports.add(Passport(
                    byr = parameterPairs.getOrElse("byr", {if (validCounter == 0) validCounter++; null}),
                    iyr = parameterPairs.getOrElse("iyr", {if (validCounter == 0) validCounter++; null}),
                    eyr = parameterPairs.getOrElse("eyr", {if (validCounter == 0) validCounter++; null}),
                    hgt = parameterPairs.getOrElse("hgt", {if (validCounter == 0) validCounter++; null}),
                    hcl = parameterPairs.getOrElse("hcl", {if (validCounter == 0) validCounter++; null}),
                    ecl = parameterPairs.getOrElse("ecl", {if (validCounter == 0) validCounter++; null}),
                    pid = parameterPairs.getOrElse("pid", {if (validCounter == 0) validCounter++; null}),
                    cid = parameterPairs["cid"]


            ))
            passportBuilder.clear()
        } else {
            passportBuilder.append(line).append(" ")
        }
        if (validCounter > 0) isvalidCounter++

    }

    return passports
}

data class Passport(val byr: String?, val iyr: String?,val eyr: String?, val hgt: String?, val hcl: String?, val ecl: String?, val pid: String?, val cid: String? )

class DAY4(private val passports: List<Passport>) {
    val byrIsValid = {byr: String -> byr.length == 4 && byr.toInt() >= 1920 && byr.toInt() <= 2002}
    val iyrIsValid = { iyr: String -> iyr.length == 4 && iyr.toInt() >= 2010 && iyr.toInt() <= 2020 }
    val eyrIsValid = { eyr: String -> eyr.length == 4 && eyr.toInt() >= 2020 && eyr.toInt() <= 2030 }
    private val heightCmRange = Pair(150, 193)
    private val heightInRange = Pair(59, 76)
    private fun hgtIsValid(hgt : String): Boolean {
        if (hgt.endsWith("in") && hgt.length > 2) {
            val height = hgt.substring(0, hgt.length-2).toInt()
            return height >= this.heightInRange.first && height <= this.heightInRange.second
        }
        if (hgt.endsWith("cm") && hgt.length > 2) {
            val height = hgt.substring(0, hgt.length-2).toInt()
            return height >= this.heightCmRange.first && height <= this.heightCmRange.second
        }
        return false
    }

    val hclIsValid = { hcl: String -> hcl.matches(Regex("^#[0-9a-f]{6}")) }
    val validHairColors = listOf<String>("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    val eclIsValid = { ecl: String -> validHairColors.contains(ecl)}
    val pidIsValid = { pid: String -> pid.matches(Regex("\\d{9}"))}
    private fun isValid(passport: Passport): Boolean {
        val (byr, iyr, eyr, hgt, hcl, ecl, pid, cid) = passport

        return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null && byrIsValid(byr) && iyrIsValid(iyr) && eyrIsValid(eyr) && hgtIsValid(hgt) && hclIsValid(hcl) && eclIsValid(ecl) && pidIsValid(pid)
    }
    fun getResult(): Int {
        return passports.count { isValid(it) }
    }

}