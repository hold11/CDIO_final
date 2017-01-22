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

public class GrantCard extends ChanceCard
{
    private final int changeBalance;
    private final int allowedAmount;

    public GrantCard(int chanceCardID,  int changeBalance, int allowedAmount) {
        super(chanceCardID);
        this.changeBalance = changeBalance;
        this.allowedAmount = allowedAmount;
    }

    @Override
    public void receiveCard(Player player) {
        if (player.getPlayerAccount().getGrossWorth(player) < allowedAmount) {
            player.getPlayerAccount().deposit(changeBalance);
        }
    }
}
