import requests

class GPS(object):
	"""docstring for GPS."""
	def __init__(self, coords):
		super(GPS, self).__init__()
		self.id = coords["id"]
		self.coord_from = [coords["x_start"], coords["y_start"]]
		self.coord_to = [coords["x_end"], coords["y_end"]]
		self.route = coords["course"]

	def get_route(self):
		 if (self.route != None):
			return (self.route)
		req = "http://router.project-osrm.org/route/v1/driving/" + self.coord_from[0] + "," + self.coord_from[1] + ";" + self.coord_to[0] + "," + self.coord_to[1] + "?steps=true&geometries=geojson&annotations=true&overview=full"
		r = requests.get(req)
		route = r.json()["routes"][0]["geometry"]["coordinates"]
		return route
