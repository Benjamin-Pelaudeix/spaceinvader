package fr.unilim.iut.model;

public class Envahisseur extends Sprite {

    Direction direction;

    public Envahisseur(Dimension dimension, Position origine, int vitesse) {
        super(dimension, origine, vitesse);
        this.direction = Direction.DROITE;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
