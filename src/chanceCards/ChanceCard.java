package chanceCards;/*
           ,                                             |
          /#\         _         _     _    ___   ___     | Project: Matador - CDIO_final
         /###\       | |__   _ | | __| |  /_  | /_  |    | Version: v0.1.0
        /#####\      | '_ \ / \| |/ _  |    | |   | |    |
       /##,-,##\     | | | | O | | ( | |   _| |_ _| |_   | Anders Wiberg Olsen (s165241), Valentin Leon Christensen (s152735),
      /##(   )##\    |_| |_|\_/|_|\__,_|  |_____|_____|  | Iman Chelhi (s165228), Troels Just Christoffersen (s120052),
     /#.--   --.#\                                       | Sebastian Tibor Bakonyvári (s145918)
    /`           ´\                                      |
*/

import GUI.fields.Chance;
import fields.Field;
import fields.Jail;
import models.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ChanceCard
{
    private int chanceCardID;
    protected String chanceText;
    protected int count = 1;
    private static ChanceCard[] chanceCards;
    private static List<ChanceCard> deck;
    private static int drawCardsCount = 0;

    public ChanceCard(int chanceCardID) {
        this.chanceCardID = chanceCardID;
        this.chanceText = lang.Lang.msg("Card" + this.chanceCardID);
    }

    public ChanceCard(String chanceText, int count) {
        this.chanceText = chanceText;
        this.count = count;
    }

    public abstract void receiveCard(Player player);

    public static void initChanceCards() {
        List<ChanceCard> cCards = new ArrayList<>();

        cCards.add(new BalanceCard(1, 800, 2300));
        cCards.add(new MoveCard(2, Field.getFieldByID(12)));
        cCards.add(new BalanceCard(3, true, 200));
        cCards.add(new MoveCard(4, 3));
        cCards.add(new BalanceCard(5, false, 1000));
        cCards.add(new BalanceCard(6, false, 1000));
        cCards.add(new BalanceCard(7, false, -300));
        cCards.add(new BalanceCard(8, false, 1000));
        cCards.add(new BalanceCard(8, false, 1000));
        cCards.add(new GrantCard(9, 40000, 15000 ));
        cCards.add(new MoveCard(10, Field.getFieldByID(1)));
        cCards.add(new MoveCard(10, Field.getFieldByID(1)));
        cCards.add(new BalanceCard(11, true, 500));
        cCards.add(new MoveCard(12, Field.getFieldByID(40)));
        cCards.add(new MoveCard(13, Field.getFieldByID(16)));
        cCards.add(new BalanceCard(14, false, -1000));
        cCards.add(new BalanceCard(15, false, -200));
        cCards.add(new BalanceCard(16, false, -200));
        cCards.add(new MoveCard(17, Field.getFieldByID(16)));
        cCards.add(new MoveCard(18, Field.getNextFieldOfType(0, Jail.class)));
        cCards.add(new MoveCard(18, Field.getNextFieldOfType(0, Jail.class)));
        cCards.add(new BalanceCard(19, 500, 2000));
        cCards.add(new BalanceCard(20, false, 1000));
        cCards.add(new BalanceCard(20, false, 1000));
        cCards.add(new MoveCard(21, Field.getFieldByID(16)));
        cCards.add(new MoveCard(21, Field.getFieldByID(16)));
        cCards.add(new MoveCard(22, Field.getFieldByID(20)));
        cCards.add(new BalanceCard(23, false, -1000));
        cCards.add(new BalanceCard(24, false, 1000));
        cCards.add(new BalanceCard(25, false, 500));
        cCards.add(new BalanceCard(25, false, 500));
        cCards.add(new MoveCard(26, Field.getFieldByID(33)));
        cCards.add(new MoveCard(27, -3));
        cCards.add(new MoveCard(27, -3));
        cCards.add(new FreeBailCard(28));
        cCards.add(new FreeBailCard(28));
        cCards.add(new BalanceCard(29, false, -3000));
        cCards.add(new BalanceCard(29, false, -3000));
        cCards.add(new BalanceCard(30, false, 1000));
        cCards.add(new BalanceCard(31, false, -200));
        cCards.add(new MoveCard(32, Field.getFieldByID(25)));
        cCards.add(new BalanceCard(33, false, 3000));
        cCards.add(new BalanceCard(34, true, 500));
        cCards.add(new BalanceCard(35, false, -1000));
        cCards.add(new BalanceCard(36, false, -2000));
        cCards.add(new BalanceCard(37, false, 200));

        deck = cCards;
    }

    public static ChanceCard[] getChanceCards() {
        return chanceCards;
    }

    public static ChanceCard getCurrentChanceCard() {
        return getChanceCards()[drawCardsCount];
    }

    public static void shuffleCards() {
        Collections.shuffle(deck);
        chanceCards = deck.stream().toArray(ChanceCard[]::new); // TODO: ensure you understand what the heck this is :D
    }

    @Override
    public String toString() {
        return this.chanceText;
    }

    public static ChanceCard drawChanceCard() {
        ChanceCard c = getChanceCards()[drawCardsCount];
        if (drawCardsCount < chanceCards.length)
            drawCardsCount++;
        else {
            drawCardsCount = 0;
            shuffleCards();
        }
        return c;
    }
}
