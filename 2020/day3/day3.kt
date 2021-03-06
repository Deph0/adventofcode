// data class Position(var x: Int = 1, var y: Int = 1)
class day3 {
  fun part1(input: List<String>): Int {
    // first fumbeling attempt on grasping the task..
    /*var treeMap: MutableList<String> = input.map { it.repeat(input.size+1) }.toMutableList()
    var pos = Position()
    for (y in 0..input.size-1) {
      // 3 right, 1 down
      // println(treeMap[y])
      for (x in 0..treeMap[y].length-1) {
        var xChar = treeMap[y][x]
        if (x+pos.x == pos.x+3) {
          val objectType = when (treeMap[y][x]) {
            '.' -> 'O'
            '#' -> 'X'
            else -> treeMap[y][x]
          }
          val charsList =  treeMap[y].toMutableList()
          charsList[x] = objectType
          treeMap[y] = charsList.joinToString(separator = "")
          // println("objectType: $objectType")
          pos.x += 3
          // println("Char: $xChar, X: $x, Y: $y")
          // println(pos)
        }
      }
      pos.y += 1
      // when pos is # then set X else O
    }*/
    var treeMap: MutableList<String> = input.toMutableList()
    var treeCount = 0
    var idx = 0
    var down = 1
    var right = 3
    for (y in 0..treeMap.size-1 step down) {
        var xChar = treeMap[y].toMutableList()
        var xCharValue = xChar[idx]
        // var xCharString = treeMap[y].toString() // debug for breakpoint

        treeCount += if (xCharValue == '#') 1 else 0

        idx += right // go 3 steps to the right
        idx -= if (idx >= xChar.size) xChar.size else 0 // we traveled above length, take reminder of idx
    }
    // println(treeCount)
    
    // treeMap.forEach(::println)
    return treeCount // count amount of X in treeMap
  }

  fun part2(input: List<String>, slopes:  List<Pair<Int, Int>>): Long {
    fun calcSlope(right: Int, down: Int): Int {
      var treeMap: MutableList<String> = input.toMutableList()
      var treeCount = 0
      var idx = 0
      for (y in 0..treeMap.size-1 step down) {
          var xChar = treeMap[y].toMutableList()
          var xCharValue = xChar[idx]
          treeCount += if (xCharValue == '#') 1 else 0
          idx += right
          idx -= if (idx >= xChar.size) xChar.size else 0 // remove if we went over length
      }
      return treeCount
    }
    return slopes.map {
      val (a, b) = it // deconstuct to (right, down)
      val slope = calcSlope(a, b)
      // println("Slope: $slope")
      slope.toLong() // 64bit Int, to avoid overflow
    }.reduce(Long::times)
  }

