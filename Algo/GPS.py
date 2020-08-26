import requests
from threading import Thread

class GPS(Thread):
	"""docstring for GPS."""
	def __init__(self, coords, token):
		super(GPS, self).__init__()
		self.id = coords["id"]
		self.coord_from = [coords["lat_pos"], coords["long_pos"]]
		self.coord_to = [coords["lat_des"], coords["long_des"]]
		self.route = None
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
		print(route)
		res = requests.put("http://localhost:9000/courses/" + self.id, headers= {"Authorization": "Bearer " + self.token}, data = {"course": str(route), "time": str(self.data["time"]), "distance": str(self.data["distance"])})
		if (res.status_code != 200):
			raise "Erreur envoi route"
