package aoc.day.two

import java.io.File

// Destruct Declaration
data class Address(val a:Int, val b:Int, val c: Int) // addr 0, 1, 2

tailrec fun compute(memory: Array<Int>, pointer: Int = 0): Array<Int> {
    val (a, b, c) = Address(memory[pointer+1], memory[pointer+2], memory[pointer+3])
    // pointer = instruction pointer
    when (memory[pointer]) {
        // opcode -> instruction
        1 -> memory[c] = memory[a] + memory[b] // calculate instruction's parameters
        2 -> memory[c] = memory[a] * memory[b] // ^- .. and set the instruction
        99 -> return memory // Halt Signal
        else -> throw Exception( "Memory Error: ${memory.toList()}" ) // Shouldn't happen, halt with error
    }
    // Advance the program
    return compute(memory, pointer + 4)
}
fun executeProgram(memory: Array<Int>, noun: Int, verb: Int): Array<Int> {
    memory[1] = noun
    memory[2] = verb
    return compute(memory)
} 

fun main(args: Array<String>) {
    // println(compute(arrayOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)).toList().get(0)) // [0]=3500, result=[3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50]
    val lineList = File("../input_2019-2.txt").readText().split(",").map { it.toInt() }
    println(executeProgram(lineList.toTypedArray(), 12, 2)[0]) // 3716250

    for (noun in 0..99) {
         for (verb in 0..99) {
            if (executeProgram(lineList.toTypedArray(), noun, verb)[0] == 19690720) {
                println(100 * noun + verb) // 6472
                break
            }
        }
    }
}
