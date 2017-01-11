package models;

import fields.LandPlot;
import fields.Ownable;

import java.util.ArrayList;
import java.util.List;

public class PurchaseLogic {

    public static void buyHouse(LandPlot landplot) {
        if (landplot.isOwned() && getAvailablePlotsToBuildOn(landplot.getOwner()).contains(landplot)) {
            landplot.getOwner().getPlayerAcct().withdraw(landplot.getHousePrice());
            landplot.setHouseCount(landplot.getHouseCount() + 1);
            System.out.println("[PurchaseLogic]: " + "player bought a house on " + landplot.toString());
        }
    }

    public static void sellHouse(LandPlot landPlot) {
        if (landPlot.isOwned() && landPlot.getHouseCount() > 0) {
            landPlot.getOwner().getPlayerAcct().deposit(landPlot.getHousePrice() / 2);
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

//    public List<LandPlot> getAvailablePlotsToBuildOn(Player player) {
//         List<LandPlot> plotsWithHouses = new ArrayList<>();
//        int currentFieldID = ((LandPlot) Field.getFieldByID(player.getCurrentField())).getGroupID();
//
//        if (LandPlot.playerHasAllPlotsInGroup(player, currentFieldID))
//            for (LandPlot l : LandPlot.getAllPlotsInGroup(currentFieldID)) {
//                if (l.getHouseCount() < 5) {                                                                                                    // Check if plot has max amount of houses
//                    for (LandPlot l2 : LandPlot.getAllPlotsInGroup(currentFieldID)) {
//                        if (l.getHouseCount() < 5 && l != l2)                                                                                      // skip if plots are the same and plot have max amount of houses
//                            if (l.getHouseCount() == l2.getHouseCount() /*|| l.getHouseCount() == (l2.getHouseCount() - 1)*/ || l.getHouseCount() == (l2.getHouseCount() + 1))                      // Check if plot have the same amount of houses or exactly one more
//                                plotsWithHouses.add(l);
//                }
//            }
//        }
//        System.out.println("[PurchaseLogic]: " + "Plots available: ");
//        for (LandPlot p : plotsWithHouses)
//            System.out.println("   " + p);
//        return  plotsWithHouses;
//    }
}