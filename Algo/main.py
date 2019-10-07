from Feux import Feu
from GPS import GPS
import requests

# Log de l'API
print("---------- Log API")

token = ""
try:
	username = "laegan"
	password = "yoho"
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
	for coord in coords:
		obj = GPS(coord)
		route = str(obj.get_route())
		res = requests.post("http://localhost:9000/courses/" + coord["id"], params= {"token": token}, data = {"route": str(route)})
		if (res.status_code != 200):
			raise "Erreur envoi route"
	print("---------- Calcule route OK")
except Exception as e:
	print("KO Calculer route")
