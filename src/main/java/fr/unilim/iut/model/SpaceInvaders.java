package fr.unilim.iut.model;

import fr.unilim.iut.moteurjeu.Commande;
import fr.unilim.iut.moteurjeu.Jeu;
import fr.unilim.iut.utils.DebordementEspaceJeuException;
import fr.unilim.iut.utils.HorsEspaceJeuException;
import fr.unilim.iut.utils.MissileException;

import java.util.ArrayList;
import java.util.List;

import static fr.unilim.iut.model.Constante.MARQUE_ENVAHISSEUR;
import static fr.unilim.iut.model.Constante.MARQUE_MISSILE;

public class SpaceInvaders implements Jeu {

    public static final int PREMIERE_MARQUE_PAR_LIGNE = 0;
    int longueur;
    int hauteur;
    Vaisseau vaisseau;
    List<Missile> missiles;
    List<Envahisseur> envahisseurs;
    Collision collision = new Collision();
    boolean finDePartie;
    private long cooldownMissile;

    public SpaceInvaders(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
        this.missiles = new ArrayList<>();
        this.envahisseurs = new ArrayList<>();
    }

    //AFFICHAGE CONSOLE

    @Override
    public String toString() {
        return recupererEspaceJeuDansChaineASCII();
    }

    public String recupererEspaceJeuDansChaineASCII() {
        StringBuilder espaceDeJeu = new StringBuilder();
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < longueur; x++) {
                espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
            }
            espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
        }
        return espaceDeJeu.toString();
    }

    public char recupererMarqueDeLaPosition(int x, int y) {
        char marque;
        if (this.aUnVaisseauQuiOccupeLaPosition(x, y)) {
            marque = Constante.MARQUE_VAISSEAU;
        }
        else if (this.aUnMissileQuiOccupeLaPosition(x,y)) {
            marque = MARQUE_MISSILE;
        }
        else if (this.aDesEnvahisseursQuiOccupeLaPosition(x,y)) {
            marque = MARQUE_ENVAHISSEUR;
        }
        else {
            marque = Constante.MARQUE_VIDE;
        }
        return marque;
    }

    //MISSILE

    private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
        if (this.aDesMissile()) {
            for (Missile missile: missiles) {
                if (missile.occupeLaPosition(x,y)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean aDesMissile() {
        return !this.missiles.isEmpty();
    }

    public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
        if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur) {
            throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
        }
        if (System.currentTimeMillis() > this.cooldownMissile + Constante.TEMPS_ENTRE_DEUX_MISSILES) {
            missiles.add(this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile));
            this.cooldownMissile = System.currentTimeMillis();
        }
    }

    public void deplacerMissile(Direction direction) {
        for (Missile missile : this.missiles) {
            missile.deplacerVerticalementVers(direction);
        }
        supprimerMissilesHorsEspaceJeu();
    }

    public void supprimerMissilesHorsEspaceJeu() {
        boolean rechercher;

        do {
            rechercher = false;
            for (Missile missile: this.missiles) {
                if (missile.ordonneeLaPlusBasse() <= 0) {
                    this.missiles.remove(missile);
                    rechercher = true;
                    break;
                }
            }
        } while (rechercher);
    }

    public List<Missile> recupererMissile() {
        return this.missiles;
    }

    //VAISSEAU

    public boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
        return this.aUnVaisseau() && vaisseau.occupeLaPosition(x,y);
    }

    public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

        int x = position.abscisse();
        int y = position.ordonnee();

        if (!estDansEspaceJeu(x, y))
            throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

        int longueurVaisseau = dimension.longueur();
        int hauteurVaisseau = dimension.hauteur();

        if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
            throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
        if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
            throw new DebordementEspaceJeuException("Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

        vaisseau = new Vaisseau(dimension, position, vitesse);
    }

    public void deplacerVaisseauVersLaDroite() {
        if (vaisseau.abscisseLaPlusADroite() < longueur-1) {
            vaisseau.deplacerHorizontalementVers(Direction.DROITE);
            if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
                vaisseau.positionner(longueur-vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
            }
        }
    }

    public void deplacerVaisseauVersLaGauche() {
        if (0 < vaisseau.abscisseLaPlusAGauche()) {
            vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
        }
        if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(),vaisseau.ordonneeLaPlusHaute())) {
            vaisseau.positionner(PREMIERE_MARQUE_PAR_LIGNE,vaisseau.ordonneeLaPlusHaute());
        }
    }
    public Vaisseau recupererVaisseau() {
        return this.vaisseau;
    }
    public boolean aUnVaisseau() {
        return vaisseau!=null;
    }

    //ENVAHISSEUR

    public void positionnerUneNouvelleLigneEnvahisseurs(Dimension dimension, Position position, int vitesse, int nombre) {
        int x = position.abscisse();
        int y = position.ordonnee();

        if (!estDansEspaceJeu(x,y)) {
            throw new HorsEspaceJeuException("L'envahisseur est positionné hors de l'espace jeu");
        }

        int longueurEnvahisseur = dimension.longueur();
        int hauteurEnvahisseur = dimension.hauteur();

        if (!estDansEspaceJeu(x+longueurEnvahisseur-1,y)) {
            throw new DebordementEspaceJeuException("L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
        }
        if (!estDansEspaceJeu(x,y-hauteurEnvahisseur+1)) {
            throw new DebordementEspaceJeuException("L'envahisseur déborde de l'espace jeu vers le bas à cause de sa longueur");
        }
        envahisseurs.add(new Envahisseur(dimension,position,vitesse));
        if (nombre > 1) {
            for (int i = 1; i < nombre; i++) {
                envahisseurs.add(new Envahisseur(dimension, new Position(position.abscisse()+2, position.ordonnee()),1));
            }
        }

    }

    public boolean aDesEnvahisseurs() {
        return !envahisseurs.isEmpty();
    }

    public boolean aDesEnvahisseursQuiOccupeLaPosition(int x, int y) {
        if (aDesEnvahisseurs()) {
            for (Envahisseur envahisseur: envahisseurs) {
                if (envahisseur.occupeLaPosition(x,y)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Envahisseur> recupererEnvahisseur() {
        return this.envahisseurs;
    }

    public void deplacerEnvahisseurVersLaGauche() {
        if (0 < envahisseurs.get(0).abscisseLaPlusAGauche()) {
            for (Envahisseur envahisseur:envahisseurs) {
                envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
            }

        }
        if (!estDansEspaceJeu(envahisseurs.get(0).abscisseLaPlusAGauche(), envahisseurs.get(0).ordonneeLaPlusHaute())) {
            for (Envahisseur envahisseur:envahisseurs) {
                envahisseur.positionner(PREMIERE_MARQUE_PAR_LIGNE, envahisseur.ordonneeLaPlusHaute());
            }
        }
    }

    public void deplacerEnvahisseurVersLaDroite() {
        if (envahisseurs.get(Constante.NOMBRE_ENVAHISSEURS-1).abscisseLaPlusADroite() <= longueur-1) {
            for (Envahisseur envahisseur:envahisseurs) {
                envahisseur.deplacerHorizontalementVers(Direction.DROITE);
                if (!estDansEspaceJeu(envahisseurs.get(Constante.NOMBRE_ENVAHISSEURS-1).abscisseLaPlusADroite(), envahisseurs.get(Constante.NOMBRE_ENVAHISSEURS-1).ordonneeLaPlusHaute())) {
                    envahisseurs.get(Constante.NOMBRE_ENVAHISSEURS-1).positionner(longueur- envahisseurs.get(Constante.NOMBRE_ENVAHISSEURS-1).longueur()-1, envahisseurs.get(Constante.NOMBRE_ENVAHISSEURS-1).ordonneeLaPlusHaute()-1);
                }
            }

        }

    }

    public void deplacerEvahisseur() {
        if (envahisseurs.get(Constante.NOMBRE_ENVAHISSEURS-1).abscisseLaPlusADroite() >= longueur-1) {
            for (Envahisseur envahisseur:envahisseurs) {
                envahisseur.setDirection(Direction.GAUCHE);
            }

        }
        if (0 >= envahisseurs.get(Constante.NOMBRE_ENVAHISSEURS-1).abscisseLaPlusAGauche()) {
            for (Envahisseur envahisseur:envahisseurs) {
                envahisseur.setDirection(Direction.DROITE);
            }


        }
        for (Envahisseur envahisseur:envahisseurs) {
            envahisseur.deplacerHorizontalementVers(envahisseur.getDirection());
        }

    }


    //JEU

    public void initialiserJeu() {
        this.finDePartie = false;
        Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
        Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
        positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
    }

    public boolean estDansEspaceJeu(int x, int y) {
        return (x>=0) && (x<longueur) && ((y>=0) && (y<hauteur));
    }

    public void detecterCollisionMissileEnvahisseur() {
        for (Envahisseur envahisseur:envahisseurs) {
            for (Missile missile: this.missiles) {
                if (this.aDesEnvahisseurs() && this.aDesMissile() && (new Collision()).detecterCollision(envahisseur, missile)) {
                    this.envahisseurs.remove(envahisseur);
                    this.missiles.remove(missile);
                    this.finDePartie = true;
                    break;
                }
            }
        }

    }

    @Override
    public void evoluer(Commande commandeUser) {
        //COMMANDES
        if (commandeUser != null) {
            if (commandeUser.gauche) {
                this.deplacerVaisseauVersLaGauche();
            }
            if (commandeUser.droite) {
                this.deplacerVaisseauVersLaDroite();
            }
            if (commandeUser.tir) {
                this.tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),Constante.MISSILE_VITESSE);
            }
        }
        //DETECTIONS
        this.detecterCollisionMissileEnvahisseur();
        //DEPLACEMENTS AUTOMATIQUES
        if (this.aDesMissile()) {
            this.deplacerMissile(Direction.HAUT_ECRAN);
        }
        if (!aDesEnvahisseurs()) {
            this.positionnerUneNouvelleLigneEnvahisseurs(new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR), new Position(longueur/2, 100), Constante.ENVAHISSEUR_VITESSE, Constante.NOMBRE_ENVAHISSEURS);
        }
        if (aDesEnvahisseurs()) {
            this.deplacerEvahisseur();
        }

    }

    @Override
    public boolean etreFini() {
        return this.finDePartie;
    }



}
