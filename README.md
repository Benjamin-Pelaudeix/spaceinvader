# Space Invaders
TPs Space Invaders - M2104
Implémentation du projet space invaders en TDD à partir de https://github.com/iblasquez/tdd_spaceInvaders.

## Carnet de bord hebdomadaire

### Sommaire 

- [Semaine 27](https://github.com/Benjamin-Pelaudeix/spaceinvaders#semaine-27)
- [Semaine 28](https://github.com/Benjamin-Pelaudeix/spaceinvaders#semaine-28)
- Semaine 29
-  [Glossaire](https://github.com/Benjamin-Pelaudeix/spaceinvaders#glossaire)

### Semaine 27

_Fonctionnalités implémentées :_

 * Fonctionnalité n°1 :
   - Story n°1 :
      **Critères d'acceptances** : L'espace de jeu est en 2D.
   - Story n°2 :
      **Critères d'acceptances** : Un nouveau vaisseau est créé, et positionné aux coordonnées données.
      
_Modélisation UML :_

![Diagramme S27](/img/spaceinvaders.png)

_Nuage de mots :_

![Nuage de mot S27](/img/wordCloudS27.jpg)

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

![Diagramme_S28](/img/spaceinvaders_S28.png)

_Nuage de mots :_

![Nuage de mots S28](/img/wordCloudS28.png)

_Difficultés et remarques :_

Aucune difficulté pour ce TP.

### Semaine 29

TP jeudi de 10h à 12h

### Glossaire

``Vaisseau`` est un véhicule commandé par le joueur, pouvant se déplacer de droite à gauche et ayant la possibilité de lancer des missiles destinés à détruire le(s) envahisseurs.

``Envahisseur`` est un ennemi qui apparaît à l'écran, se déplace automatiquement et qui doit être détruit par un missile lancé depuis le vaisseau du joueur.

``Missile`` est un projectile envoyé à la verticale par le vaisseau vers l'envahisseur dans le but de le détruire.
