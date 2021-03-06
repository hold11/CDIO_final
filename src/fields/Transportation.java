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

public class Transportation extends Ownable
{
    public Transportation(int fieldID, int price) {
        super(fieldID, price);
    }

    @Override
    public void landOnField(Player player) {
        if (this.isOwned() && this.owner != player && this.owner.getTurnsInJail() == 0) {
            System.out.println("   [Transportation LOF]");
            System.out.print("      " + player + " pays ");
            System.out.println(getRent() + " to " + this.owner + " in rent.");
            player.getPlayerAccount().transfer(getRent(), this.owner);
        }
    }

    @Override
    public int getRent() {
        return 250 * ((int) Math.pow(2, getTotalTransportationCount(this.owner)));
    }

    private static int getTotalTransportationCount(Player player) {
        int totalCount = 0;

        for (Ownable o : getOwnedOwnables()) {
            if (o instanceof Transportation && o.getOwner() == player)
                totalCount++;
        }
        return totalCount;
    }
}
