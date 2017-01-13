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
import chanceCards.ChanceCard;
import chanceCards.FreeBailCard;
import chanceCards.OwnableCard;
import fields.*;
import lang.Lang;
import models.GameController;
import models.Player;
import GUI.GUIController;

public class Main {

    private static GUIController gui;

    public static void main(String[] args) {
        // TODO: Put the following in a separate method:
        String[] locale = new String[2];
        boolean autoGame = false;
        if (args.length == 1 || args.length == 3) {
            if (args[0].equals("auto")) {
                autoGame = true;
                System.out.println("[Autogame enabled]");
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
        if (autoGame)
            setupAutoGame(game);
        else
            setup(game);
        // TODO: commented out for testing purposes

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

        // set players double roll count back to 0
        game.getCurrentPlayer().getDiceCup().setDoublesRolled(0);

        // Then restart the game loop (until a player has won the game)
        gameLoop(game);
    }

    private static void playNormalTurn(GameController game) {
        // Start by rolling the dice
        int doubleRollCount = game.getCurrentPlayer().getDiceCup().getDoublesRolled();

        playerRoll(game.getCurrentPlayer());

        // if player gets 3 double rolls, throw player in jail
        if (doubleRollCount == 3)
            game.throwInJail();

        Field playerLandedOn = game.playerLandedOn(); //TODO: Do we need this?

        // Move the player's car
        gui.moveCars(game.getCurrentPlayer());

//        System.out.println("   [Main 1]: " + game.getCurrentPlayer() + " has kr. " + game.getCurrentPlayer().getPlayerAcct().getBalance());

        // Purchase field if the player can and want to
        if (game.canPurchaseField())
            if (gui.getPurchaseChoice(game.getCurrentPlayer()))
                game.purchaseCurrentField();

        // Player landed on a field
        game.playerLandsOnField();
        // Player passed a field
        game.playerPassedField();
        gui.updateBalance(game.getPlayers());
        System.out.println("         [Main Balance]: " + game.getCurrentPlayer() + " has kr. " + game.getCurrentPlayer().getPlayerAcct().getBalance());


        if (game.getCurrentPlayer().getDiceCup().wasRollDouble()) {
            // Check for double roll and give extra turn
            if (doubleRollCount < 3)
                playNormalTurn(game);
        }
        else
            // Next Player
            game.nextPlayer();
    }

    private static void playJailTurn(GameController game) {
        if (game.getCurrentPlayer().getTurnsInJail() != 3) {
            game.getCurrentPlayer().incrementTurnsInJail();

            GUI.GUI.removeAllCars(game.getCurrentPlayer().toString());
            GUI.GUI.setCar(11, game.getCurrentPlayer().toString());

            // Show the options for getting out of jail. Return is a String.
            String answer = gui.getJailButtons(game.getJailButtons().contains(Jail.buttons.PAY_BAIL_OUT), game.getJailButtons().contains(Jail.buttons.FREE_BAIL_CARD));

            if (answer.equals("Pay bail out.")) {
                game.getCurrentPlayer().getPlayerAcct().withdraw(1000);
                grantFreedom(game);
            }

            if (answer.equals("Use Free Bail Card.")) {
                for (OwnableCard o : OwnableCard.getPlayersCards(game.getCurrentPlayer()))
                    if (o instanceof FreeBailCard) {
                        o.removeOwner();
                        break;
                    }
                grantFreedom(game);
            }

            if (answer.equals("Roll")) {
                game.getCurrentPlayer().getDiceCup().roll();
                GUI.GUI.setDice(game.getCurrentPlayer().getDiceCup().getResultArr()[0], game.getCurrentPlayer().getDiceCup().getResultArr()[1]);
                if (game.getCurrentPlayer().getDiceCup().wasRollDouble()) {
                    grantFreedom(game);
                } else
                    game.nextPlayer();
            }

//            if (game.getJailButtons().contains(Jail.buttons.PAY_BAIL_OUT)) {
//                // Show pay bail out button
//                if (gui.getPayBailOut()) {
//                    game.getCurrentPlayer().getPlayerAcct().withdraw(1000);
//                    grantFreedom(game);
//                }
//            }
//            if (game.getJailButtons().contains(Jail.buttons.FREE_BAIL_CARD)) {
//                // Show use free bail card button
//                if (gui.getFreeBailCard()) {
//                    // TODO: Remove Free Bail Card from player
//                    grantFreedom(game);
//                }
//            }
        } else
            game.getCurrentPlayer().setTurnsInJail(0);
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

        int[] autoRolls1 = { 3, 7, 5, 6, 7, 7, 6, 2, 3 };
        int[] autoRolls2 = { 5, 4, 11, 5, 3, 4, 5, 5, 5 };
        int[] autoRolls3 = { 3, 6, 6, 6, 7, 7, 6, 2, 3 };

        getAutomatedPlayerName("Dirch", new test_models.AutoDiceCup(autoRolls1));
        getAutomatedPlayerName("Inger", new test_models.AutoDiceCup(autoRolls2));
        getAutomatedPlayerName("Ove", new test_models.AutoDiceCup(autoRolls3));
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
        GUI.GUI.getUserButtonPressed(player.getPlayerName() + "", "Roll!");
        player.getDiceCup().roll();
        GUI.GUI.setDice(player.getDiceCup().getResultArr()[0], player.getDiceCup().getResultArr()[1]);
    }

    private static void grantFreedom(GameController game) {
        game.getCurrentPlayer().setTurnsInJail(0);
        playNormalTurn(game);
    }
}
