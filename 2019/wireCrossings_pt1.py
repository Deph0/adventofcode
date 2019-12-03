# AdventOfCode 2019 day 3 pt 1
# https://adventofcode.com/2019/day/3
# start 18:00pm - solved with tests 19:30pm but doesn't work with puzzleinput
# Solved using Set() 21:15 after having my first version running in 75min without solution.
# Big thanks to the community of AOC githubs, Python is not my strong suit :)

def manhattan(ax, ay, bx, by):
	return abs(ax - bx) + abs(ay - by)

filepath = 'input_2019-3.txt'
with open(filepath) as fp:
    wire1 = fp.readline().replace("\n","").split(",")
    wire2 = fp.readline().replace("\n","").split(",")

    # wire1 = ["R8","U5","L5","D3"]
    # wire2 = ["U7","R6","D4","L4"] # distance: 6
    # wire1 = ["R75","D30","R83","U83","L12","D49","R71","U7","L72"]
    # wire2 = ["U62","R66","U55","R34","D71","R55","D58","R83"] # distance: 159
    # wire1 = ["R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"]
    # wire2 = ["U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"] # distance: 135

    wires = [ wire1, wire2 ]
    wireDict = { 0: {"x": 0, "y": 0, "steps": 0 }, 1: {"x": 0, "y": 0, "steps": 0 } }
    # wireWalkedDict = { 0: [], 1: [] }
    wiresCrossedDistanceFromOrigin = []
    walked = [set(), set()]

    for wire in range(len(wires)):
        for path in wires[wire]:
            dir = path[:1]
            steps = int(path[1:])

            for i in range(steps):
                if dir == "R":
                    wireDict[wire]["x"] += 1
                elif dir == "L":
                    wireDict[wire]["x"] -= 1
                elif dir == "U":
                    wireDict[wire]["y"] += 1
                elif dir == "D":
                    wireDict[wire]["y"] -= 1
                # wireWalkedDict[wire].append(wireDict[wire].copy()) # save each step
                walked[wire].add( (wireDict[wire]["x"], wireDict[wire]["y"]) )


    # Check if wires crossed eachother (warning: slow with puzzle input, fast with examples...)
    # for w1 in wireWalkedDict[0]:
    #     w1x, w1y = w1["x"], w1["y"]
    #     for w2 in wireWalkedDict[1]:
    #         w2x, w2y = w2["x"], w2["y"]
            
    #         if w1x == w2x and w1y == w2y:
    #             print("wire corssed eachother", w1, w2, w1x, w2x, w1y, w2y)
    #             wiresCrossedDistanceFromOrigin.append(manhattan(0,0, w1x, w1y))
    #             print("distance:", manhattan(0,0, w1x, w1y) )
    # print("Min distance from origin crossed:", min(wiresCrossedDistanceFromOrigin))

    # Solved by using set()'s as found in https://github.com/Dementophobia/advent-of-code-2019/blob/master/2019_03_p1.py (Thanks!)
    # Runtime my solution: over 1h, without finding min distance, runtime with Set()'s: instant
    wiresCrossed = walked[0].intersection(walked[1]) # wire 1 and wire 2 cross intersections
    print("Min distance from origin crossed:", min(manhattan(0,0,pos[0],pos[1]) for pos in wiresCrossed) )
