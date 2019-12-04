# AdventOfCode 2019 day 4 pt 1
# https://adventofcode.com/2019/day/4
# started 7:15-paused 8:00

def check(list_):
    last = None
    for element in list_:
        if element == last:
            return element # True
        else:
            last = element
    return False

filepath = 'input_2019-4.txt'
with open(filepath) as fp:
    #pwdrange = [int(x) for x in fp.readline().split("-")]
    pwdrange = [111123,135679]
    p1 = pwdrange[0]
    p2 = pwdrange[1]
    print(pwdrange)

    loopedpass = p1
    possiblepasswords = 0
    for i in range(p1, p2):
        # has double, never decreases
        # adjacent digits are the same double
        # Going from left to right, the digits never decreas
        # can only increse
        x = str(loopedpass)
        xlist = [xx for xx in x]
        double = check(xlist)
        print("//",x, double)
        # if no digit below double in rest of the digits then +1
        if double != False:
            previous = ''
            for j in xlist:
                if j < double:
                    break
                if j < previous: 
                    # TODO need to check if previous and next is a double
                    break
                previous = j
            possiblepasswords += 1

        # doubledigit = ''
        # for j in range(len(x)):
        #     if x[j] == x[j+1]:
        #         doubledigit = x[j] + x[j+1]
        # possiblepasswords += 1
        
        loopedpass += 1

    print(possiblepasswords)
    # 14612 = too high