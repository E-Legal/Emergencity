import requests
from threading import Thread

class GPS(Thread):
	"""docstring for GPS."""
	def __init__(self, coords, token):
		super(GPS, self).__init__()
		self.id = coords["id"]
		self.coord_from = [coords["x_start"], coords["y_start"]]
		self.coord_to = [coords["x_end"], coords["y_end"]]
		self.route = coords["course"]
		self.token = token
		self.data = {"time": 0, "distance": 0}

	def get_route(self):
		if (self.route != None):
			return (self.route)
		req = "http://router.project-osrm.org/route/v1/driving/" + self.coord_from[0] + "," + self.coord_from[1] + ";" + self.coord_to[0] + "," + self.coord_to[1] + "?steps=true&geometries=geojson&annotations=true&overview=full"
		r = requests.get(req)
		self.data["time"] = r.json()["routes"][0]["duration"]
		self.data["distance"] = r.json()["routes"][0]["distance"]
		route = r.json()["routes"][0]["geometry"]["coordinates"]
		return route

	def run(self):
		print("-- Je suis un thread")
		route = str(self.get_route())
		res = requests.post("http://localhost:9000/courses/" + self.id, params= {"token": self.token}, data = {"route": str(route), "time": str(self.data["time"]), "distance": str(self.data["distance"])})
		if (res.status_code != 200):
			raise "Erreur envoi route"
