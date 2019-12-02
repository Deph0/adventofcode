# AdventOfCode 2018 day 3 pt 1
# https://adventofcode.com/2018/day/3

#r = [1,1,4,4] # x,y,w,h
r1 = [1,3,4,4]
r2 = [3,1,4,4]
r3 = [5,5,2,2]
r = [r1,r2,r3]

size = 8

#map = [['.' for i in range(size)]]*size # copies the reference to each row
map = [x[:] for x in [['.'] * size] * size]
#map = [['.', '.', '.', '.', '.', '.', '.', '.'], ['.', 'o', 'o', 'o', 'o', '.', '.', '.'], ['.', 'o', 'o', 'o', 'o', '.', '.', '.'], ['.', 'o', 'o', 'o', 'o', '.', '.', '.'], ['.', 'o', 'o', 'o', 'o', '.', '.', '.'], ['.', '.', '.', '.', '.', '.', '.', '.'], ['.', '.', '.', '.', '.', '.', '.', '.'], ['.', '.', '.', '.', '.', '.', '.', '.']]

#for x in map: print x

# this method only works if x or y is smaller than width or height
def nope_fillrect():
	for i in range(len(r)):
		rx,ry,rw,rh = r[i]
		for y in range(ry,ry+rh+1): # x..width
			for x in range(rx,rx+rw+1): # y..height
				if map[y][x] != '.':
					map[y][x] = '#' # colision
				else:
					map[y][x] = str(i+1)
				#print i,'xy',x,y
		#print i,rx,ry,rw,rh
	show()

def fillrect():
	yi = 0
	for y in map:
		xi = 0
		for x in y:
			#map[yi][xi] = ''
			fillCord(xi,yi)
			print x, xi, yi
			xi = xi + 1
		yi = yi + 1
	show()

def fillCord(x,y):
	if x in r
	map[y][x] = str(1)

def show():
	for x in map: print x



###########
## Random test
###########

#r = [1,1,4,4] # x,y,w,h
r1 = [1,3,4,4]
r2 = [3,1,4,4]
r3 = [5,5,2,2]
r = [r1,r2,r3]

size = 8

#map = [['.' for i in range(size)]]*size # copies the reference to each row
map = [x[:] for x in [['.'] * size] * size]
#map = [['.', '.', '.', '.', '.', '.', '.', '.'], ['.', 'o', 'o', 'o', 'o', '.', '.', '.'], ['.', 'o', 'o', 'o', 'o', '.', '.', '.'], ['.', 'o', 'o', 'o', 'o', '.', '.', '.'], ['.', 'o', 'o', 'o', 'o', '.', '.', '.'], ['.', '.', '.', '.', '.', '.', '.', '.'], ['.', '.', '.', '.', '.', '.', '.', '.'], ['.', '.', '.', '.', '.', '.', '.', '.']]

#for x in map: print x

for i in range(len(r)):
	rx,ry,rw,rh = r[i]
	
	for y in range(ry,rh+1):
		for x in range(rx,rw+1):
			if map[y][x] != '.':
				map[y][x] = '#' # colision
			else:
				map[y][x] = str(i+1)
			print i,'xy',x,y
	#print i,rx,ry,rw,rh


for x in map: print x
