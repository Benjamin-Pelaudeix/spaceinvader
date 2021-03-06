package fr.unilim.iut;

import fr.unilim.iut.model.Dimension;
import fr.unilim.iut.model.Position;
import fr.unilim.iut.model.Vaisseau;
import fr.unilim.iut.utils.MissileException;
import org.junit.Test;

public class VaisseauTest {
    @Test(expected = MissileException.class)
    public void test_LongueurMissileSuperieureALongueurVaisseau_UneExceptionEstLevee() throws Exception {
        Vaisseau vaisseau = new Vaisseau(new Dimension(5,2), new Position(5,9),1);
        vaisseau.tirerUnMissile(new Dimension(7,2),1);
    }

}
