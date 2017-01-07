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

import java.util.ArrayList;
import java.util.List;

public abstract class Ownable extends Field
{
    protected int price;
    protected Player owner;
    protected static List<Ownable> ownedOwnables = new ArrayList<>();
    public abstract int getRent(Player player);

    public Ownable(int fieldID, int price) {
        super(fieldID);
        this.price = price;
    }

    public boolean isOwned() {
        for (Ownable o : ownedOwnables)
            return (this == o); // if the plot is owned, return true, otherwise return false.
        return false;
    }

    public void purchaseField(Player player) {
        if (!isOwned() && player.getPlayerAcct().getBalance() >= this.price) {
            this.owner = player;
            ownedOwnables.add(this);
            player.getPlayerAcct().withdraw(this.price);
            System.out.println(this.owner + " just bought " + this.toString());
        }
    }

    public Player getOwner() { return this.owner; }
}
