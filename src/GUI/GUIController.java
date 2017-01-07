package GUI;

/*
           ,                                             |
          /#\         _         _     _    ___   ___     | Project: Matador - CDIO_final
         /###\       | |__   _ | | __| |  /_  | /_  |    | Version: v0.1.0
        /#####\      | '_ \ / \| |/ _  |    | |   | |    |
       /##,-,##\     | | | | O | | ( | |   _| |_ _| |_   | Anders Wiberg Olsen (s165241), Valentin Leon Christensen (s152735),
      /##(   )##\    |_| |_|\_/|_|\__,_|  |_____|_____|  | Iman Chelhi (s165228), Troels Just Christoffersen (s120052),
     /#.--   --.#\                                       | Sebastian Tibor Bakonyvári (s145918)
    /`           ´\                                      |
 */


import GUI.GUI;
import GUI.backend.Car;
import GUI.fields.*;
import lang.Lang;
import models.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class GUIController {
    private Car[] cars = new Car[6];

    public GUIController() {
    }

    //TODO: Figure out if this is still needed
//    public void initMovers() {
//
//        movers[0] = new Car.Builder()
//                .troldeTanja()
//                .primaryColor(new Color(22, 154,255))
//                .secondaryColor(new Color(255, 21, 208))
//                .overlayDualColor()
//                .build();
//
//        movers[1] = new Car.Builder()
//                .troldeTanja()
//                .primaryColor(Color.GREEN)
//                .secondaryColor(Color.WHITE)
//                .overlayDualColor()
//                .build();
//
//        movers[2] = new Car.Builder()
//                .troldeTanja()
//                .primaryColor(Color.WHITE)
//                .secondaryColor(Color.RED)
//                .overlayDualColor()
//                .build();
//
//        movers[3] = new Car.Builder()
//                .troldeTanja()
//                .primaryColor(Color.YELLOW)
//                .secondaryColor(Color.GREEN)
//                .overlayDualColor()
//                .build();
//
//        movers[4] = new Car.Builder()
//                .troldeTanja()
//                .primaryColor(Color.BLUE)
//                .secondaryColor(Color.ORANGE)
//                .overlayDualColor()
//                .build();
//
//        movers[5] = new Car.Builder()
//                .troldeTanja()
//                .primaryColor(Color.WHITE)
//                .secondaryColor(new Color(22, 154,255))
//                .overlayDualColor()
//                .build();
//    }

    public int selectPlayerCount() {
        return Integer.parseInt(GUI.getUserSelection("Select players",  "3", "4", "5", "6"));
    }

    //TODO: Figure out if this is needed at all
//    public void createBoardWithFields() {
//        ArrayList<Field> list = new ArrayList<>();
//
//        //Field #1
//        list.add(new Territory.Builder()
//                .setTitle("Tribe Encampment")
//                .setPicture("src/GUI/art/fields/territories/tribalEncampment.png")
//                .setRent("100")
//                .setPrice("1000")
//                .build());
//
//        //Field #2
//        list.add(new Territory.Builder()
//                .setTitle("Crater")
//                .setPicture("src/GUI/art/fields/territories/crater.png")
//                .setRent("300")
//                .setPrice("1500")
//                .build());
//
//        //Field #3
//        list.add(new Territory.Builder()
//                .setTitle("Mountain")
//                .setPicture("src/GUI/art/fields/territories/mountain.png")
//                .setRent("500")
//                .setPrice("2000")
//                .build());
//
//        //Field #4
//        list.add(new Territory.Builder()
//                .setTitle("Cold Desert")
//                .setPicture("src/GUI/art/fields/territories/coldDesert.png")
//                .setRent("700")
//                .setPrice("3000")
//                .build());
//
//        //Field #5
//        list.add(new Territory.Builder()
//                .setTitle("Black Cave")
//                .setPicture("src/GUI/art/fields/territories/blackCave.png")
//                .setRent("1000")
//                .setPrice("4000")
//                .build());
//
//        //Field #6
//        list.add(new Territory.Builder()
//                .setTitle("The Werewall")
//                .setPicture("src/GUI/art/fields/territories/werewall.png")
//                .setRent("1300")
//                .setPrice("4300")
//                .build());
//
//        //Field #7
//        list.add(new Territory.Builder()
//                .setTitle("Mountain Village")
//                .setPicture("src/GUI/art/fields/territories/mountainVillage.png")
//                .setRent("1600")
//                .setPrice("4750")
//                .build());
//
//        //Field #8
//        list.add(new Territory.Builder()
//                .setTitle("South Citadel")
//                .setPicture("src/GUI/art/fields/territories/southCitadel.png")
//                .setRent("2000")
//                .setPrice("5000")
//                .build());
//
//        //Field #9
//        list.add(new Territory.Builder()
//                .setTitle("Palace Gates")
//                .setPicture("src/GUI/art/fields/territories/palaceGates.png")
//                .setRent("2600")
//                .setPrice("5500")
//                .build());
//
//        //Field #10
//        list.add(new Territory.Builder()
//                .setTitle("Tower")
//                .setPicture("src/GUI/art/fields/territories/tower.png")
//                .setRent("3200")
//                .setPrice("6000")
//                .build());
//
//        //Field #11
//        list.add(new Territory.Builder()
//                .setTitle("Castle")
//                .setPicture("src/GUI/art/fields/territories/castle.png")
//                .setRent("4000")
//                .setPrice("8000")
//                .build());
//
//        //Field #12
//        list.add(new Refuge.Builder()
//                .setTitle("Walled City")
//                .setPicture("src/GUI/art/Fields/refuge/walledCity.png")
//                .setBonus("5000")
//                .build());
//
//        //Field #13
//        list.add(new Refuge.Builder()
//                .setTitle("Monastery")
//                .setPicture("src/GUI/art/Fields/refuge/monastery.png")
//                .setBonus("500")
//                .build());
//
//        //Field #14
//        list.add(new LaborCamp.Builder()
//                .setTitle("Huts in the Mountain")
//                .setPicture("src/GUI/art/Fields/laborCamp/hutsInTheMountain.png")
//                .setRent("dice x100")
//                .setPrice("2500")
//                .build());
//
//        //Field #15
//        list.add(new LaborCamp.Builder()
//                .setTitle("The Pit")
//                .setPicture("src/GUI/art/Fields/laborCamp/thePit.png")
//                .setRent("dice x100")
//                .setPrice("2500")
//                .build());
//
//        //Field #16
//        list.add(new Tax.Builder()
//                .setTitle("Goldmine")
//                .setPicture("src/GUI/art/Fields/tax/goldmine.png")
//                .setTax("2000")
//                .build());
//
//        //Field #17
//        list.add(new Tax.Builder()
//                .setTitle("Caravan")
//                .setPicture("src/GUI/art/Fields/tax/caravan.png")
//                .setTax("10% or 4000")
//                .build());
//
//        //Field #18
//        list.add(new Fleet.Builder()
//                .setTitle("Second Sail")
//                .setPicture("src/GUI/art/Fields/fleet/secondSail.png")
//                .setRent("1000")
//                .setPrice("4000")
//                .build());
//
//        //Field #19
//        list.add(new Fleet.Builder()
//                .setTitle("Sea Grover")
//                .setPicture("src/GUI/art/Fields/fleet/seaGrover.png")
//                .setRent("2000")
//                .setPrice("4000")
//                .build());
//
//        //Field #20
//        list.add(new Fleet.Builder()
//                .setTitle("The Buccaneers")
//                .setPicture("src/GUI/art/Fields/fleet/theBuccaneers.png")
//                .setRent("3000")
//                .setPrice("4000")
//                .build());
//
//        //Field #21
//        list.add(new Fleet.Builder()
//                .setTitle("Privateer Armada")
//                .setPicture("src/GUI/art/Fields/fleet/privateerArmada.png")
//                .setRent("4000")
//                .setPrice("4000")
//                .build());
//
//        GUI.create(list);
//    }

    public void createPlayers() {
        //initMovers();

        for (int i = 0; i < Player.getPlayers().size(); i++)
            GUI.addPlayer(Player.getPlayers().get(i).getPlayerName(), Player.getPlayers().get(i).getPlayerAcct().getBalance());
    }

    public void moveCars(Player player) {
        if (player.getPreviousField() >= player.getCurrentField()) {
            for (int i = player.getPreviousField() ; i <= 21 ; i++) {
                GUI.removeAllCars(player.getPlayerName());
                GUI.setCar(i, player.getPlayerName());
                sleep(500);
            }
            for (int i = 1 ; i <= player.getCurrentField(); i++) {
                GUI.removeAllCars(player.getPlayerName());
                GUI.setCar(i, player.getPlayerName());
                sleep(500);
            }
        } else {
            for (int i = player.getPreviousField(); i <= player.getCurrentField(); i++) {
                GUI.removeAllCars(player.getPlayerName());
                GUI.setCar((i), player.getPlayerName());
                sleep(500);
            }
        }
    }

    //TODO: Get this thing working
//    public void removeBankruptPlayer(Player player) {
//        if (player.getPlayerAccount().getBalance() == 0) {
//            GUI.removeAllMovers(player);
//
//            List<Integer> ownablesInt = new ArrayList<>();
//            List<fields.Ownable> tmp = fields.Ownable.getPlayersOwnedFields(player);
//            models.GameBoard board = new models.GameBoard();
//
//            for (int i = 0; i > board.getFields().length; i++) {
//                for (int i2 = 0; i > tmp.size(); i2++) {
//                    if (board.getFields()[i].toString() == tmp.get(i2).toString())
//                        ownablesInt.add(i);
//                }
//            }
//            for (int i : ownablesInt)
//                GUI.removeOwner(i);
//        }
//    }

    public void updateBalance(Player player) {
        GUI.setBalance(player.getPlayerName(), player.getPlayerAcct().getBalance());
    }

    //TODO: Move to Main!
//    public void playerRoll(Player player) {
//        GUI.getUserButtonPressed(player.getPlayerName() + "! Roll for adventure and glory!", "Roll!");
//        player.getDiceCup().roll();
//        GUI.setDice(player.getDiceCup().getResultArr()[0], player.getDiceCup().getResultArr()[1]);
//    }

    public boolean getPlayerPurchaseChoice(Player player) {
        showDescriptionCardBuy(player);
        String answer = GUI.getUserButtonPressed("Want to purchase this field?", "Yes!", "No!");
        if (answer.equals("Yes!")) {
            GUI.setOwner(player.getCurrentField(), player.getPlayerName());
            return true;
        } else return false;
    }

   public void showDescriptionCardBuy(Player player) {

        switch(player.getCurrentField()) {

            case 1: GUI.displayChanceCard(Lang.msg("desc_buy_TribeEncampment"));
                    break;
            case 2: GUI.displayChanceCard(Lang.msg("desc_buy_Crater"));
                    break;
            case 3: GUI.displayChanceCard(Lang.msg("desc_buy_Mountain"));
                    break;
            case 4: GUI.displayChanceCard(Lang.msg("desc_buy_ColdDesert"));
                    break;
            case 5: GUI.displayChanceCard(Lang.msg("desc_buy_BlackCave"));
                    break;
            case 6: GUI.displayChanceCard(Lang.msg("desc_buy_TheWerewall"));
                    break;
            case 7: GUI.displayChanceCard(Lang.msg("desc_buy_MountainVillage"));
                    break;
            case 8: GUI.displayChanceCard(Lang.msg("desc_buy_SouthCitadel"));
                    break;
            case 9: GUI.displayChanceCard(Lang.msg("desc_buy_PalaceGates"));
                    break;
            case 10: GUI.displayChanceCard(Lang.msg("desc_buy_Tower"));
                    break;
            case 11: GUI.displayChanceCard(Lang.msg("desc_buy_Castle"));
                    break;
            case 14: GUI.displayChanceCard(Lang.msg("desc_buy_HutsInTheMountains"));
                    break;
            case 15: GUI.displayChanceCard(Lang.msg("desc_buy_ThePit1") + " 100 " + Lang.msg("desc_buy_ThePit2"));
                    break;
            case 18: GUI.displayChanceCard(Lang.msg("desc_buy_SecondSail"));
                    break;
            case 19: GUI.displayChanceCard(Lang.msg("desc_buy_SeaGrover"));
                    break;
            case 20: GUI.displayChanceCard(Lang.msg("desc_buy_TheBuccaneers"));
                    break;
            case 21: GUI.displayChanceCard(Lang.msg("desc_buy_PrivateArmada"));
                break;
            default:
                break;
        }
   }

   public void showDescriptionCardRent(Player player) {

        switch(player.getCurrentField()) {

            case 1: GUI.displayChanceCard(Lang.msg("desc_rent_TribeEncampment"));
                break;
            case 2: GUI.displayChanceCard(Lang.msg("desc_rent_Crater"));
                break;
            case 3: GUI.displayChanceCard(Lang.msg("desc_rent_Mountain"));
                break;
            case 4: GUI.displayChanceCard(Lang.msg("desc_rent_ColdDesert"));
                break;
            case 5: GUI.displayChanceCard(Lang.msg("desc_rent_BlackCave"));
                break;
            case 6: GUI.displayChanceCard(Lang.msg("desc_rent_TheWerewall"));
                break;
            case 7: GUI.displayChanceCard(Lang.msg("desc_rent_MountainVillage"));
                break;
            case 8: GUI.displayChanceCard(Lang.msg("desc_rent_SouthCitadel"));
                break;
            case 9: GUI.displayChanceCard(Lang.msg("desc_rent_PalaceGates"));
                break;
            case 10: GUI.displayChanceCard(Lang.msg("desc_rent_Tower"));
                break;
            case 11: GUI.displayChanceCard(Lang.msg("desc_rent_Castle"));
                break;
            case 14: GUI.displayChanceCard(Lang.msg("desc_rent_HutsInTheMountains"));
                break;
            case 15: GUI.displayChanceCard(Lang.msg("desc_rent_ThePit1" + " 100 " + "desc_rent_ThePit2"));
                break;
            case 18: GUI.displayChanceCard(Lang.msg("desc_rent_fleet1" + " 100 " + "desc_rent_fleet2"));
                break;
            case 19: GUI.displayChanceCard(Lang.msg("desc_rent_fleet1" + " 100 " + "desc_rent_fleet2"));
                break;
            case 20: GUI.displayChanceCard(Lang.msg("desc_rent_fleet1" + " 100 " + "desc_rent_fleet2"));
                break;
            case 21: GUI.displayChanceCard(Lang.msg("desc_rent_fleet1" + " 100 " + "desc_rent_fleet2"));
                break;
            default:
                break;

        }
    }

    public static void sleep(int n) {
        long t0, t1;
        t0 = System.currentTimeMillis();
        do{
            t1 = System.currentTimeMillis();
        }
        while((t1 - t0) < (n));
    }
}
