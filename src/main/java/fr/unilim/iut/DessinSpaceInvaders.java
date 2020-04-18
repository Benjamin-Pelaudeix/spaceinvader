package fr.unilim.iut;

import fr.unilim.iut.moteurjeu.DessinJeu;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DessinSpaceInvaders implements DessinJeu {
    private final SpaceInvaders jeu;

    public DessinSpaceInvaders(SpaceInvaders jeu) {
        this.jeu = jeu;
    }

    @Override
    public void dessiner(BufferedImage image) {
        if (!jeu.etreFini()) {
            if (jeu.aUnVaisseau()) {
                Vaisseau vaisseau = this.jeu.recupererVaisseau();
                this.dessinerUnVaisseau(vaisseau, image);
            }
            if (jeu.aUnMissile()) {
                Missile missile = this.jeu.recupererMissile();
                this.dessinerUnMissile(missile, image);
            }
        }
    }

    private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage img) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();

        crayon.setColor(Color.gray);
        crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(), vaisseau.hauteur());
    }

    private void dessinerUnMissile(Missile missile, BufferedImage img) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();

        crayon.setColor(Color.blue);
        crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.longueur(), missile.hauteur());

    }
}

