class day1 {
  fun solvept1(input: List<Int>): Int {
    // first halfassed attempt on the example
    // var res = 0
    // var i = 0
    // var cur = 0
    // var prev = 0
    // while (res != 2020) {
    //   cur = input[i++]
    //   res = prev + cur
    //   println(res)
    //   prev = cur
    // }

    // more thought about the problem, which solved it
    for (x in input) {
      for (y in input) {
        if (x+y == 2020) {
          // println( "${x*y}, $x, $y" )
          return x*y
        }
      }
    }
    return 0
  }

  fun solvept2(input: List<Int>): Int {
    for (x in input) {
      for (y in input) {
        for (z in input) {
          if (x+y+z == 2020) {
            // println( "${x*y*z}, $x, $y, $z" )
            return x*y*z
          }
        }
      }
    }
    return 0
  }

}

// david: https://github.com/Meowth52/Advent2020/blob/master/Day01.cs
