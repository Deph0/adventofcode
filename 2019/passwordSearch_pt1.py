# AdventOfCode 2019 day 4 pt 1
# https://adventofcode.com/2019/day/4
# started 7:15-paused 8:00
# started over at 9:05 - 10:00

low, high = [171309,643603]

possiblepasswords = 0
for i in range(low, high+1):
  previous = 0
  hasdoubledigit = False

  # Loop though each digit in the number
  for strdigit in str(i):
    # check if bigger than previous, if not then break
    if int(strdigit) < previous:
        break
    # check for double digits
    elif int(strdigit) == previous:
      hasdoubledigit = True
    previous = int(strdigit)
  
  # forloop fell though (break)- heres the values that worked out- still for-loop'ing
  # https://book.pythontips.com/en/latest/for_-_else.html
  else:
    if hasdoubledigit:
      # print(i, doubledigit)
      possiblepasswords += 1
  
print(possiblepasswords) # 1625

