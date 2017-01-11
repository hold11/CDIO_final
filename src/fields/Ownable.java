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
//    protected static List<Ownable> getOwnedOwnables = new ArrayList<>();
    public abstract int getRent();

    public Ownable(int fieldID, int price) {
        super(fieldID);
        this.price = price;
    }

    public static Ownable[] getOwnedOwnables() {
        List<Ownable> ownedOwnables = new ArrayList<>();
        for (Field field : Field.getFields()) {
            if (field instanceof Ownable)
                if (((Ownable) field).getOwner() != null)
                    ownedOwnables.add(((Ownable) field));
        }
        return ownedOwnables.toArray(new Ownable[ownedOwnables.size()]);
    }

    public boolean isOwned() {
        if (this.owner == null)
            return false;
        else
            return true;
    }

    public void purchaseField(Player player) {
        if (!isOwned() && player.getPlayerAcct().getBalance() >= this.price) {
            this.owner = player;
//            getOwnedOwnables.add(this);
            player.getPlayerAcct().withdraw(this.price);
            System.out.println(this.owner + " just bought " + this.toString());
        }
    }

    public Player getOwner() { return this.owner; }

    public int getPrice() {
        return price;
    }
}
