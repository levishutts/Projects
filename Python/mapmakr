import png
import random
import math

# init map
width = 400
height = 200
map = [[0 for i in range(width)] for j in range(height)]
maxP = 0

# create n craters with random position and size
def craters(n):
    maxP = 0
    for i in range(n):
        cx = random.randint(0, width)
        cy = random.randint(0, height)
        r = random.randint(10, 100)
        for x in range(cx - r, cx + r):
            for y in range(cy - r, cy + r):
                distance = math.sqrt((cx - x) ** 2 + (cy - y) ** 2)
                if distance <= r:
                    shade = 255 * min((1 - (distance / r)), 1)
                    if x >= 0 and y >= 0 and x < width and y < height:
                        #shade = (shade + map[y][x]) / 2
                        map[y][x] += math.floor(map[y][x] + shade)
                        if map[y][x] > maxP:
                            maxP = map[y][x]
    return maxP;
    
def prepare(map):
    for x in range(width):
        for y in range(height):
            map[y][x] = math.floor((map[y][x] / maxP) * 255)
            

f = open('ramp.png', 'wb')      # binary mode is important
w = png.Writer(width, height, greyscale=True)
maxP = craters(50)
print(maxP)
prepare(map)
w.write(f, map)
f.close()

test = [range(10)]
