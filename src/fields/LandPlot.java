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
        if (this.isOwned() && this.owner != player) {                    // if the plot is owned by another player
            player.getPlayerAcct().transfer(this.getRent(), this.owner); // transfer rent to the rightful owner
        }
    }

    @Override
    public int getRent() {
        // TODO: If pawning gets implemented, start by checking if field is pawned
//        if (this.isOwned() && this.owner != player)
            return rents[houseCount];
//        else
//            return 0;
    }

    public void buyHouse() {
        if (this.isOwned() && this.houseCount <= 5) {
            this.getOwner().getPlayerAcct().withdraw(this.housePrice);
            this.houseCount++;
        }
    }

    public void sellHouse() {
        if (this.isOwned() && this.houseCount > 0) {
            this.getOwner().getPlayerAcct().deposit(this.housePrice / 2);
            this.houseCount--;
        }
    }

    public int getGroupID() {
        return groupID;
    }
}
