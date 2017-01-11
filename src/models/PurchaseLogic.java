package models;

import fields.Field;
import fields.LandPlot;
import fields.Ownable;

import java.util.ArrayList;
import java.util.List;

public class PurchaseLogic {

    public void buyHouse(LandPlot landplot) {
        if (landplot.isOwned() && getAvailablePlotsToBuildOn(landplot.getOwner()).contains(landplot)) {
            landplot.getOwner().getPlayerAcct().withdraw(landplot.getHousePrice());
            landplot.setHouseCount(landplot.getHouseCount() + 1);
            System.out.println("[PurchaseLogic]: " + "player bought a house on " + landplot.toString());
        }
    }

    public void sellHouse(LandPlot landPlot) {
        if (landPlot.isOwned() && landPlot.getHouseCount() > 0) {
            landPlot.getOwner().getPlayerAcct().deposit(landPlot.getHousePrice() / 2);
            landPlot.setHouseCount(landPlot.getHouseCount() - 1);
            System.out.println("[PurchaseLogic]: " + "player bought a house");
        }
    }

    public int getTotalHouseCount() {
        int totalHouseCount = 0;
        for (Ownable o : Ownable.getOwnedOwnables()) {
            if (o instanceof LandPlot)
                if (((LandPlot) o).getHouseCount() < 5)     // Casting ownable o to LandPlot, because getHouseCount method is there.
                    totalHouseCount += ((LandPlot) o).getHouseCount();
        }
        return totalHouseCount;
    }

    public int getTotalHotelCount() {
        int totalHotelCount = 0;
        for (Ownable o : Ownable.getOwnedOwnables()) {
            if (o instanceof LandPlot)
                if (((LandPlot) o).getHouseCount() == 5)    // Casting ownable o to LandPlot, because getHouseCount method is there.
                    totalHotelCount++;
        }
        return totalHotelCount;
    }

    public List<LandPlot> getAvailablePlotsToBuildOn(Player player) {
         List<LandPlot> plotsWithHouses = new ArrayList<>();
        int currentFieldID = ((LandPlot) Field.getFieldByID(player.getCurrentField())).getGroupID();

        if (LandPlot.hasAllPlotsInGroup(player, currentFieldID))
            for (LandPlot l : LandPlot.getPlotGroup(currentFieldID)) {
                if (l.getHouseCount() < 5) {                                                                                                    // Check if plot has max amount of houses
                    for (LandPlot l2 : LandPlot.getPlotGroup(currentFieldID)) {
                        if (l.getHouseCount() < 5 && l != l2)                                                                                      // skip if plots are the same and plot have max amount of houses
                            if (l.getHouseCount() == l2.getHouseCount() /*|| l.getHouseCount() == (l2.getHouseCount() - 1)*/ || l.getHouseCount() == (l2.getHouseCount() + 1))                      // Check if plot have the same amount of houses or exactly one more
                                plotsWithHouses.add(l);
                }
            }
        }
        System.out.println("[PurchaseLogic]: " + "Plots available: ");
        for (LandPlot p : plotsWithHouses)
            System.out.println("   " + p);
        return  plotsWithHouses;
    }
}