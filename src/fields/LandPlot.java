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
    private int groupID;

    public LandPlot(int fieldID, int groupID, int price, int housePrice, int[] rents) {
        super(fieldID, price);
        this.groupID = groupID;
        this.housePrice = housePrice;
        this.houseCount = 0;
        if (rents.length == 6)
            this.rents = rents;
        else
            throw new IndexOutOfBoundsException("The length has to be exactly 6.");
    }

    @Override
    public void landOnField(Player player) {
        // TODO: If pawning gets implemented, start by checking if field is pawned
        if (this.isOwned() && this.owner != player && !this.owner.isInJail()) {                    // if the plot is owned by another player and is owner NOT in jail
            player.getPlayerAcct().transfer(this.getRent(), this.owner); // transfer rent to the rightful owner
        } else {
            purchaseField(player);
        }
    }

    @Override
    public int getRent() {
        return rents[houseCount];
    }

    public int getHousePrice() {
        return housePrice;
    }

    public int getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(int houseCount) {
        this.houseCount = houseCount;
    }

    public int getGroupID() {
        return groupID;
    }
}
