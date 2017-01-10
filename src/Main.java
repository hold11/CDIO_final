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
        // TODO: Put the following in a separate method:
        String[] locale = new String[2];
        boolean autoGame = false;
        if (args.length == 1 || args.length == 3) {
            if (args[0] == "auto") {
                autoGame = true;
            }

            if (args.length == 3) {
                locale[0] = args[1];
                locale[1] = args[2];
            } else {
                locale[0] = "da";
                locale[1] = "DK";
            }
        } else {
            locale[0] = "da";
            locale[1] = "DK";
        }

        Lang.setLanguage(locale);
        CLIController cli = new CLIController(); // For testing purposes
        GameController game = new GameController();
        // TODO: commented out for testing purposes
//        if (!autoGame)
//            setup(game);
//        else
            setupAutoGame(game);

        gameLoop(game);
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

    private static void playNormalTurn(GameController game) {
        // Start by rolling the dice
        playerRoll(game.getCurrentPlayer());
        Field playerLandedOn = game.playerLandedOn(); //TODO: Do we need this?

        // Move the player's car
        gui.moveCars(game.getCurrentPlayer());

        System.out.println("   [Main 1]: " + game.getCurrentPlayer() + " has kr. " + game.getCurrentPlayer().getPlayerAcct().getBalance());

        // Purchase field if the player can and want to
        if (game.canPurchaseField())
            if (gui.getPlayerPurchaseChoice(game.getCurrentPlayer()))
                game.purchaseCurrentField();

        // Player landed on a field
        game.playerLandsOnField();
        gui.updateBalance(game.getPlayers());
        System.out.println("   [Main 2]: " + game.getCurrentPlayer() + " has kr. " + game.getCurrentPlayer().getPlayerAcct().getBalance());

        // Next Player
        game.nextPlayer();
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

        int[] autoRolls = { 3, 7, 11, 7, 7, 6, 2, 3};

        getAutomatedPlayerName("Dirch", new test_models.AutoDiceCup(autoRolls));
        getAutomatedPlayerName("Inger", new test_models.AutoDiceCup(autoRolls));
        // for testing, Inger buys Roskildevej right away.
        ((Ownable) Field.getFields()[6]).purchaseField(Player.getPlayers().get(1));

        getAutomatedPlayerName("Ove", new test_models.AutoDiceCup(autoRolls));
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
