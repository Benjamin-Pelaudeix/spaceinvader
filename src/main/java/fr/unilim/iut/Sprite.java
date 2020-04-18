package fr.unilim.iut;

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

    public void deplacerVersLaDroite() {
        this.origine.changerAbscisse(this.origine.abscisse()+vitesse);
    }

    public int abscisseLaPlusAGauche() {
        return this.origine.abscisse();
    }

    public int ordonneeLaPlusHaute() {
        return this.origine.ordonnee();
    }

    public void deplacerVersLaGauche() {
        this.origine.changerAbscisse(this.origine.abscisse()-vitesse);
    }

    public void positionner(int x, int y) {
        this.origine.changerAbscisse(x);
        this.origine.changerOrdonnee(y);
    }

    public int longueur() {
        return this.dimension.longueur();
    }
}
