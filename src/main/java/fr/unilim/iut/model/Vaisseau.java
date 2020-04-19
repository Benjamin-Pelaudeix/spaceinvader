package fr.unilim.iut.model;

import fr.unilim.iut.utils.MissileException;

public class Vaisseau extends Sprite {

    public Vaisseau(Dimension dimension, Position origine, int vitesse) {
        super(dimension, origine, vitesse);
    }

    public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
        if (this.longueur() > dimensionMissile.longueur()) {
            Position positionOrigineMissile = calculerPositionDeTirDuMissile(dimensionMissile);
            return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
        }
        else {
            throw new MissileException("La longueur du missile est supérieure à celle du vaisseau");
        }
    }

    private Position calculerPositionDeTirDuMissile(Dimension dimensionMissile) {
        int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
        int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur / 2);
        int ordonneeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
        return new Position(abscisseOrigineMissile, ordonneeOrigineMissile);
    }
}
