# start pt2 8:55 - paused 9:30
# unpaused 13:30 - solved pt2 13:50

for noun in range(100):
    for verb in range(100):
        filepath = 'input_2019-2.txt'
        with open(filepath) as fp:
            xintcode = [int(x) for x in fp.readline().split(",")]
            # print( noun, verb )
            xintcode[1] = noun
            xintcode[2] = verb
            
            result = calcPt1(xintcode) #IndexError: list assignment index out of range
        
            if result[0] == 19690720:
                print( (100*noun)+verb )
                break
