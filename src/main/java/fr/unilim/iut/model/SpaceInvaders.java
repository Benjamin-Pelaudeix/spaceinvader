package fr.unilim.iut.model;

import fr.unilim.iut.moteurjeu.Commande;
import fr.unilim.iut.moteurjeu.Jeu;
import fr.unilim.iut.utils.DebordementEspaceJeuException;
import fr.unilim.iut.utils.HorsEspaceJeuException;
import fr.unilim.iut.utils.MissileException;
import javafx.geometry.Pos;

import static fr.unilim.iut.model.Constante.MARQUE_ENVAHISSEUR;
import static fr.unilim.iut.model.Constante.MARQUE_MISSILE;

public class SpaceInvaders implements Jeu {

    public static final int PREMIERE_MARQUE_PAR_LIGNE = 0;
    int longueur;
    int hauteur;
    Vaisseau vaisseau;
    Missile missile;
    Envahisseur envahisseur;
    Collision collision = new Collision();
    boolean finDePartie;

    public SpaceInvaders(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
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
        else if (this.aUnEnvahisseurQuiOccupeLaPosition(x,y)) {
            marque = MARQUE_ENVAHISSEUR;
        }
        else {
            marque = Constante.MARQUE_VIDE;
        }
        return marque;
    }

    //MISSILE

    private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
        return this.aUnMissile() && missile.occupeLaPosition(x,y);
    }

    public boolean aUnMissile() {
        return missile!=null;
    }

    public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
        if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur) {
            throw new MissileException("Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
        }
        this.missile = this.vaisseau.tirerUnMissile(dimensionMissile,vitesseMissile);
    }

    public void deplacerMissile() {
        this.missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
    }

    public Missile recupererMissile() {
        return this.missile;
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

    public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {
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
        envahisseur = new Envahisseur(dimension, position, vitesse);
    }

    public boolean aUnEnvahisseur() {
        return envahisseur != null;
    }

    public boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
        return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x,y);
    }

    public Envahisseur recupererEnvahisseur() {
        return this.envahisseur;
    }

    public void deplacerEnvahisseurVersLaGauche() {
        if (0 < envahisseur.abscisseLaPlusAGauche()) {
            envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
        }
        if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(),envahisseur.ordonneeLaPlusHaute())) {
            envahisseur.positionner(PREMIERE_MARQUE_PAR_LIGNE,envahisseur.ordonneeLaPlusHaute());
        }
    }

    public void deplacerEnvahisseurVersLaDroite() {
        if (envahisseur.abscisseLaPlusADroite() < longueur-1) {
            envahisseur.deplacerHorizontalementVers(Direction.DROITE);
            if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) {
                envahisseur.positionner(longueur-envahisseur.longueur(),envahisseur.ordonneeLaPlusHaute());
            }
        }

    }

    public void deplacerEvahisseur() {
        if (envahisseur.abscisseLaPlusADroite() >= longueur-1) {
            envahisseur.setDirection(Direction.GAUCHE);
        }
        if (0 >= envahisseur.abscisseLaPlusAGauche()) {
            envahisseur.setDirection(Direction.DROITE);

        }
        envahisseur.deplacerHorizontalementVers(envahisseur.getDirection());
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
        if (this.aUnEnvahisseur() && this.aUnMissile() && collision.detecterCollision(envahisseur,missile)) {
            envahisseur = null;
            this.finDePartie = true;
        }
    }

    @Override
    public void evoluer(Commande commandeUser) {
        //COMMANDES
        if (commandeUser.gauche) {
            this.deplacerVaisseauVersLaGauche();
        }
        if (commandeUser.droite) {
            this.deplacerVaisseauVersLaDroite();
        }
        if (commandeUser.tir && !this.aUnMissile()) {
            this.tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),Constante.MISSILE_VITESSE);
        }
        //DETECTIONS
        detecterCollisionMissileEnvahisseur();
        //DEPLACEMENTS AUTOMATIQUES
        if (this.aUnMissile()) {
            this.deplacerMissile();
        }
        if (!aUnEnvahisseur()) {
            this.positionnerUnNouveauEnvahisseur(new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR), new Position(longueur/2, 100), Constante.ENVAHISSEUR_VITESSE);
        }
        if (aUnEnvahisseur()) {
            this.deplacerEvahisseur();
        }

    }

    @Override
    public boolean etreFini() {
        return this.finDePartie;
    }



}
