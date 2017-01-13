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
    int type;

    /**
     * Type = 0: Pay/receive money to/from the bank.
     * Type = 1: Receive from all players
     * @param chanceCardID
     * @param changeBalance
     * @param type
     */
    public BalanceCard(int chanceCardID, int type, int changeBalance) {
        super(chanceCardID);
        this.type = type;                       // TODO: Can we do this in a prettier way?
        this.changeBalance = changeBalance;
    }

    @Override
    public void receiveCard(Player player)
    {
        if (this.changeBalance < 0 && this.type == 0)                 // Pay money to the bank
            player.getPlayerAccount().withdraw(this.changeBalance);
        else if (this.changeBalance > 0 && this.type == 0)            // Receive money from the bank
            player.getPlayerAccount().deposit(this.changeBalance);
        else if (this.type == 1)                                      // Receive money from all players except you
            for (Player p : Player.getPlayers())
                if (p != player)                                      // If the player in the list is not the current player, transfer the money (really not necesary to check this)
                    p.getPlayerAccount().transfer(this.changeBalance, p);
    }
}
