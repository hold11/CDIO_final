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

import CLI.CLIController;
import fields.*;
import lang.Lang;
import models.GameController;
import models.Player;
import GUI.GUIController;
import models.PurchaseLogic;

public class Main {

    public static void main(String[] args) {
        Lang.setLanguage(args);
        CLIController cli = new CLIController(); // For testing purposes
        PurchaseLogic prl = new PurchaseLogic();

        Player p1 = new Player();
        Player p2 = new Player();

        p1.setCurrentField(9);

        for (LandPlot l : LandPlot.getPlotGroup(2))
            System.out.println(l.toString());
        GUI.GUIController.sleep(200);
        if(LandPlot.hasAllPlotsInGroup(p1, 2))
            System.out.println(p1.getPlayerName() + " has all the plots in the group!");
        else
            System.out.println(p1.getPlayerName() + " doesn't have all the plots in the group!");
        ((LandPlot) Field.getFields()[6]).purchaseField(p1);
        if(LandPlot.hasAllPlotsInGroup(p1, 2))
            System.out.println(p1.getPlayerName() + " has all the plots in the group!");
        else
            System.out.println(p1.getPlayerName() + " doesn't have all the plots in the group!");
        ((LandPlot) Field.getFields()[8]).purchaseField(p1);
        if(LandPlot.hasAllPlotsInGroup(p1, 2))
            System.out.println(p1.getPlayerName() + " has all the plots in the group!");
        else
            System.out.println(p1.getPlayerName() + " doesn't have all the plots in the group!");

        ((LandPlot) Field.getFields()[9]).purchaseField(p1);
        if(LandPlot.hasAllPlotsInGroup(p1, 2))
            System.out.println(p1.getPlayerName() + " has all the plots in the group!");
        else
            System.out.println(p1.getPlayerName() + " doesn't have all the plots in the group!");

        for (int i=0 ; i < 7 ; i++) {
            prl.buyHouse(((LandPlot) Field.getFields()[9]));
            System.out.println((Field.getFields()[9]).toString() + " contains " + ((LandPlot) Field.getFields()[9]).getHouseCount() + " Houses");
            System.out.println(p1.getPlayerName() + " has "+ prl.getTotalHotelCount() + " Hotels");
            System.out.println(p1.getPlayerName() + " has "+ prl.getTotalHouseCount() + " Houses");
        }


//        setup();

//        gameLoop();
    }

    private static void gameLoop() {
        GameController game = new GameController();

        // Call aPlayerhasWon() and stop the main method if a player has won the game.
        if (game.getWinner() != null) {
            aPlayerHasWon(game);
            return;
        }

        // If a player hasn't won the game
        if (game.playNormalTurn()) {
            playNormalTurn(game);
        } else {
            playJailTurn(game);
        }

        // Then restart the game loop (until a player has won the game)
        gameLoop();
    }

    private static void playNormalTurn(GameController game) {
        playerRoll(game.getCurrentPlayer());
        Field playerLandedOn = game.playerLandedOn();
    }

    private static void playJailTurn(GameController game) {
        if (game.getJailButtons().contains(Jail.buttons.PAY_BAIL_OUT)) {
            // Show pay bail out button
        }
        if (game.getJailButtons().contains(Jail.buttons.FREE_BAIL_CARD)) {
            // Show use free bail card button
        }

        // Show roll button
        // Show sell house button (if the player owns any houses that is)
    }

    private static void setup() {
        GUIController gui = new GUIController();

        int players = gui.selectPlayerCount();
            for (int i = 1; i <= players; i++)
                getPlayerName();
        gui.createPlayers();

    }

    private static void aPlayerHasWon(GameController game) {
        if (game.getWinner() != null)
            System.out.println(game.getWinner() + " has won the game!");
    }

    private static void getPlayerName() {
        Player p = new Player(GUI.GUI.getUserString(" please type your name"));
    }

    private static void playerRoll(Player player) {
        GUI.GUI.getUserButtonPressed(player.getPlayerName() + "! Roll for adventure and glory!", "Roll!");
        player.getDiceCup().roll();
        GUI.GUI.setDice(player.getDiceCup().getResultArr()[0], player.getDiceCup().getResultArr()[1]);
    }
}
