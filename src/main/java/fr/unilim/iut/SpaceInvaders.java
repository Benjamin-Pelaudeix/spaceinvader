package fr.unilim.iut;

import fr.unilim.iut.utils.DebordementEspaceJeuException;
import fr.unilim.iut.utils.HorsEspaceJeuException;

public class SpaceInvaders {

    public static final char MARQUE_VAISSEAU = 'V';
    public static final char MARQUE_VIDE = '.';
    public static final char MARQUE_FIN_LIGNE = '\n';
    public static final int PREMIERE_MARQUE_PAR_LIGNE = 0;
    int longueur;
    int hauteur;
    Vaisseau vaisseau;

    public SpaceInvaders(int longueur, int hauteur) {
        this.longueur = longueur;
        this.hauteur = hauteur;
    }

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
            espaceDeJeu.append(MARQUE_FIN_LIGNE);
        }
        return espaceDeJeu.toString();
    }

    public char recupererMarqueDeLaPosition(int x, int y) {
        char marque;
        if (this.aUnVaisseauQuiOccupeLaPosition(x, y)) {
            marque = MARQUE_VAISSEAU;
        }
        else {
            marque = MARQUE_VIDE;
        }
        return marque;
    }

    public boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
        return this.aUnVaisseau() && vaisseau.occupeLaPosition(x,y);
    }

    public boolean aUnVaisseau() {
        return vaisseau!=null;
    }

    public boolean estDansEspaceJeu(int x, int y) {
        return (x>=0) && (x<longueur) && ((y>=0) && (y<hauteur));
    }

    public void deplacerVaisseauVersLaDroite() {
        if (vaisseau.abscisseLaPlusADroite() < longueur-1) {
            vaisseau.deplacerVersLaDroite();
            if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
                vaisseau.positionner(longueur-vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
            }
        }
    }

    public void deplacerVaisseauVersLaGauche() {
        if (0 < vaisseau.abscisseLaPlusAGauche()) {
            vaisseau.deplacerVersLaGauche();
        }
        if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(),vaisseau.ordonneeLaPlusHaute())) {
            vaisseau.positionner(0,vaisseau.ordonneeLaPlusHaute());
        }
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

    public void initialiserJeu() {
        Position positionVaisseau = new Position(this.longueur/2,this.hauteur-1);
        Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
        positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);
    }
}
