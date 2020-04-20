package fr.unilim.iut.model;

public class Collision {

    public boolean detecterCollision(Sprite spriteTouche, Sprite spriteQuiTouche) {
        return this.collisionSprites(spriteTouche,spriteQuiTouche);
    }

    private boolean collisionSprites(Sprite spriteTouche, Sprite spriteQuiTouche) {
        return (this.collisionSpritesEnBas(spriteTouche, spriteQuiTouche) && this.collisionSpritesAGaucheOuADroite(spriteTouche,spriteQuiTouche)) || (this.collisionSpritesEnHaut(spriteTouche,spriteQuiTouche) && this.collisionSpritesAGaucheOuADroite(spriteTouche,spriteQuiTouche));
    }

    private boolean collisionSpritesEnHaut(Sprite spriteTouche, Sprite spriteQuiTouche) {
        return (spriteTouche.ordonneeLaPlusBasse() <= spriteQuiTouche.ordonneeLaPlusHaute() && spriteQuiTouche.ordonneeLaPlusBasse() <= spriteTouche.ordonneeLaPlusHaute());
    }

    private boolean collisionSpritesAGaucheOuADroite(Sprite spriteTouche, Sprite spriteQuiTouche) {
        return (spriteTouche.abscisseLaPlusAGauche() <= spriteQuiTouche.abscisseLaPlusADroite() && spriteQuiTouche.abscisseLaPlusAGauche() <= spriteTouche.abscisseLaPlusADroite());
    }

    private boolean collisionSpritesEnBas(Sprite spriteTouche, Sprite spriteQuiTouche) {
        return (spriteTouche.ordonneeLaPlusBasse() <= spriteQuiTouche.ordonneeLaPlusHaute() && spriteQuiTouche.ordonneeLaPlusBasse() <= spriteTouche.ordonneeLaPlusHaute());
    }
}
