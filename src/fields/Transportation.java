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

import models.DiceCup;
import models.Player;

public class Transportation extends Ownable
{
    public Transportation(int fieldID, int price) {
        super(fieldID, price);
    }

    @Override
    public void landOnField(Player player) {
        if (this.isOwned() && this.owner != player)
            player.getPlayerAcct().transfer(getRent(), this.owner);
    }

    @Override
    public int getRent() {
//        if (this.isOwned() && this.owner != player)
            return 250 * ((int) Math.pow(2, getTotalTransportationCount(this.owner)));
//        else
//            return 0;
    }

    /**
     * This method calculates how many transportation services is owned by a specific player
     * @param player
     * @return
     */
    private static int getTotalTransportationCount(Player player) {
        int totalCount = 0;

        for (Ownable o : ownedOwnables) {
            if (o instanceof Transportation && o.getOwner() == player)
                totalCount++;
        }

        return totalCount;
    }
}
