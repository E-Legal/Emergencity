## Introduction

Ce repository a été créé dans le but de présenter l'utilisation de Docker dans le cadre du developpement d'application mobile.

### Prérequis :

Avoir docker installé sur sa machine et configuré pour autoriser l'utilisation de volume dans le repertoire de travail que vous allez utiliser.

Avoir un compte expo : [https://expo.io/signup](https://expo.io/signup)

Avoir l'application expo sur son mobile.

Avoir son notebook et son smartphone sur le même réseau et de vous munir de votre Ipv4.

## Installation de React-Native

Pour lancer le container :

	$ docker-compose up -d

React-native est préinstallé en global sur le build fourni.

On peut y accèder avec la commande :

	$ docker exec -it react-native bash

## Simulation 

Il convient une fois dans notre docker de renseigner l'IP

	$ export env REACT_NATIVE_PACKAGER_HOSTNAME=##IP_MACHINE##