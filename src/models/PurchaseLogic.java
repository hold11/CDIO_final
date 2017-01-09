package models;

import fields.Field;
import fields.LandPlot;
import fields.Ownable;

/**
 * Created by tjc on 9/1/17.
 */
public class PurchaseLogic {

    // Buy house
    public void buyHouse() {

    }

    // Sell house
    public void sellHouse() {

    }

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
        for (Ownable o : Ownable.getOwnedOwnables()) {

        }
    }
}
