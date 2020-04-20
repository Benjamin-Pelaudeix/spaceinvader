package fr.unilim.iut;

import fr.unilim.iut.model.*;
import fr.unilim.iut.utils.DebordementEspaceJeuException;
import fr.unilim.iut.utils.HorsEspaceJeuException;
import fr.unilim.iut.utils.MissileException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceInvadersTest {

    private SpaceInvaders spaceinvaders;

    @Before
    public void initialisation() {
        spaceinvaders = new SpaceInvaders(15,10);
    }

    @Test
    public void test_AuDebut_JeuSpaceInvaderEstVide() {
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());

    }

    @Test
    public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
        //Act
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);

        //Assert
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".......VVV.....\n" +
                ".......VVV.....\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {
        try {
            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1),new Position(15,9), 1);
            fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }


        try {
            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(-1,9), 1);
            fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }


        try {
            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(14,10), 1);
            fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }


        try {
            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(1,1), new Position(14,-1), 1);
            fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {
        }

    }

    @Test
    public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionnerDansEspaceJeu() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(7,9), 1);
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".......VVV.....\n" +
                ".......VVV.....\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {
        try {
            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(9,2), new Position(7,9), 1);
            fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
        }
        catch (final DebordementEspaceJeuException e) {
        }

        try {
            spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,4), new Position(7,1), 1);
            fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
        }
        catch (final DebordementEspaceJeuException e) {
        }
    }

    @Test
    public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9), 3);
        spaceinvaders.deplacerVaisseauVersLaGauche();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "....VVV........\n" +
                "....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(0,9), 3);
        spaceinvaders.deplacerVaisseauVersLaGauche();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "VVV............\n" +
                "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(1,9), 3);
        spaceinvaders.deplacerVaisseauVersLaGauche();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "VVV............\n" +
                "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(7,9),3);
        spaceinvaders.deplacerVaisseauVersLaDroite();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "..........VVV..\n" +
                "..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2), new Position(12,9), 3);
        spaceinvaders.deplacerVaisseauVersLaDroite();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "............VVV\n" +
                "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3,2),new Position(10,9),3);
        spaceinvaders.deplacerVaisseauVersLaDroite();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "............VVV\n" +
                "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_MissibleBienPositionneAuMilieuDuVaisseau() {
        //spaceinvaders initialisé dans @Before
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9),2);
        spaceinvaders.tirerUnMissile(new Dimension(3,2),2);
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".......MMM.....\n" +
                ".......MMM.....\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test(expected = MissileException.class)
    public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2),new Position(5,9), 1);
        spaceinvaders.tirerUnMissile(new Dimension(7,9),1);
    }

    @Test
    public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9),2);
        spaceinvaders.tirerUnMissile(new Dimension(3,2),2);
        spaceinvaders.deplacerMissile();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".......MMM.....\n" +
                ".......MMM.....\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_MissileDisparait_QuandIlCommenceASortirDeEsapceJeu() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9),1);
        spaceinvaders.tirerUnMissile(new Dimension(3,2),1);

        for (int i = 0; i <= 6; i++) {
            spaceinvaders.deplacerMissile();
        }
        spaceinvaders.deplacerMissile();

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_EnvahisseurCorrectementPositionnerDansEspaceJeu() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9),1);
        spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(8,2), 1);

        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "........E......\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_EnvahisseurAvance_SeDeplacerVersLaGauche() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9),1);
        spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(8,2),1);
        spaceinvaders.deplacerEnvahisseurVersLaGauche();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                ".......E.......\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_EnvahisseurAvance_SeDeplacerVersLaDroite() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9),1);
        spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(8,2),1);
        spaceinvaders.deplacerEnvahisseurVersLaDroite();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                ".........E.....\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_EnvahisseurImmobile_SeDeplacerVersLaGauche() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9),1);
        spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(0,2),1);
        spaceinvaders.deplacerEnvahisseurVersLaGauche();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "E..............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_EnvahisseurImmobile_SeDeplacerVersLaDroite() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9),1);
        spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(13,2), 1);
        spaceinvaders.deplacerEnvahisseurVersLaDroite();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "..............E\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_EnvahisseurAvancePartiellement_SeDeplacerVersLaGauche() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9),1);
        spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(3,2),4);
        spaceinvaders.deplacerEnvahisseurVersLaGauche();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "E..............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_EnvahisseurAvancePartiellement_SeDeplacerVersLaDroite() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9),1);
        spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(10,2),4);
        spaceinvaders.deplacerEnvahisseurVersLaDroite();
        assertEquals("" +
                "...............\n" +
                "...............\n" +
                "..............E\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                "...............\n" +
                ".....VVVVVVV...\n" +
                ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

    @Test
    public void test_UnNouveauEnvahisseurPositionneHorsEspaceJeu_DoitLeverExceptionHorsEspaceJeu() {
        try {
            spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(-1,2),1);
            fail("L'envahisseur est positionné trop à gauche : doit lever HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {

        }
        try {
            spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(16,2),1);
            fail("L'envahisseur est positionné trop à droit : doit lever HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {

        }
        try {
            spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(8,-1),1);
            fail("L'envahisseur est positionné trop en haut : doit lever HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {

        }
        try {
            spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,1), new Position(8,13),1);
            fail("L'envahisseur est positionné trop en bas : doit lever HorsEspaceJeuException");
        } catch (final HorsEspaceJeuException e) {

        }
    }

    @Test
    public void test_UnNouveauEnvahisseurPositionneDansEspaceJeuAvecUneDimensionTropGrande_DoitLeverExceptionDebordementEspaceJeu() {
        try {
            spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 1), new Position(13, 2), 1);
            fail("L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
        } catch (final DebordementEspaceJeuException e) {

        }
        try {
            spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(1,3), new Position(8,1),1);
            fail("l'envahisseur déborde de l'espace jeu vers le haut à cause de sa longueur");
        } catch (final DebordementEspaceJeuException e) {

        }
    }

    @Test
    public void test_MissileToucheSprite_DoitDeclencherFinDuJeu() {
        spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7,2), new Position(5,9), 1);
        spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(4,3), new Position(5,4), 1);
        spaceinvaders.tirerUnMissile(new Dimension(5,3), 2);
        spaceinvaders.deplacerMissile();
        spaceinvaders.deplacerMissile();

        spaceinvaders.detecterCollisionMissileEnvahisseur();

        assertTrue(spaceinvaders.etreFini());
    }
}
