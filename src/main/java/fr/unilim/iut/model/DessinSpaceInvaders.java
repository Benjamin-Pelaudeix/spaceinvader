package fr.unilim.iut.model;

import fr.unilim.iut.moteurjeu.DessinJeu;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

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
            if (jeu.aDesMissile()) {
                List<Missile> missiles = this.jeu.recupererMissile();
                this.dessinerUnMissile(missiles, image);
            }
            if (jeu.aUnEnvahisseur()) {
                Envahisseur envahisseur = this.jeu.recupererEnvahisseur();
                this.dessinerEnvahisseur(envahisseur,image);
            }
        }
    }

    private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage img) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();

        crayon.setColor(Color.gray);
        crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(), vaisseau.hauteur());
    }

    private void dessinerUnMissile(List<Missile> missiles, BufferedImage img) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();

        crayon.setColor(Color.blue);
        for (Missile missile : missiles) {
            crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.longueur(), missile.hauteur());
        }
    }

    private void dessinerEnvahisseur(Envahisseur envahisseur, BufferedImage img) {
        Graphics2D crayon = (Graphics2D) img.getGraphics();

        crayon.setColor(Color.green);
        crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(), envahisseur.longueur(), envahisseur.hauteur());
    }
}

