# AdventOfCode 2019 day 3 pt 2
# https://adventofcode.com/2019/day/3
# start 21:25pm - sovled 22:15 pm

def manhattan(ax, ay, bx, by):
	return abs(ax - bx) + abs(ay - by)

filepath = 'input_2019-3.txt'
with open(filepath) as fp:
    wire1 = fp.readline().replace("\n","").split(",")
    wire2 = fp.readline().replace("\n","").split(",")

    # wire1 = ["R8","U5","L5","D3"]
    # wire2 = ["U7","R6","D4","L4"] # distance: 6
    # wire1 = ["R75","D30","R83","U83","L12","D49","R71","U7","L72"]
    # wire2 = ["U62","R66","U55","R34","D71","R55","D58","R83"] # distance: 159, steps: 610
    # wire1 = ["R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"]
    # wire2 = ["U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"] # distance: 135, steps: 410

    wires = [ wire1, wire2 ]
    wireDict = { 0: {"x": 0, "y": 0, "steps": 0 }, 1: {"x": 0, "y": 0, "steps": 0 } }
    wiresCrossedDistanceFromOrigin = []
    wireWalkedDict = { 0: [], 1: [] }
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
                
                wireDict[wire]["steps"] += 1

                walked[wire].add( (wireDict[wire]["x"], wireDict[wire]["y"]) )
                wireWalkedDict[wire].append(wireDict[wire].copy()) # save each step

    wiresCrossed = walked[0].intersection(walked[1])
    
    totSteps = []
    for x,y in wiresCrossed:
        w1steps = 0
        w2steps = 0
        for w in wireWalkedDict[0]:
            if w["x"] == x and w["y"] == y:
                w1steps = w["steps"]
        for w in wireWalkedDict[1]:
            if w["x"] == x and w["y"] == y:
                w2steps = w["steps"]
        totSteps.append( w1steps + w2steps )
    
    print("Min steps from origin to crossed wire:", min(totSteps) )
