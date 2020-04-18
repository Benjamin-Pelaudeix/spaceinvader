package fr.unilim.iut;

import fr.unilim.iut.moteurjeu.DessinJeu;
import fr.unilim.iut.moteurjeu.Jeu;
import fr.unilim.iut.moteurjeu.MoteurGraphique;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SpaceInvaders jeu = new SpaceInvaders(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
        jeu.initialiserJeu();
        DessinSpaceInvaders afficheur = new DessinSpaceInvaders(jeu);

        MoteurGraphique moteur = new MoteurGraphique(jeu, afficheur);
        moteur.lancerJeu(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
    }

}
