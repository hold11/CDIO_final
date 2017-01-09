import lang.Lang;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import chanceCards.*;
import models.*;

/**
 * Created by AndersWOlsen on 07-01-2017.
 */
public class OwnableCardTest {
    Player p1, p2;

    @Before
    public void setUp() throws Exception {
        p1 = new Player();
        p2 = new Player();
        Lang.setLanguage(new String[]{"da", "DK"});
    }

    @After
    public void tearDown() throws Exception {
        Player.reset();
    }

    @Test
    public void playerHasCard() throws Exception {
        ChanceCard.addChanceCard(new FreeBailCard(999));
        //assertFalse(OwnableCard.playerHasCard(p1, FreeBailCard.class));
        OwnableCard.getOwnedCards()[0].setOwner(p1);
        assertTrue(OwnableCard.playerHasCard(p1, FreeBailCard.class));
    }
}