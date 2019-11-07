## Introduction

Ce repository a été créé dans le but de présenter l'utilisation de Docker dans le cadre du developpement d'application web.

### Prérequis :

Avoir docker installé sur sa machine et configuré pour autoriser l'utilisation de volume dans le repertoire de travail que vous allez utiliser.

### Lancement du Docker:
Pour lancer le container :

	$ docker build -t web-emergencity .
    
    A partir de ce moment le docker va installer les différent paquet nécessaire au fonctionnement de l'application web.
    Une fois fini il ne vous reste plus qu'a faire.

    $ docker run -it -p 8080:8080 web-emergencity

    Il est important de ne rien avoir bind sur notre port 8080 afin de pouvoir simple analyser notre application à l'adresse :
    * $ localhost:8080
