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
import test_models.AutoDiceCup;

public class Main {

    private static GUIController gui;

    public static void main(String[] args) {
        Lang.setLanguage(args);
        CLIController cli = new CLIController(); // For testing purposes
        GameController game = new GameController();
//        setup(game);
//
//        gameLoop(game);
    }

    private static void gameLoop(GameController game) {


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
        gameLoop(game);
    }

    static int test = 0;
    private static void playNormalTurn(GameController game) {
        playerRoll(game.getCurrentPlayer());
        Field playerLandedOn = game.playerLandedOn();
        gui.moveCars(game.getCurrentPlayer());
        System.out.println("Can Purchase: " + game.canPurchaseField());

        if (game.canPurchaseField())
            if (gui.getPlayerPurchaseChoice(game.getCurrentPlayer()))
                game.purchaseCurrentField();
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

    private static void setup(GameController game) {
        gui = new GUIController();

        int players = gui.selectPlayerCount();
            for (int i = 1; i <= players; i++)
                getPlayerName();
        gui.createPlayers(game.getPlayers());
    }

    private static void setupAutoGame(GameController game) {
        gui = new GUIController();

        getAutomatedPlayerName("Bent", new test_models.AutoDiceCup());
        getAutomatedPlayerName("Knud", new test_models.AutoDiceCup());
        getAutomatedPlayerName("Ove", new test_models.AutoDiceCup());
        gui.createPlayers(game.getPlayers());

    }

    private static void aPlayerHasWon(GameController game) {
        if (game.getWinner() != null)
            System.out.println(game.getWinner() + " has won the game!");
    }

    private static void getPlayerName() {
        Player p = new Player(GUI.GUI.getUserString(" please type your name"));
    }

    private static void getAutomatedPlayerName(String name, test_models.AutoDiceCup diceCup) {
        Player p = new Player(name, diceCup);
    }

    private static void playerRoll(Player player) {
        GUI.GUI.getUserButtonPressed(player.getPlayerName() + "! Roll for adventure and glory!", "Roll!");
        player.getDiceCup().roll();
        GUI.GUI.setDice(player.getDiceCup().getResultArr()[0], player.getDiceCup().getResultArr()[1]);
    }
}
