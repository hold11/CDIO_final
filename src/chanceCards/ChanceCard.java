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

import fields.Field;
import models.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class ChanceCard
{
    private int chanceCardID;
    protected String chanceText;
    protected int count = 1;
    private static ChanceCard[] chanceCards;

    public ChanceCard(int chanceCardID) {
        this.chanceCardID = chanceCardID;
        this.chanceText = lang.Lang.msg("Card" + this.chanceCardID);
        initChanceCards();
    }

    public ChanceCard(String chanceText, int count) {
        this.chanceText = chanceText;
        this.count = count;
        initChanceCards();
    }

    private void initChanceCards() {
        List<ChanceCard> cCards = new ArrayList<>();
        cCards.add(new BalanceCard(1, 800, 2300));
        cCards.add(new MoveCard(2, Field.getFieldByID(12)));
        cCards.add(new BalanceCard(3, true, 200));
        cCards.add(new MoveCard(4, 3));
        cCards.add(new BalanceCard(5, false, 1000));
        cCards.add(new BalanceCard(6, false, 1000));
        cCards.add(new BalanceCard(7, false, -300));
        cCards.add(new BalanceCard(8, false, 1000));
        // card 9 matadorlegatet
        cCards.add(new MoveCard(10, Field.getFieldByID(1)));
        cCards.add(new BalanceCard(11, true, 500));
        cCards.add(new MoveCard(12, Field.getFieldByID(40)));
        // card 13 er et næste transportkort
        cCards.add(new BalanceCard(14, false, -1000));
        cCards.add(new BalanceCard(15, false, -200));
        cCards.add(new BalanceCard(16, false, -200));
        // card 17 er et næste transportkort
        // card 18 get jailed
        cCards.add(new BalanceCard(19, 500, 2000));
        cCards.add(new BalanceCard(20, false, 1000));
        // card 21 næste transport + betal dobbelt leje
        




        chanceCards = cCards.stream().toArray(ChanceCard[]::new); // TODO: ensure you understand what the heck this is :D
    }

    public abstract void receiveCard(Player player);

    public static ChanceCard[] getChanceCards() {
        return chanceCards;
    }
}
