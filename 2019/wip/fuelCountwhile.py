# AdventOfCode 2019 day 1 pt 2
# https://adventofcode.com/2019/day/1
# For fun, solve it both with Recursion and While-Loop.

# pt 1
# For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
# For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
# For a mass of 1969, the fuel required is 654.
# For a mass of 100756, the fuel required is 33583.

# pt 2
# - A module of mass 14 requires 2 fuel.
#  14 / 3 - 2 = 2
# - A module of mass 1969 requires 654 fuel. 
#  654 + 216 + 70 + 21 + 5 = 966.
# - A module of mass 100756 requires 50346 fuel.
#  33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12 + 2 = 50346.

# Recursion
def Recursive_CalcModuleFuel(mass):
    newmass = (round(mass // 3) - 2)
    return mass if newmass <= 0 else mass + Recursive_CalcModuleFuel(newmass) 

# While
def CalcModuleFuel(mass):
    previous = 0
    current = mass
    total = 0
    while current > 0:
        previous = current
        current = previous // 3 - 2
        total = total + previous
        # print(mass, total, previous, current)
    return total

def Simplified_CalcModuleFuel(mass):
    current = mass # inital seed for loop
    total = 0
    while current > 0:
        total += current # add previous value to total
        current = current // 3 - 2 # calculate new mass
    return total


mass = [12, 14, 1969, 100756]
module = [ round(x // 3) - 2 for x in mass ]
totFuel = [ CalcModuleFuel(x) for x in module ]
totFuelSimplified = [ Simplified_CalcModuleFuel(x) for x in module ]
totFuelrec = [ Recursive_CalcModuleFuel(x) for x in module ]

print("mass", sum(mass), mass)
print("module", sum(module), module)
print("totFuel", sum(totFuel), totFuel)
print("totFuelSimplified", sum(totFuelSimplified), totFuelSimplified)
print("totFuelRecursion", sum(totFuelrec), totFuelrec)

