# start 10:00 - paused 11:00
routes = ["R8","U5","L5","D3"]

dirDict = {
  "R": [1, 0], # R = Right / Höger
  "L": [-1, 0], # L = Left / Vänster
  "U": [0, 1], # U = Up
  "D": [0, -1] # D = Down
}

#........... # 11x10
#...........
#...........
#....+----+.
#....|....|.
#....|....|.
#....|....|.
#.........|.
#.o-------+.
#...........

# Fill map
sizew = 11
sizeh = 10
matrix = [x[:] for x in [['.'] * sizew] * sizeh]

origin = 8
for path in routes:
  pdir = path[:1]
  psteps =  int(path[1:])
  print(pdir, psteps)
  
  dx, dy = dirDict[pdir]
  for i in range(sizew*sizeh):
    for step in range(psteps):
      matrix[dy+step][dx+step] = "*"
    

#  if pdir == "R":
#    for x in range(1, psteps+1):
#      matrix[origin][x] = "-"
#  elif pdir == "L":
#    for x in range(1, psteps+1):
#      matrix[origin][x] = "-"
#  elif pdir == "U":
#    for y in range(1, psteps+1):
#      matrix[y][origin] = "|"
#  elif pdir == "D":
#    for y in range(1, psteps+1):
#      matrix[y][origin] = "|"


# draw
for y in range(sizeh):
  for x in range(sizew):
    print(matrix[y][x], end =" ") # prints the matrix
  print()


