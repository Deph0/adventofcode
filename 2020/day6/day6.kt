class day6 {
  fun part1(input: List<String>): Int {
    val inputGroupsSets = input.map {
      it.split("\n").flatMap { group ->
        group.split("").filter {
          it.isNotBlank() // [, a, b,] because java is stupid
        }.toSet()
      }.toSet()
    }

    // println(inputGroupsSets)
    var res = inputGroupsSets.sumBy {
      it.size
    }
    return res
  }

  fun part2(input: List<String>): Int {
    val inputGroups = input.map {
      it.split("\n").map { group ->
        group.split("").filter {
          it.isNotBlank() // [, a, b,] because java is stupid
        }// .toSet()
      }//.toSet()
    }
    // var countGroups = inputGroups.map {
    //   var seen = hashMapOf<String, Int>()
    //   it.forEach {
    //     it.forEach {
    //       var value = seen.getOrPut(it) { 0 }
    //       seen.set(it, ++value )
    //     }
    //   }
    //   seen
    // }
    // println(inputGroups)
    // println(countGroups)

    // thx @Meowth52 github, was super lost on this part.. i got the hashmap hint from the puzzle but couldn't figure out how to apply it..
    var res = 0
    inputGroups.forEach { group ->
      var seen = hashMapOf<String, Int>()
      // println("group: $group")
      group.forEach { ans ->
        ans.forEach { ansChar ->
          //  println(ansChar)
          var value = seen.getOrPut(ansChar) { 0 }
          seen.set(ansChar, ++value )
        }
      }
      // seen.forEach { (key, value) ->
      seen.forEach { (_, value) ->
        // println("$key = $value")
        if (value == group.size) res ++
      }
    }
    return res
  }

  init {
    // val exampleinput = inputSep("inputs/day6-example.txt", "\n\n")
    // println("day6-pt1-example: " + part1(exampleinput)) // 11
    // println("day6-pt2-example: " + part2(exampleinput)) // 6

    val input = inputSep("inputs/day6.txt", "\n\n")
    println("day6-pt1: " + part1(input)) // 6587
    println("day6-pt2: " + part2(input)) // 3235
    
  }
}
