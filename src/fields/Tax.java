package fields;/*
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

public class Tax extends Field
{
    private double percentage;
    private int amount;

    public Tax(int fieldID, int amount) {
        super(fieldID);
        this.percentage = 0;
        this.amount = amount;
    }

    public Tax(int fieldID, double percentage, int amount) {
        super(fieldID);
        this.percentage = percentage;
        this.amount = amount;
    }

    @Override
    public void landOnField(Player player) {
        if (percentage == 0) {
            player.getPlayerAcct().withdraw(amount);
        } else {
            if (player.getPlayerAcct().getBalance() < player.getPlayerAcct().getGrossWorth(player) * percentage){
                player.getPlayerAcct().withdraw(amount);    ////If players balance is < players gross worth: withdraw tax amount (4000).
            } else {    //Withdraw 10% of players gross worth.
                player.getPlayerAcct().withdraw((int)(player.getPlayerAcct().getGrossWorth(player) * percentage));
            }
        }
    }

    public double getPercentageInPercent() {
        double percentagalizer = this.percentage * 100;
        return (int) percentagalizer;
    }

    public int getAmount() {
        return amount;
    }
}
