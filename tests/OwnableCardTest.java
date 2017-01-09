import fields.Ownable;
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
        // Create a new FreeBailCard (OwnableCard)
        ChanceCard.addChanceCard(new FreeBailCard(999)); // ID=999 is used for testing.
        assertFalse(OwnableCard.playerHasCard(p1, FreeBailCard.class)); // Owner is null, should be false

        ((OwnableCard) ChanceCard.getChanceCards().get(0)).setOwner(p1); // Chance ownership of the card
        assertTrue(OwnableCard.playerHasCard(p1, FreeBailCard.class));   // p1 is owner, should be true
    }
}