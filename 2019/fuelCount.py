# AdventOfCode 2019 day 1
# https://adventofcode.com/2019/day/1
# Started: 9:55 am
# Solved: pt1 10:17am, pt2 11:07 am (1 hour 12 minutes)

def calcModuleFuel(mass):
    newmass = (round(mass // 3) - 2)
    if newmass <= 0:
        return mass
    else:
        newfuel = calcModuleFuel(newmass)
        return mass + newfuel

filepath = 'input_2019-1a.txt'
with open(filepath) as fp:
    # testfp = [14, 1969, 100756]
    # module = [(round(float(mass) // 3)) - 2 for mass in testfp]
    module = [(round(float(mass) // 3)) - 2 for (i, mass) in enumerate(fp)]
    moduleTotalFuel = [calcModuleFuel(x) for x in module]
    print("Module fuel:", sum(module))
    print("Module's module total fuel:" , sum(moduleTotalFuel) )
