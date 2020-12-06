import java.io.*
fun input(f: String): List<String> = File(f).bufferedReader().use { it.readLines() }
fun inputSep(f: String, separator: String = "\n"): List<String> = File(f).bufferedReader().use { it.readText().split(separator) }

fun main(args: Array<String>) {
  // day1()
  // day1pt3()
  // day2()
  // day3()
  // day4()
  // day5()
  day6()
}
