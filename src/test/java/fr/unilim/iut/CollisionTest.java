package fr.unilim.iut;

import fr.unilim.iut.model.*;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert.*;

import static org.junit.Assert.assertTrue;

public class CollisionTest {

    Collision collision;

    @Before
    public void initialisation() {
        collision = new Collision();
    }

    @Test
    public void test_MissileToucheSpriteParLeBas() {
        Envahisseur envahisseur = new Envahisseur(new Dimension(1,1), new Position(8,2),2);
        Missile missileVaisseau = new Missile(new Dimension(1,1), new Position(8,2), 2);

        assertTrue(collision.detecterCollision(envahisseur,missileVaisseau));
    }

    @Test
    public void test_MissileToucheSpriteParLaGauche() {
        Envahisseur envahisseur = new Envahisseur(new Dimension(2,2), new Position(8,2), 2);
        Missile missileVaisseau = new Missile(new Dimension(1,2), new Position(9,1),2);

        assertTrue(collision.detecterCollision(envahisseur,missileVaisseau));
    }

    @Test
    public void test_MissileToucheSpriteParLaDroite() {
        Envahisseur envahisseur = new Envahisseur(new Dimension(2,2), new Position(8,2),2);
        Missile missileVaisseau = new Missile(new Dimension(1,2), new Position(9,3), 2);

        assertTrue(collision.detecterCollision(envahisseur,missileVaisseau));
    }

    @Test
    public void test_MissileToucheSpriteParLeHaut() {
        Envahisseur envahisseur = new Envahisseur(new Dimension(1,2), new Position(8,3),2);
        Missile missileVaisseau = new Missile(new Dimension(1,2), new Position(8,2),2);

        assertTrue(collision.detecterCollision(envahisseur,missileVaisseau));
    }
}