  init {
    val slopes = listOf(
      Pair(1, 1),
      Pair(3, 1),
      Pair(5, 1),
      Pair(7, 1),
      Pair(1, 2)
    )
    val exampleinput = listOf(
      "..##.......",
      "#...#...#..",
      ".#....#..#.",
      "..#.#...#.#",
      ".#...##..#.",
      "..#.##.....",
      ".#.#.#....#",
      ".#........#",
      "#.##...#...",
      "#...##....#",
      ".#..#...#.#"
    )
    println("day3-pt1-example: " + part1(exampleinput)) // 7
    println("day3-pt2-example: " + part2(exampleinput, slopes)) // 336

    val input = listOf(
      ".....#.##......#..##..........#",
      "##.#.##..#............##....#..",
      "......###...#..............#.##",
      ".....#..##.#..#......#.#.#..#..",
      "..#.......###..#..........#.#..",
      "..#..#.##.......##.....#....#..",
      ".##....##....##.###.....###..#.",
      "..##....#...##..#....#.#.#.....",
      ".....##..###.##...............#",
      "#.....#..#....#.##...####..#...",
      "#......#.#....#..#.##....#..#.#",
      "##.#...#.#............#......#.",
      ".#####.......#..#.#....#......#",
      "..#.#....#.#.##...#.##...##....",
      ".....#.#...#..####.##..#.......",
      "#....#...##.#.#.##.#..##.....#.",
      "##.##...#....#...#......#..##..",
      "....##...#..#.#...#.#.#.....##.",
      "..#....##......##....#.#....#..",
      "#..#....#....###..#.##....#.#.#",
      "..#.#####..##....#....#.....##.",
      ".#...##.......#...#....#.#...##",
      "#.#.#.##.......#.....#.#.#....#",
      ".#.#.....#.......#.......##....",
      ".#......#....#....#.......##...",
      "#......#.....#......#..#..#....",
      "#.#...#...#....##....#.#...#..#",
      "....#.....##...#...#..#.#......",
      "..#......#..........#...#.#....",
      "..#..#......####..##...###.....",
      ".#.....#...##...#.##........###",
      "#.#....#..#....#..#.....#.#..#.",
      "...##.##.#.#.##...#.....#......",
      "##....#.#.#...####.#.#.#.#.....",
      ".##.........#..#..###..........",
      "..##.###.#..#..#....##.....#...",
      "##........#..###....#.#..#..#..",
      "....#.#.......##..#.#.#.#......",
      "....##.....#.........##.......#",
      "..#........##.#.........###..##",
      "....#..................##..#...",
      "#...#.#..###..#.....#..#..#...#",
      "..#..#.##..#..#.......#.......#",
      ".....#..##..#....##...........#",
      "..##...#........#...#.#.......#",
      ".........#.#..#.#..#.##.#.###..",
      "....#...#..#..#......##....#.#.",
      "..#..#.#....#....#..#.####..##.",
      "##....#.....#......##.###.#..#.",
      "#..#..##..###......#.#.#.#...#.",
      ".......#..##..##...#...#..#....",
      "..#.###.#...#....#.##.#.....##.",
      ".#.#.......##...##...##....#...",
      "#...#.#.#...#.####..#..##......",
      "###..#.##..#..........#...#....",
      "##.#.........#..##......####...",
      "..##.#..#....#.##..............",
      "...#....#.......###............",
      "...#.....##....#.#.#.#.......##",
      "###.###...#...#...###.##...##..",
      "#.#....#.##..#.....#.....##.#..",
      "...#....#....#.........#....#.#",
      "##.#....#........#..#..##.#....",
      ".#.#..#.......#...##.......#...",
      ".##...##........#....#.#..#....",
      "....#..#.##.###.....#.#........",
      ".#.#...#.#..#.....#.........#..",
      ".......#.#.#..##....#.........#",
      ".##...#....#..#...#........#..#",
      "....#....#..#.#..#.#.#....##.##",
      "..##....#.....##..#.#...#...#..",
      "#.##.........#.....#.......#.##",
      "...#...##.#.#..........#......#",
      "###...#.....#..#.......#####..#",
      "#.####...##.#.#..#...#.........",
      ".##.....#.....##..#...##.##....",
      ".........###...#......##....###",
      ".#....##...###.#..#...##..#.#.#",
      ".......#.......#.#...##.#......",
      ".....#.#........#..##.....##...",
      "....#.#.........##.#...##..#.#.",
      "#..#..#.##..#.##.##.....##.###.",
      "..##.........###...#....#....#.",
      ".###...#..#.##...........#.....",
      "#..##..........#..........#....",
      ".....#.#....#..##..#...#.#....#",
      "..#.....#.#....#...##.##.......",
      "##.....##........#....#..##....",
      ".#..#.#.........#..#..#........",
      ".............##....#....#..#...",
      "....##....#..#.#.##....###.##.#",
      ".###..#.....#..#..##..#..##..#.",
      "...#..###.......#.#....#..###..",
      "#.#..#.....#...#......#........",
      "#..#..............###.#......#.",
      "..#....##.#....#.##.#.#...#....",
      ".........##..#...#.#.......#...",
      "........#...#.#....#.....##..#.",
      "...#.##..#..#..###..#..#......#",
      ".....####......#...#....#...#.#",
      "...###.#.#......#....#.......#.",
      "#...##.#....#....##....##.###..",
      ".......##...##.....#.##.#..#..#",
      ".....#.#............##...#.####",
      ".##..#.#.#.#..#.#.#.....#.##...",
      ".#..####...#.#....#.....#..#...",
      "....##..#.#...#..#....#.#......",
      "...#......###..#..###..#.....#.",
      ".#.#.#..##....#...##..#.....#..",
      "###....#....#...##.....#...#...",
      "#.##....#......#...###.........",
      ".#..#.#...#..#....#....#....#..",
      "...............##...####..#..#.",
      "#.#...........####..#...##.....",
      "##.#....#........#......#...##.",
      "......#...#...#....#....#.....#",
      "#......#.............#....###..",
      ".#...#...##.....#...##.##..#...",
      "..#.#......#.#........#........",
      ".......#..#.#...##..#.#.#......",
      "..##...#.##........#....#.#...#",
      ".....#..#..#........#.#......##",
      "....#.#...##............##....#",
      ".#.#....#.#.#...#...#.##.....#.",
      "#.#.##...#....#.#.#..#.##..#.#.",
      ".........####..#...#...#.......",
      "#..#..####......#..##..#...#...",
      ".........##..................#.",
      ".....##.#..##.#.#...#......##..",
      "...#....#....#.#.....#...#..#.#",
      "#...##.#...##...........#..#...",
      "#..........#.#..#..#.##..#..#.#",
      ".#...#.##...#.#.#..#.......##..",
      ".........#...........#..#..#...",
      ".##...##....#.#......#........#",
      "#.#...........#....#.......#...",
      "##.#.#.......#...###......##..#",
      "...###..#.##..##.#.#.......#...",
      ".#...#..##.#...#........#.....#",
      "...#.......#..#..........#.#...",
      "..#.#.#.#.....#.#.......#..#..#",
      "#.##.....#..##...#..###.#....#.",
      ".......#...........#...#....###",
      ".......#..#...#.............#..",
      "#.....###.......#...#........#.",
      ".#..#..#..#...........#........",
      "....#.#...#.#.##.#.#....#.##..#",
      ".......#..##...##...#...#......",
      "...#.....##.###...#.#...##....#",
      "#..#....#...##......#....##....",
      "#.#.......#....#.###.##..#..#..",
      "..##...........#...#....#......",
      ".#........#.....#..#..#...#..##",
      ".....#.#.#..#.......#....#.....",
      "#..#.#......#......##....#.....",
      "##.....................##......",
      ".##........###..#.........#...#",
      "........#.........#..#.........",
      ".#.##....#.....#...#.........##",
      "....##......#.........#........",
      "...#.#..#...##.##.#.#..####....",
      "..##...........##.#.#....#.....",
      ".#.....#.#...#..#.......#....#.",
      "....#...#......##...#...##.#..#",
      "....#..##....#..#.........##.#.",
      "..##...##.##....#....##.###...#",
      "..#....##..##.#.#.#...#......#.",
      "##...#.........#...........#...",
      ".##....##.#.....#...#.......#..",
      "..........##.###.##....###....#",
      "..........#..##..#....#.#.##.##",
      "........##.#...#.#.#.#...###.#.",
      ".#......#.#.#...###.#.#.#......",
      ".........#......#......#...#..#",
      "......#.....#.##....##.#####..#",
      "..#..##...###.#..........#.#.#.",
      ".#..#....###.#...#..#....#...##",
      "...................#..........#",
      "....###.....#...##......#.....#",
      "#.....#..##.....#.#..........#.",
      "..#.......##.#....#..#.##.#...#",
      "........##.#..###..#......##...",
      "#...........##.#...###..#....#.",
      "....#...........#.....#.#...#..",
      ".##..#.#...#...#.##...#..#.....",
      "#........#.#.#.#.#.#...........",
      "#..#.....#..#..#.##....#....#.#",
      "..#............##....#.#.##...#",
      ".....###.#....#.#......#.###...",
      "...#.....#.#.................#.",
      "..#...##..#.#...#...#...#.....#",
      ".##.#........#..#....##..#..##.",
      ".#..........#...#.#..#..#.#....",
      "#.......##.........#.##..#.####",
      ".#..............#.......##.....",
      "#......#.##..........#..#......",
      "..##...#...#.#...#............#",
      ".##.##..##..##........##.....#.",
      ".....#..#.....##...............",
      ".#..#...##...#...#.....#.......",
      "#......#...#.......#..##.###.##",
      "###..##......##......###....#..",
      "....#..........#...#.##.#.....#",
      ".........#....#..#..#.#..##....",
      ".....#.....#...........#......#",
      ".#.......#...#....##...#.##...#",
      "..##.#..............#..#...#.#.",
      ".#..####.#.........#....#....#.",
      "..###.#...#..#......#.......###",
      ".#.#..##...###...#...#.#...#.#.",
      "...#..##..###.#..#.....#.##....",
      "#...###.#...##.....####.....#..",
      ".#.##...#..#.#..##.....#.......",
      "...#.##.....##.....#....#......",
      ".#...##.....#..###..#..........",
      "..........#...#.....#....##.#..",
      ".......#...#...#...#........#..",
      "#....##..#...#..##.#.#.....#...",
      ".#.#..............#..#....#....",
      ".####.#.#.###......#...#.#....#",
      ".#...#...##.#...............#.#",
      "...#.......##...#...#....##....",
      "#..........###.##..........##.#",
      ".......#...#....#.#..#.#....#..",
      "....#.##.#...###..#..##.##.....",
      "..#.#.#......#.#.......###.....",
      "#..................#.##....#...",
      "#.....#..#.#.#..#...#.........#",
      "..#..#...#.#.##........#.......",
      "#..#.#..#..........###...#.#...",
      ".......#.##....#........##.#...",
      ".####.#.#...#.#...##.##.....###",
      "........#.#...#.#..##...##.....",
      "....##.##......#.##.........#..",
      ".#..#...#.#...........#........",
      ".......#..........#....#...#...",
      "..###.#.###..#..#.....#..##....",
      ".#..........#.......##...#.....",
      ".#.....#...#........#...#.##..#",
      ".#..#.......#..#.......#.#.#...",
      "....#..##.#...##...#.#....#....",
      ".....#.........#..#..#....#....",
      "..#.#..##....#..#..##.#.#.....#",
      "........#.#...###....#.#.#.....",
      ".#.....#.......#..###.#........",
      ".......#...#.#...#...##........",
      "##.............#.#.....#.#..#..",
      ".#....#.......#.#.......#..##..",
      "#.....#........#..##..##.......",
      "...........#.........###......#",
      "....#.##...#.#...#...#....#..##",
      "......#..##......#......#.##.#.",
      "......##....####...###...#.....",
      "#....#..........#.#.##.....#..#",
      "....#.#...........#.#.#.#.#...#",
      "....####.....##...#..##..#...#.",
      "#....#.###..###.....#..###.....",
      "..##.........#......#...##.#...",
      "..#.....#.#...#.##.#...#..###.#",
      "..#.##..##........#.......#.###",
      ".....#..........#.....#....#...",
      ".......##..##..###.......#####.",
      "..###......#.#....###....##...#",
      "#..##.....#..###...#.....##.##.",
      "#..#..##.##.###.####.##.#......",
      ".#.#......#.##......#..#......#",
      "..###.....#.#......#.#.####....",
      "#..............#..#.#...#.###..",
      "...#..#.##..........##.#...#.##",
      ".#.#.#.........#....#.#..#.....",
      "..#.##..#...#..#...#......#....",
      ".......#...#.##.#.#..#...##..#.",
      "..........#.####...#........#.#",
      "....#...#....##.#.........#.#..",
      "##.#..#.......###....#..#..#.#.",
      "..##.....#..#.#.#.####......#..",
      ".#.....#..........#..#..#.#....",
      "......#.#.......#.#...#..#..#..",
      "...#...............#....#...#..",
      "##.......#.........#.......#...",
      "...#.......###...#.#...#.......",
      "#...###....#....##....#....#...",
      "...#....##..#.#.............##.",
      ".....#.#.#..#......#...#.#..#..",
      ".##....#..##..#####..##.....##.",
      "....##.#.#..#.....#.#...#......",
      "...#.....##.#.#..##..#.#.......",
      ".......#..#..#..........#......",
      ".......#...#..#.........#.##...",
      "..#..#..#...##..#.#....#......#",
      "..#....#...#.#......#........#.",
      ".#...#..#...#.#..........#.....",
      "#..#...####..#......##.##.#.#..",
      ".#...#.#...#.#.....#..##.#.....",
      "..#.##.#......#.........##...#.",
      "###..............#.............",
      "...#...###....#..#.............",
      ".##....#......#..#.....#..#..#.",
      ".#..........#.....##...#..#....",
      "....##..#.#......###.##......#.",
      ".#..##.#.##.#...##.#......###.#",
      "#..###.#...###..#........#.#...",
      "#..#.#.#..#...###.##.##..#..#..",
      "#.#..#....#.........##......#..",
      "....###.....###....#...........",
      "....#..##.##....##..#.....#....",
      ".#.....#....####...#..##.#..###",
      ".........##..#......#.#...##...",
      ".##.......#.....#.###.#..#.#..#",
      ".....#.#...###.....#......####.",
      "##.#...#......#.#.#..#.####...#",
      ".#.##.....#..#..#.............#",
      ".#..###..#..#......#..##......#",
      ".......#.#........##.....#.#...",
      "#....#...#..###..#.#.....#.##..",
      ".##.....#.#....###..#.....##...",
      "...##....#....#...#....#.#.#...",
      "#####..#...........###....#...#",
      ".#.......##.##.....#....#......",
      ".#..#.#...#..#......#...#..#.#.",
      "....#.....##...#####..#...#...#",
      "###.##...#.#............#....#.",
      ".....#...#........##.........#."
    )
    println("day3-pt1: " + part1(input)) // 276
    println("day3-pt2: " + part2(input, slopes)) // 7812180000
    
  }
}
