from Feux import Feu
from GPS import GPS
import requests
import time

# Log de l'API
print("---------- Log API")

token = ""
try:
	username = "Laegan"
	password = "nouveau"
	req = "http://localhost:9000/users/login"
	res = requests.post(req, data = {"username": username, "password": password})
	print(res.json())
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
		res = requests.get("http://localhost:9000/courses", headers={"Authorization": "Bearer " + token})
		coords = res.json()
		print("---------- GET coordonnées OK")
	except Exception as e:
		print("KO GET coordonnées")
		print(e)

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
		print(e)

	#c = input();
	#if (c == "end"):
	#	break
	time.sleep(5)
