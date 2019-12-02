# AdventOfCode 2019 day 2 pt 1
# https://adventofcode.com/2019/day/2
# start 7:19am 
# started over at 7:37am confused af
# solved 8:19am

def calcPt1(intcode):
    i = 0
    while i < len(intcode):
        opcode = intcode[i]
        if opcode == 99: break
        if opcode == 1:
            pos_one = intcode[i + 1]
            pos_two = intcode[i + 2]
            pos_three = intcode[i + 3]
            opcode_one = intcode[pos_one] + intcode[pos_two]
            intcode[pos_three] = opcode_one
            # print("op1", opcode, pos_one, pos_two, pos_three, opcode_one, intcode[pos_three], intcode)
        elif opcode == 2:
            pos_one = intcode[i + 1]
            pos_two = intcode[i + 2]
            pos_three = intcode[i + 3]
            opcode_two = intcode[pos_one] * intcode[pos_two]
            intcode[pos_three] = opcode_two
            # print("op2", opcode, pos_one, pos_two, pos_three, opcode_one, intcode[pos_three], intcode)
        i += 4
    return intcode


filepath = 'input_2019-2.txt'
with open(filepath) as fp:
    intcode = [int(x) for x in fp.readline().split(",")]
    # print(intcode)
    intcode[1] = 12
    intcode[2] = 2
    result = calcPt1(intcode)
    print(result[0])