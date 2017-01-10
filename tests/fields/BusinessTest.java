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
        Ownable.reset();
    }

    @Test
    public void testIsOwnable() throws Exception {
        // Tests if business ownable field can be owned.
        b1.purchaseField(p1);
        assertTrue(b1.isOwned());
    }
    @Test
    public void testOnlyOneOwner() throws Exception {
        // tests if it is possible for someone to purchased the field after it has already been purchased. It should not be possible.
        b1.purchaseField(p1);
        b1.purchaseField(p2);
        assertNotEquals(p2, b1.getOwner());
    }

    @Test
    public void testNoOwner() throws Exception {
        // Tests if the field starts without a owner / if it is possible for a field to not have an owner.
        assertEquals(null, b1.getOwner());
    }
    @Test
    public void testLandOnFieldNoOwner() throws Exception {
        // The test is based on balance change as it is the only function of the landOnField method really handles. No balance change is expected seeing as it should have no owner in this test.
        int startingBalance = p1.getPlayerAcct().getBalance();
        b1.landOnField(p1);
        assertEquals(startingBalance, p1.getPlayerAcct().getBalance() );
    }
    @Test
    public void testLandOnFieldSelfOwner() throws Exception {
        // The test is based on balance change as it is the only function of the landOnField method really handles. No balance change is expected seeing as it is the owner who lands on it.
        b1.purchaseField(p1);
        int startingBalance = p1.getPlayerAcct().getBalance();
        b1.landOnField(p1);
        assertEquals(startingBalance, p1.getPlayerAcct().getBalance() );
    }
    @Test
    public void testLandOnFieldOtherOwner() throws Exception {
        // The test is based on balance change as it is the only function of the landOnField method really handles.Balance change is expected as p1 lands on a field owned by someone else.
        b1.purchaseField(p2);
        int startingBalance = p1.getPlayerAcct().getBalance();
        p1.getDiceCup().roll();
        b1.landOnField(p1);
        assertEquals(startingBalance, p1.getPlayerAcct().getBalance() );
        System.out.print(p1.getPlayerAcct().getBalance());
    }



}
