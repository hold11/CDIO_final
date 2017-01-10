package models;

import fields.Field;
import fields.LandPlot;
import fields.Ownable;

import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseLogic {

    public void buyHouse(LandPlot landplot) {
        if (landplot.isOwned() && landplot.getHouseCount() <= 5) {
            landplot.getOwner().getPlayerAcct().withdraw(landplot.getHousePrice());
            landplot.setHouseCount(landplot.getHouseCount() + 1);
            System.out.println("player bought a house");
        }
    }

    public void sellHouse(LandPlot landPlot) {
        if (landPlot.isOwned() && landPlot.getHouseCount() > 0) {
            landPlot.getOwner().getPlayerAcct().deposit(landPlot.getHousePrice() / 2);
            landPlot.setHouseCount(landPlot.getHouseCount() - 1);
            System.out.println("player bought a house");
        }
    }

    public int getHouseCount() {
        int houseCount = 0;
        for (Ownable o : Ownable.getOwnedOwnables()) {
            if (o instanceof LandPlot)
                if (((LandPlot) o).getHouseCount() < 5)     // Casting ownable o to LandPlot, because getHouseCount method is there.
                    houseCount++;
        }
        return houseCount;
    }

    public int getHotelCount() {
        int hotelCount = 0;
        for (Ownable o : Ownable.getOwnedOwnables()) {
            if (o instanceof LandPlot)
                if (((LandPlot) o).getHouseCount() == 5)    // Casting ownable o to LandPlot, because getHouseCount method is there.
                    hotelCount++;
        }
        return hotelCount;
    }

    public LandPlot[] getAvailablePlotsToBuildOn(Player player) throws Exception {
        List<LandPlot> plotsWithHouses = new ArrayList<>();
        int currentFieldID = ((LandPlot) Field.getFieldByID(player.getCurrentField())).getGroupID();

        if (LandPlot.hasAllPlotsInGroup(player, currentFieldID))
            for (LandPlot l : LandPlot.getGroupedPlots(currentFieldID)) {
                if (l.getHouseCount() < 5) {                                                                                                    // Check if plot has max amount of houses
                    for (LandPlot l2 : LandPlot.getGroupedPlots(currentFieldID)) {
                        if (l.getHouseCount() < 5 && l != l2)                                                                                      // skip if plots are the same and plot have max amount of houses
                            if (l.getHouseCount() == l2.getHouseCount() /*|| l.getHouseCount() == (l2.getHouseCount() - 1)*/ || l.getHouseCount() == (l2.getHouseCount() + 1))                      // Check if plot have the same amount of houses or exactly one more
                                plotsWithHouses.add(l);
                            else
                                throw new Exception("[PurchaseLogic]: lel u haz 2 mani howses");
                }
            }
        }
        System.out.println("Plots available: " + plotsWithHouses.toString());
        return  plotsWithHouses.toArray(new LandPlot[plotsWithHouses.size()]);
    }
}


// TODO: check if player has all fields in group and make sure houses get purchased in a distributed fashion.