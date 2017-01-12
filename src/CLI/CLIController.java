package CLI;

/**
 * Created by AndersWOlsen on 07-01-2017.
 */

import models.*;
import fields.*;

/**
 * This class is for testing purposes only.
 * It is created to simplify some functions when displaying data.
 */
public class CLIController {
    public void displayBalance(Player player) {
        System.out.println(player + "'s balance is " + player.getPlayerAcct().getBalance());
        System.out.println();
    }

    public void displayOwnerOfField(Field field) {
        if (field instanceof Ownable)
            System.out.println(field + " is currently owned by " + ((Ownable) field).getOwner());
        else
            System.out.println("This field type doesn't have an owner.");
        System.out.println();
    }

    public void displayOwnedFields(Player player) {
        System.out.println(player + " owns following ownables: ");
        for (Ownable o : Ownable.getOwnedOwnables())
            System.out.println("   * " + o + " (id: " + o.getFieldId() + ")");
        System.out.println();
    }

    public void displayHasAllPlotsInGroup(Player player, LandPlot landPlot) {
        System.out.println(player + " has all plots in group (" + landPlot.getGroupID() + "): " + landPlot.playerHasAllPlotsInGroup());
        System.out.println();
    }

//    public void displayPlotsInGroup(int groupId) {
//        for (LandPlot l : LandPlot.getAllPlotsInGroup(groupId))
//            System.out.println(l + "(id:" + l.getFieldId() + ") is in group " + groupId);
//        System.out.println();
//    }

    public void displayCanPurchaseHouse(LandPlot lplot) {
        if (PurchaseLogic.canPurchaseHouse(lplot))
            System.out.println("It is possible to purchase a house on " + lplot);
        else
            System.out.println("It is not possible to purchase a house on " + lplot);
        System.out.println();
    }
}
