class Feu(object):
	"""docstring for Feu."""
	def __init__(self, lat, lng, ville):
		super(Feu, self).__init__()
		if (type(lat) != type(float()) or type(lng) != type(float())):
			raise "Erreur: lat and lng doivent etre des nombre floatant"
		if (type(ville) != type(str())):
			raise "Erreur: ville doit etre une string"
		self.lat = lat
		self.lng = lng
		self.ville = ville

	def get_lat(self):
		return self.lat

	def get_lng(self):
		return self.lng

	def get_coordonee(self):
		return {'lat': self.lat, 'lng': self.lng}

	def get_ville(self):
		return self.ville
