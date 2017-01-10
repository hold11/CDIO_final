package fields;

import lang.Lang;
import models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by s165228 on 10-01-2017.
 */
public class BusinessTest {
    Player p1;
    Player p2;
    Business b1;
    Business b2;

    @Before
    public void setUp() throws Exception {
        Lang.setLanguage(new String[] {"da", "DK"});
        p1 = new Player();
        p2 = new Player();
        b1 = new Business(1, 1000);
        b2 = new Business(2, 2000);
    }

    @After
    public void tearDown() throws Exception {
        Player.reset();
    }

    @Test
    public void testIsOwnable() throws Exception {
        b1.purchaseField(p1);
        assertTrue(b1.isOwned());
    }
    @Test
    public void testOnlyOneOwner() throws Exception {
        b1.purchaseField(p2);
        assertEquals(p1, b1.getOwner());
    }

    @Test
    public void testLandOnField() throws Exception {
        p1.landPlot(1, 2, 1000, 2000, 3000);

    }

}
