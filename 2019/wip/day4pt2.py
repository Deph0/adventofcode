# AdventOfCode 2019 day 4 pt 1
# https://adventofcode.com/2019/day/4
# started 7:15-paused 8:00
# started over at 9:05 - 10:00
# pt2 unsolved 1000-1130, 1145-1200

low, high = [171309,643603]

possiblepasswords = 0
for i in range(low, high+1):
  previous = 0
  doubledigits = []
  hasdoubledigit = False

  # Loop though each digit in the number
  for strdigit in str(i):
    # check if bigger than previous, if not then break
    if int(strdigit) < previous:
        break
    # check for double digits
    elif int(strdigit) == previous:
      #if strdigit not in doubledigits:
      doubledigits.append(strdigit)
      doubledigits.append(str(previous))
      hasdoubledigit = True
    previous = int(strdigit)
  
  # forloop fell though (break)- heres the values that worked out- still for-loop'ing
  # https://book.pythontips.com/en/latest/for_-_else.html
  else:
    if hasdoubledigit:
      # print(i, doubledigit, str(i).count(doubledigit))
      # possiblepasswords += 1
      # find how many dimes doubledigit exists inside i
      # print(doubledigits) # [ '7', '7','9', '9', '9' ]
      ddtimes = 0
      prev = ''
      for dd in doubledigits:
          if dd == prev:
              continue
          if  doubledigits.count(dd) == 2:
              possiblepasswords += 1
          prev = dd


print(possiblepasswords) # pt1: 1625, pt2: ?
# 930 too low
# 469 too low

"""
x = ['4', '4', '6', '6', '6', '6']

h = 0
prev = ''
for y in x:
    if y == prev: continue
    if  x.count(y) == 2: h+=1
    print(x, y, x.count(y) )
    prev = y
print(h)
"""
