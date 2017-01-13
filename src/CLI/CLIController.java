package CLI;

/**
 * Created by AndersWOlsen on 07-01-2017.
 */

import models.*;
import fields.*;

import java.util.ArrayList;

/**
 * This class is for testing purposes only.
 * It is created to simplify some functions when displaying data.
 */
public class CLIController {
    public void displayStartBalance(Player player) {
        System.out.println("Starting balance: " + player.getPlayerAccount().getBalance() + ".");
        if (player.getTurnsInJail() == 0)
            System.out.println("Player in jail: false.");
        else
            System.out.println("Player in jail: true.");
        System.out.println();
    }

    public void displayEndBalance(Player player) {
        System.out.println("\nEnd balance: " + player.getPlayerAccount().getBalance());
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

    public void displayRolled(Player player) {
        System.out.println("Player rolled [" + player.getDiceCup().getResultArr()[0] +
        "] and [" + player.getDiceCup().getResultArr()[1] + "].");
    }

    public void displayLandedOn(Player player) {
        Field landedOn = Field.getFieldByID(player.getCurrentField());
        System.out.println("Player landed on \"" + landedOn + "\".");
        if (landedOn instanceof Ownable) {
            System.out.print("   Field Type: Ownable : ");
            if (landedOn instanceof Business)
                System.out.println("Business.");
            else if (landedOn instanceof LandPlot)
                System.out.println("LandPlot.");
            else if (landedOn instanceof Transportation)
                System.out.println("Transportation.");
            System.out.println("   Rent: " + ((Ownable) landedOn).getRent());
        } else if (landedOn instanceof ChanceField)
                System.out.println("   Field Type: ChanceField.");
        else if (landedOn instanceof Jail)
            System.out.println("   Field Type: Jail.");
        else if (landedOn instanceof Rest)
            System.out.println("   Field Type: Rest");
        else if (landedOn instanceof Tax)
            System.out.println("   Field Type: Tax");
    }

    public void displayThreeDoubleRoll() {
        System.out.println("Player rolled doubled 3 times in a row");
    }

    public void displayEndTurn() {
        System.out.println("\n3 double rolls in a row. Going to jail.");
    }

    public void displayCanPurchaseField() {
        System.out.println("Current field is for sale.");
    }

    public void displayPlayerBoughtField(Ownable ownable) {
        System.out.println("   Player chose to buy the field for " + ownable.getPrice() + ".");
    }
}
