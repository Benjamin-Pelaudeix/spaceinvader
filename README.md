# :alien: Space Invaders
Mini-projet "Space Invaders" développé en Java dans le cadre du module de Conception M2104 de première année de DUT Informatique :computer:.

## Carnet de bord hebdomadaire

### Sommaire 

- [Semaine 27](https://github.com/Benjamin-Pelaudeix/spaceinvader#semaine-27)
- [Semaine 28](https://github.com/Benjamin-Pelaudeix/spaceinvader#semaine-28)
- [Semaine 29](https://github.com/Benjamin-Pelaudeix/spaceinvader#semaine-29)
- [Vacances](https://github.com/Benjamin-Pelaudeix/spaceinvader#vacances)
- [Glossaire](https://github.com/Benjamin-Pelaudeix/spaceinvader#glossaire)

### Semaine 27

_Fonctionnalités implémentées :_

 * Fonctionnalité n°1 :
   - Story n°1 :
      **Critères d'acceptances** : L'espace de jeu est en 2D.
   - Story n°2 :
      **Critères d'acceptances** : Un nouveau vaisseau est créé, et positionné aux coordonnées données.
      
_Modélisation UML :_

![Diagramme S27](/img/spaceinvadersS27.png)

_Nuage de mots :_

Non disponible en raison de la reprise du projet.

_Difficultés et remarques :_

Pas de difficultés particulières sachant que les parties sont détaillées. Même si parfois elles manquent de précision, au niveau des classes où il faut implémenter les méthodes demandées.

### Semaine 28

_Fonctionalités implémentées :_

* Fonctionnalité n°1 :
   - Story n°3 :
      **Critères d'acceptances** : Le vaisseau se déplace d'un pas vers la droite. Si le vaisseau se trouve sur la bordure droite de l'espace de jeu, le vaisseau doit rester immobile.
   - Story n°4 :
      **Critères d'acceptances** : Idem que la story n°3 mais pour le déplacement à gauche.
      
* Fonctionnalité n°2 :
  **Critères d'acceptances** : Dimensionner le vaisseau et gérer le déplacement de ce dernier suivant les différents cas (déplacement vers le haut, le bas, la gauche et la droite sans que le vaisseau ne déborde de l'espace de jeu).
  
_Modélisation UML :_

![Diagramme_S28](/img/spaceinvadersS28.png)

_Nuage de mots :_

Non disponible en raison de la reprise du projet.

_Difficultés et remarques :_

Aucune difficulté pour ce TP.

### Semaine 29

_Fonctionnalités implémentées :_

* Fonctionnalité n°3 :
   **Critères d'acceptances** : Pourvoir choisir la vitesse du vaisseau afin qu'il puisse se déplacer à une vitesse convenable dans l'application graphique.
   
_Modélisation UML :_

![Diagramme classe S29](/img/spaceinvadersS29.png)

_Nuage de mots :_

![Mots S29](/img/motsS29.PNG)

_Difficultés rencontrées :_

J'ai eu un problème qui m'a été remonté concernant mon .gitignore. En essayant de le corriger, mon projet a été corrompu. J'ai dû créer un nouveau dépôt GitHub pour héberger mon projet.

### Vacances

   #### 18/04/2020
_Fonctionnalités implémentées :_

* Fonctionnalité n°4 :
   **Critères d'acceptances** : Pourvoir tirer un missile depuis le vaisseau et le faire changer de position de manière autonome.

* Spike "Moteur Graphique" :
   **Critères d'acceptances** : Ajouter à notre mini-projet un moteur graphique.

_Modélisation UML :_

![Diagramme](/img/spaceinvader_vacs.png)


_Nuage de mots :_

Après Fonctionnalité n°4
![Nuage de mots](/img/motsVacF4.PNG)
Après Spike
![Nuage de mots avec moteur graphique](/img/motsVacs.PNG)

_Difficultés rencontrées :_

Aucune

#### 19/04/2020

_Fonctionnalités implémentées :_

* Fonctionnalité n°5 :
   **Critères d'acceptances** : Ajouter un envahisseur au jeu.
   
* Fonctionnalité n°6 :
   **Critères d'accptances** : Détecter les collisions entre deux sprites.
   
_Modélisation UML :_

![diagramme](/img/spaceinvaderVacF6.png)

_Nuage de mots :_

Fonctionnalité n°5
![nuage mot F5](/img/motsVacF5.PNG)
Fonctionnalité n°6
![nuage mot F6](/img/motsVacF6.PNG)

_Difficultés rencontrées :_

Aucune

#### 20/04/2020

_Fonctionnalités implémentées :_

* Fonctionnalité n°7 :
   **Critères d'acceptances** : Détecter la fin du jeu lorsque le missile touche l'envahisseur.
   
_Modélisation UML :_

![diagramme](/img/spaceinvaderF7.png)

_Nuage de mots :_

![mots F7](/img/motsF7.PNG)

_Difficultés rencontrées :_

Aucune


### Glossaire

:airplane: ``Vaisseau`` est un véhicule commandé par le joueur, pouvant se déplacer de droite à gauche et ayant la possibilité de lancer des missiles destinés à détruire le(s) envahisseurs.

:alien: ``Envahisseur`` est un ennemi qui apparaît à l'écran, se déplace automatiquement et qui doit être détruit par un missile lancé depuis le vaisseau du joueur.

:rocket: ``Missile`` est un projectile envoyé à la verticale par le vaisseau vers l'envahisseur dans le but de le détruire.

:dash: ``Vitesse`` est, pour ce projet, une vitesse instantanée définie comme un vecteur optenu en dérivant les coordonnées cartésiennes de la position par rapport au temps.

:runner: ``Sprite`` est, dans les jeux vidéos, un élément graphique qui peut se déplacer sur l'écran.

:boom: ``Collisions`` sont un aspect fondamental de tout jeu d'action ou d'animation qui prendront en paramètre 2 objets. Ces fonctions renverront simplement un booléen.

### Références

Sujet SpaceInvaders créé par @iblasquez pour le compte du module M2104 de DUT Informatique :arrow_right: [Sujet](https://github.com/iblasquez/tdd_spaceInvaders.git).

Définition des **collisions** récupérée sur ce site :arrow_right: [Lien vers jeux.developpez.com](https://jeux.developpez.com/tutoriels/theorie-des-collisions/).

IDE utilisé pour ce projet :arrow_right: [IntelliJ](https://www.jetbrains.com/fr-fr/idea/download/#section=windows).

JDK utilisé pour ce projet :arrow_right: [JDK 8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).
