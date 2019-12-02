package aoc.day.one

import java.io.File

fun fuel(mass: Int): Int = mass / 3 - 2
tailrec fun fuelOfFuel(mass: Int, result: Int = 0): Int =
    if (mass > 0) fuelOfFuel(fuel(mass), result + mass) else result

fun main(args: Array<String>) {
    // println(fuel(100756)) // 33583
    // println(fuelOfFuel(fuel(100756))) // 50346
    val lineList = File("../input_2019-1a.txt").readLines().map { it.toInt()}
    val totFuel = lineList.map { fuel(it) }.sum()
    val totFuelOfFuel = lineList.map { fuelOfFuel(fuel(it)) }.sum()
    println("Fuel: $totFuel, payload fuel: $totFuelOfFuel")
}
