#use python 3.6

from Feux import Feu
from tkinter import *
import requests

p1 = ["5.834861", "43.409492"]
p2 = ["5.767904", "43.473034"]

req = "http://router.project-osrm.org/route/v1/driving/" + p1[0] + "," + p1[1] + ";" + p2[0] + "," + p2[1] + "?steps=true&geometries=geojson"

r = requests.get(req)
#print(r.text)
d = r.json()

#for key, value in d['routes'][0].items():
#	print(key)
#print("----------------------")

#print(d['routes'][0]['duration'] / 60)

#print("----------------------")
#print(j['destinations'])

#for c in d['routes'][0]['legs'][0]['steps']:
	#for x in c:
	#	print(x)
	#print(d['routes'][0]['geometry'][i])

coord = []
min = [100, 100]
max = [0, 0]
size = [500, 500]

for c in d['routes'][0]['geometry']['coordinates']:
	if (c[0] < min[0]):
		min[0] = c[0]
	if (c[0] > max[0]):
		max[0] = c[0]
	if (c[1] < min[1]):
		min[1] = c[1]
	if (c[1] > max[1]):
		max[1] = c[1]
	coord.append(c)

un = max[0] - min[0]
de = max[1] - min[1]

if (un > de):
	max[1] = min[1] + un
else:
	max[0] = min[0] + de

max[0] -= min[0]
max[0] = int(max[0] * 1000000)
max[1] -= min[1]
max[1] = int(max[1] * 1000000)

for c in range(len(coord)):
	coord[c][0] -= min[0]
	coord[c][1] -= min[1]
	coord[c][0] = int(coord[c][0] * 1000000)
	coord[c][1] = int(coord[c][1] * 1000000)
	coord[c][0] = int(coord[c][0] * size[0] / max[0]) + 10
	coord[c][1] = size[1] - int(coord[c][1] * size[1] / max[1]) + 10
	print(c)

print(min, max)
print(coord)
fen = Tk()
can = Canvas(fen, width = size[0] + 20, height = size[1] + 20)
can.pack()

c = 0
while (c < len(coord) - 1):
	can.create_line(coord[c][0], coord[c][1], coord[c + 1][0], coord[c + 1][1])
	c += 1

fen.mainloop()
