# start pt2 8:55 - paused 9:30
intcode = []
for noun in range(100):
  for verb in range(100):
    # print( noun, verb )
    intcode[1] = noun
    intcode[2] = verb
    
    result = calcPt1(intcode) #IndexError: list assignment index out of range
   
    if result[0] == 19690720:
      print( (100*noun)+verb )
