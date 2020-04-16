package fr.unilim.iut;

public class Position {

    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int abscisse() {
        return x;
    }

    public int ordonnee() {
        return y;
    }

    public void changerAbscisse(int nouvelleAbscisse) {
        this.x = nouvelleAbscisse;
    }

    public void changerOrdonnee(int nouvelleOrdonnee) {
        this.y = nouvelleOrdonnee;
    }
}
