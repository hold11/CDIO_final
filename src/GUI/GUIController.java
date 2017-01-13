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


import GUI.backend.Car;
import models.Player;

import java.awt.*;
import java.util.List;

public class GUIController {
    private Car[] cars = new Car[6];

    public GUIController() { }

    private void initMovers() {

        cars[0] = new Car.Builder()
                .typeCar()
                .primaryColor(new Color(22, 154,255))
                .secondaryColor(new Color(255, 21, 208))
                .patternZebra()
                .build();

        cars[1] = new Car.Builder()
                .typeCar()
                .primaryColor(Color.GREEN)
                .secondaryColor(Color.WHITE)
                .patternZebra()
                .build();

        cars[2] = new Car.Builder()
                .typeCar()
                .primaryColor(Color.WHITE)
                .secondaryColor(Color.RED)
                .patternZebra()
                .build();

        cars[3] = new Car.Builder()
                .typeCar()
                .primaryColor(Color.YELLOW)
                .secondaryColor(Color.GREEN)
                .patternZebra()
                .build();

        cars[4] = new Car.Builder()
                .typeCar()
                .primaryColor(Color.BLUE)
                .secondaryColor(Color.ORANGE)
                .patternZebra()
                .build();

        cars[5] = new Car.Builder()
                .typeCar()
                .primaryColor(Color.WHITE)
                .secondaryColor(new Color(22, 154,255))
                .patternZebra()
                .build();
    }

    public int selectPlayerCount() {
        return Integer.parseInt(GUI.getUserSelection("Select players",  "3", "4", "5", "6"));
    }

    public void createPlayers(List<Player> players) {
        initMovers();

        for (int i = 0; i < players.size(); i++)
            GUI.addPlayer(players.get(i).getPlayerName(), players.get(i).getPlayerAcct().getBalance(), cars[i]);
    }

    public void moveCars(Player player) {
        if (player.getPreviousField() >= player.getCurrentField()) {
            for (int i = player.getPreviousField() ; i <= 40 ; i++) {
                sleep(125);
                GUI.removeAllCars(player.getPlayerName());
                GUI.setCar(i, player.getPlayerName());
            }
            for (int i = 1 ; i <= player.getCurrentField(); i++) {
                sleep(125);
                GUI.removeAllCars(player.getPlayerName());
                GUI.setCar(i, player.getPlayerName());
            }
        } else {
            for (int i = player.getPreviousField(); i <= player.getCurrentField(); i++) {
                sleep(125);
                GUI.removeAllCars(player.getPlayerName());
                GUI.setCar((i), player.getPlayerName());
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

    public void updateBalance(List<Player> players) {
        for (Player player : players)
            GUI.setBalance(player.getPlayerName(), player.getPlayerAcct().getBalance());
    }

    public boolean getPurchaseChoice(Player player) {
        String answer = GUI.getUserButtonPressed("Want to purchase this field?", "Yes!", "No!");
        if (answer.equals("Yes!")) {
            GUI.setOwner(player.getCurrentField(), player.getPlayerName());
            return true;
        } else return false;
    }

    public String getJailButtons(boolean freeBail, boolean payBailOut) {
        if (freeBail && payBailOut)
            return GUI.getUserButtonPressed("", "Roll", "Use Free Bail Card.", "Pay bail out. 1000,-");
        else if (freeBail)
            return GUI.getUserButtonPressed("", "Roll", "Use Free Bail Card.");
        else if (payBailOut)
            return GUI.getUserButtonPressed("", "Roll", "Pay bail out. 1000,-");
        else
            return GUI.getUserButtonPressed("", "Roll");
    }

    public boolean getPayBailOut() {
        return GUI.getUserButtonPressed("", "Pay bail out. 1000,-").equals("Pay bail out. 1000,-");
    }

    private static void sleep(int n) {
        long t0, t1;
        t0 = System.currentTimeMillis();
        do{
            t1 = System.currentTimeMillis();
        }
        while((t1 - t0) < (n));
    }
}
