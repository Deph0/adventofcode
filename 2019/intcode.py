# AdventOfCode 2019 day 2 pt 1
# https://adventofcode.com/2019/day/2
# start 7:19am 
# started over at 7:37am confused af
# solved pt 1 8:19am
#
# AdventOfCode 2019 day 2 pt 2
# start pt2 8:55 - paused 9:30
# unpaused 13:30
# solved pt2 13:50

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
    originalintcode = [int(x) for x in fp.readline().split(",")]
    intcode = [x for x in originalintcode]
    intcode[1] = 12
    intcode[2] = 2
    result = calcPt1(intcode)
    print(result[0]) # 3716250

    # pt 2
    for noun in range(100):
        for verb in range(100):
            xintcode = [x for x in originalintcode]
            xintcode[1] = noun
            xintcode[2] = verb
            
            result = calcPt1(xintcode)
        
            if result[0] == 19690720:
                print( (100*noun)+verb ) # 6472
                break
