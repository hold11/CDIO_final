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
            player.getPlayerAccount().withdraw(amount);
        } else {
            if (player.getPlayerAccount().getBalance() < player.getPlayerAccount().getGrossWorth(player) * percentage){
                player.getPlayerAccount().withdraw(amount);
            } else {
                player.getPlayerAccount().withdraw((int)(player.getPlayerAccount().getGrossWorth(player) * percentage));
            }
        }
    }

    // Converts decimal to percentage
    public double getPercentageInPercent() {
        double percentagalizer = this.percentage * 100;
        return (int) percentagalizer;
    }

    public int getAmount() {
        return amount;
    }
}
