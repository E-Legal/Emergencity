from Feux import Feu

#test bon type lat et lng
statut = "OK"
try:
	feu = Feu(1.1, 1.1, "ville")
except Exception as e:
	statut = "Erreur"

print("[" + statut + "] Bon type lat et/ou lng")


#test mauvais type lat et lng
statut = "Erreur"
try:
	feu = Feu(1, 1, "ville")
except Exception as e:
	statut = "OK"

print("[" + statut + "] Mauvais type lat et/ou lng")

#test bon type ville
statut = "OK"
try:
	feu = Feu(1.3, 1.1, "MAR")
except Exception as e:
	statut = "Erreur"

print("[" + statut + "] Bon type ville")

#test mauvais type ville
statut = "Erreur"
try:
	feu = Feu(1.3, 1.1, 12)
except Exception as e:
	statut = "OK"

print("[" + statut + "] Mauvais type ville")
