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
import java.util.Arrays;
import java.util.List;

public abstract class Ownable extends Field
{
    private final int price;
    protected Player owner;
    public abstract int getRent();

    protected Ownable(int fieldID, int price) {
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
        return ownedOwnables.stream().toArray(Ownable[]::new);
    }

    public boolean isOwned() {
        return (this.owner != null);
    }

    public void purchaseField(Player player) {
        if (!isOwned() && player.getPlayerAccount().getBalance() >= this.price) {
            this.owner = player;
            player.getPlayerAccount().withdraw(this.price);
            System.out.println("[Ownable]: " + this.owner + " just bought " + this.toString());
        }
    }

    public Player getOwner() { return this.owner; }

    public int getPrice() {
        return price;
    }

    public static void resetPlayersPlots(Player player) {
        // Reset the ownership
        Arrays.stream(getOwnedOwnables())
                .filter(ownable -> ownable.getOwner().equals(player))
                .forEach(Ownable::resetOwnership);

        // Reset House Count
        Arrays.stream(getOwnedOwnables())
                .filter(ownable -> ownable instanceof LandPlot)
                .forEach(landPlot -> ((LandPlot) landPlot).setHouseCount(0));

        // TODO: Add methods for removing gui plots, houses and hotels
    }

    private void resetOwnership() { this.owner = null; }

    public static void resetAllFields() {
        for (Field f : Field.getFields()) {
            if (f instanceof Ownable) {
                ((Ownable) f).owner = null;
            }
        }
    }
}
