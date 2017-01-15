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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Business extends Ownable
{
    public Business(int fieldID, int price) {
        super(fieldID, price);
    }

    @Override
    public void landOnField(Player player) {
        if (this.isOwned() && this.owner != player && this.owner.getTurnsInJail() == 0) {
            System.out.println("   [Business LOF]");
            System.out.print("      " + player + " pays ");
            System.out.println(getRent(player) + " to " + this.owner + " in rent.");
            player.getPlayerAccount().transfer(this.getRent(player), this.owner);
        }
    }

    @Override
    public int getRent() {
        throw new NotImplementedException(); // Use getRent(Player) instead in this method.
    }

    public int getRent(Player player) {
        if (this.isOwned() && this.owner != player)
            return player.getDiceCup().getTotalEyes() * 100 * getTotalBusinessCount(this.owner);
        else
            return 0;
    }

    public static int getTotalBusinessCount(Player player) {
        int totalCount = 0;

        for (Ownable o : getOwnedOwnables()) {
            if (o instanceof Business && o.getOwner() == player)
                totalCount++;
        }
        return totalCount;
    }
}
