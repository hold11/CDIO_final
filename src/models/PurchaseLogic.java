package models;

import fields.LandPlot;
import fields.Ownable;

public class PurchaseLogic {

    public void buyHouse(LandPlot landplot) {
        if (landplot.isOwned() && landplot.getHouseCount() <= 5) {
            landplot.getOwner().getPlayerAcct().withdraw(landplot.getHousePrice());
            landplot.setHouseCount(landplot.getHouseCount() + 1);
        }
    }

    public void sellHouse(LandPlot landPlot) {
        if (landPlot.isOwned() && landPlot.getHouseCount() > 0) {
            landPlot.getOwner().getPlayerAcct().deposit(landPlot.getHousePrice() / 2);
            landPlot.setHouseCount(landPlot.getHouseCount() - 1);
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
}
