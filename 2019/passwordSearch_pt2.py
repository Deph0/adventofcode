# AdventOfCode 2019 day 4 pt 2
# https://adventofcode.com/2019/day/4
# started 7:15-paused 8:00
# started over at 9:05 - 10:00
# pt2 unsolved 10:00-11:30, 11:45-12:00
# start 19:00pm-19:25pm solved

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
    ddtimes = 0
    if hasdoubledigit:
        # find how many dimes doubledigit exists inside i
        prev = ''
        for dd in doubledigits:
            if dd == prev: continue
            if doubledigits.count(dd) == 2:
                ddtimes += 1
            prev = dd
        if ddtimes > 0:
            possiblepasswords += 1

print(possiblepasswords) # pt2: 1111
