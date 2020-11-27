from Feux import Feu
from GPS import GPS
import requests
import time

# Log de l'API
print("---------- Log API")

token = ""
try:
	username = "algo"
	password = "cocolito"
	req = "http://localhost:9000/login"
	res = requests.post(req, data = {"name": username, "password": password})
	token = res.json()['token']
	print("---------- LOG OK")
except Exception as e:
	print(e)
	print("KO LOG API")

# get feux coordonnées
print("---------- GET FEU")

feux = []
try:
	req = "http://localhost:9000/feu"
	res = requests.get(req, params = {"token": token})
	for feu in res.json()['content']:
		feux.append(Feu(float(feu["x"]), float(feu["y"]), feu["city"]))

	print("---------- Get feu OK")
except Exception as e:
	print("KO GET feu")


while (1):
	print("-------- Je tourne en boucle ---------")
	print()
	# get coord destination
	print("---------- GET coordonnées")

	coords = {}
	try:
		res = requests.get("http://localhost:9000/courses", params = {"token": token})
		coords = res.json()["content"]
		print("---------- GET coordonnées OK")
	except Exception as e:
		print("KO GET coordonnées")

	# calcule road from position to to
	print("---------- calcule route")

	try:
		th = []
		for coord in coords:
			obj = GPS(coord, token)
			obj.start()
			th.append(obj)

		for i in th:
			i.join()
		print("---------- Calcule route OK")
	except Exception as e:
		print("KO Calculer route")

	#c = input();
	#if (c == "end"):
	#	break
	time.sleep(5)
