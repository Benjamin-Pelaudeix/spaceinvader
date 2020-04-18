package fr.unilim.iut;

import sun.java2d.windows.GDIRenderer;

import java.security.DigestException;

import static fr.unilim.iut.Direction.HAUT;

public abstract class Sprite {
    protected int vitesse;
    Position origine;
    Dimension dimension;

    public Sprite(Dimension dimension, Position origine, int vitesse) {
        this.dimension = dimension;
        this.origine = origine;
        this.vitesse = vitesse;
    }

    public boolean occupeLaPosition(int x, int y) {
       return (estAbscisseCouverte(x) && estOrdonneeCouverte(y));
    }

    public boolean estOrdonneeCouverte(int y) {
        return (ordonneeLaPlusBasse()<=y) && (y<=ordonneeLaPlusHaute());
    }

    public int ordonneeLaPlusBasse() {
        return this.origine.ordonnee()-this.dimension.hauteur()+1;
    }

    public boolean estAbscisseCouverte(int x) {
        return (abscisseLaPlusAGauche() <= x) && (x <= abscisseLaPlusADroite());
    }

    public int abscisseLaPlusADroite() {
        return this.origine.abscisse()+this.dimension.longueur()-1;
    }

    public int abscisseLaPlusAGauche() {
        return this.origine.abscisse();
    }

    public int ordonneeLaPlusHaute() {
        return this.origine.ordonnee();
    }

    public void deplacerVerticalementVers(Direction direction) {
        this.origine.changerOrdonnee(this.origine.ordonnee()+direction.valeur()*vitesse);
    }

    public void deplacerHorizontalementVers(Direction direction) {
        this.origine.changerAbscisse(this.origine.abscisse()+direction.valeur()*vitesse);
    }

    public void positionner(int x, int y) {
        this.origine.changerAbscisse(x);
        this.origine.changerOrdonnee(y);
    }

    public int longueur() {
        return this.dimension.longueur();
    }

    public int hauteur() {
        return this.dimension.hauteur();
    }
}
