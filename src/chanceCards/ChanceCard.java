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

import models.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class ChanceCard
{
    private int chanceCardID;
    protected String chanceText;
    protected int count = 1;
    private static List<ChanceCard> chanceCards = new ArrayList<>();

    public ChanceCard(int chanceCardID) {
        this.chanceCardID = chanceCardID;
        this.chanceText = lang.Lang.msg("Card" + this.chanceCardID);
    }

    public ChanceCard(String chanceText, int count) {
        this.chanceText = chanceText;
        this.count = count;
    }

    public abstract void receiveCard(Player player);

    public static List<ChanceCard> getChanceCards() {
        return chanceCards;
    }

    public static void addChanceCard(ChanceCard cc) { // TODO: Need this???? Maybe private
        chanceCards.add(cc);
    }
}
