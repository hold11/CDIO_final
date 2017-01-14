package models;/*
           ,                                             |
          /#\         _         _     _    ___   ___     | Project: Matador - CDIO_final
         /###\       | |__   _ | | __| |  /_  | /_  |    | Version: v0.1.0
        /#####\      | '_ \ / \| |/ _  |    | |   | |    |
       /##,-,##\     | | | | O | | ( | |   _| |_ _| |_   | Anders Wiberg Olsen (s165241), Valentin Leon Christensen (s152735),
      /##(   )##\    |_| |_|\_/|_|\__,_|  |_____|_____|  | Iman Chelhi (s165228), Troels Just Christoffersen (s120052),
     /#.--   --.#\                                       | Sebastian Tibor Bakonyvári (s145918)
    /`           ´\                                      |
*/

import fields.LandPlot;
import fields.Ownable;

import java.util.ArrayList;
import java.util.List;

public class PurchaseLogic {

    public static final int MAXHOUSECOUNT = 32;
    public static final int MAXHOTELCOUNT = 12;

    public static void buyHouse(LandPlot landplot) {
        if (landplot.isOwned() && getAvailablePlotsToBuildOn(landplot.getOwner()).contains(landplot)) {
            landplot.getOwner().getPlayerAccount().withdraw(landplot.getHousePrice());
            landplot.setHouseCount(landplot.getHouseCount() + 1);
            System.out.println("[PurchaseLogic]: " + "player bought a house on " + landplot.toString());
        }
    }

    public static void sellHouse(LandPlot landPlot) {
        if (landPlot.isOwned() && landPlot.getHouseCount() > 0) {
            landPlot.getOwner().getPlayerAccount().deposit(landPlot.getHousePrice() / 2);
            landPlot.setHouseCount(landPlot.getHouseCount() - 1);
            System.out.println("[PurchaseLogic]: " + "player bought a house");
        }
    }

    public static int getTotalHouseCount() {
        int totalHouseCount = 0;
        for (Ownable o : Ownable.getOwnedOwnables()) {
            if (o instanceof LandPlot)
                if (((LandPlot) o).getHouseCount() < 5)     // Casting ownable o to LandPlot, because getHouseCount method is there.
                    totalHouseCount += ((LandPlot) o).getHouseCount();
        }
        return totalHouseCount;
    }

    public static int getTotalHotelCount() {
        int totalHotelCount = 0;
        for (Ownable o : Ownable.getOwnedOwnables()) {
            if (o instanceof LandPlot)
                if (((LandPlot) o).getHouseCount() == 5)    // Casting ownable o to LandPlot, because getHouseCount method is there.
                    totalHotelCount++;
        }
        return totalHotelCount;
    }

    public static int getPlayerHouseCount(Player player) {
        int playerHouseCount = 0;
        for (Ownable o : Ownable.getOwnedOwnables()) {
            if (o instanceof LandPlot)
                if (o.getOwner().equals(player))
                    if (((LandPlot) o).getHouseCount() < 5)     // Casting ownable o to LandPlot, because getHouseCount method is there.
                        playerHouseCount += ((LandPlot) o).getHouseCount();
        }
        return playerHouseCount;
    }

    public static int getPlayerHotelCount(Player player) {
        int playerHotelCount = 0;
        for (Ownable o : Ownable.getOwnedOwnables()) {
            if (o instanceof LandPlot)
                if (o.getOwner().equals(player))
                    if (((LandPlot) o).getHouseCount() == 5)     // Casting ownable o to LandPlot, because getHouseCount method is there.
                        playerHotelCount += ((LandPlot) o).getHouseCount();
        }
        return playerHotelCount;
    }

    public static boolean canPurchaseHouse(LandPlot landPlot) {
        // Loop through all plots in group
        for (LandPlot l : landPlot.getAllPlotsInGroup()) {
            if (landPlot.playerHasAllPlotsInGroup())
                if (landPlot.getHouseCount() <= l.getHouseCount() && landPlot.getHouseCount() < 5 && landPlot != l)
                    return true;
        }
        return false;
    }

    public static LandPlot[] getAllFullyGroupedPlots(Player player) {
        List<LandPlot> result = new ArrayList<>();
        for (Ownable o : Ownable.getOwnedOwnables()) {
            if (o instanceof LandPlot) {
                if (((LandPlot) o).playerHasAllPlotsInGroup() && ((LandPlot) o).getHouseCount() < 5)
                    result.add(((LandPlot) o));
            }
        }
        // TODO: Finish sentence below...
        // return result.toArray(new LandPlot[result.size()]); was the
        return result.stream().toArray(LandPlot[]::new);
    }

    private static int[] getAllFullGroupIds(Player player) {
        List<Integer> result = new ArrayList<>();

        for (LandPlot l : getAllFullyGroupedPlots(player))
            if (!result.contains(l.getGroupID()))
                result.add(l.getGroupID());
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static List<LandPlot> getAvailablePlotsToBuildOn(Player player) {
        List<LandPlot> result = new ArrayList<>();
        for (LandPlot l : getAllFullyGroupedPlots(player)) {
            if (canPurchaseHouse(l))
                result.add(l);
        }
        return result;
    }

    public static boolean playerCanDevelopPlots(Player player) {
        return (getAllFullyGroupedPlots(player).length != 0);

    }
}