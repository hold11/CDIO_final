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

/**
 * There are multiple types of balance cards. I have implemented:
 *    Receive money from the bank
 *    Pay money to the bank
 *    Receive from all players
 *
 * Missing:
 *    Chance balance by assets
 */

public class BalanceCard extends ChanceCard
{
    int changeBalance;
    int receiveFromPlayers;

    public BalanceCard(int chanceCardID, int changeBalance) {
        super(chanceCardID);
        this.changeBalance = changeBalance;
    }

    public BalanceCard(int chanceCardID, int changeBalance, int receiveFromPlayers) {
        super(chanceCardID);
        this.changeBalance = 0; // TODO: Can we do this in a prettier way? chanceBalance isn't used here
        this.receiveFromPlayers = receiveFromPlayers;
    }

    @Override
    public void receiveCard(Player player) {
        if (this.changeBalance < 0) {                               // Pay money to the bank
            player.getPlayerAcct().withdraw(this.changeBalance);
        } else if (this.changeBalance > 0) {                        // Receive money from the bank
            player.getPlayerAcct().deposit(this.changeBalance);
        } else if (this.changeBalance == 0) {                       // Receive money from all players except you
            for (Player p : Player.getPlayers())
                if (p != player)                                    // If the player in the list is not the current player, transfer the money (really not necesary to check this)
                    p.getPlayerAcct().transfer(this.receiveFromPlayers, p);
        }
    }
}
