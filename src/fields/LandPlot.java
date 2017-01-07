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

public class LandPlot extends Ownable
{
    // [0] : 0 houses, [1] : 1 house,  [2] : 2 houses
    // [3] : 3 houses, [4] : 4 houses, [5] : 1 hotel
    private int rents[] = new int[6];
    private int housePrice;
    private int houseCount;

    public LandPlot(int fieldID, int price, int housePrice, int[] rents) {
        super(fieldID, price);
        this.housePrice = housePrice;
        this.houseCount = 0;
        if (rents.length == 6)
            this.rents = rents;
        else
            throw new IndexOutOfBoundsException("The length has to be exactly 6.");
    }

    @Override
    public void landOnField(Player player) {
        if (this.isOwned() && this.owner != player) {                    // if the plot is owned by another player
            player.getPlayerAcct().transfer(this.getRent(), this.owner); // transfer rent to the rightful owner
        }
    }

    @Override
    public int getRent() {
        return 0;
    }
}
