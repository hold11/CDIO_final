package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by tjc on 9/1/17.
 */
public class BankAccountTest {
    private BankAccount playerAcct;
    private Player p1, p2;

    /**
     * Sets up 2 players, with the default name.
     * @throws Exception
     */

    @Before
    public void setUp() throws Exception {
        p1 = new Player();
        p2 = new Player();
    }

    /**
     * Resets all the users so we won't end up with 60 users.
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        Player.reset();
    }

//    /**
//     * Tests if the balance can be set to a random amount.
//     * @throws Exception
//     */
//    @Test
//    public void setBalance() throws Exception {
//        p1.getPlayerAccount().setBalance(100);
//        p2.getPlayerAccount().setBalance(250);
//
//        assertEquals(100, p1.getPlayerAccount().getBalance());
//        assertEquals(250, p2.getPlayerAccount().getBalance());
//
//    }

    /**
     * Tests if balance can be fetched from player Acct.
     * @throws Exception
     */
    @Test
    public void getBalance() throws Exception {
        assertEquals(30000, p1.getPlayerAccount().getBalance());
        assertEquals(30000, p2.getPlayerAccount().getBalance());

    }

    /**
     * Tests if a deposit can be made to players bank Acct.
     * @throws Exception
     */
    @Test
    public void deposit() throws Exception {
        p1.getPlayerAccount().deposit(200);
        p2.getPlayerAccount().deposit(300);

        assertEquals(30200, p1.getPlayerAccount().getBalance());
        assertEquals(30300, p2.getPlayerAccount().getBalance());

    }

    /**
     * Tests if a withdraw can be made to players bank Acct.
     * @throws Exception
     */
    @Test
    public void withdraw() throws Exception {
        p1.getPlayerAccount().withdraw(1000);
        p2.getPlayerAccount().withdraw(2000);

        assertEquals(29000, p1.getPlayerAccount().getBalance());
        assertEquals(28000, p2.getPlayerAccount().getBalance());

    }

//    /**
//     * Tests that the bank Acct can not have a negative balance.
//     * @throws Exception
//     */
//    @Test
//    public void withdrawNegative() throws Exception {
//        p1.getPlayerAccount().withdraw(32000);
//        p2.getPlayerAccount().withdraw(35000);
//
//        assertEquals(0, p1.getPlayerAccount().getBalance());
//        assertEquals(0, p2.getPlayerAccount().getBalance());
//
//    }
}
