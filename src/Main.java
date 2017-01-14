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
import chanceCards.FreeBailCard;
import models.PurchaseLogic;
import fields.*;
import lang.Lang;
import models.GameController;
import models.Player;
import GUI.GUIController;

public class Main {

    private static GUIController gui;
    private GameController game;
    private CLIController cli;

    private Main (GameController game, CLIController cli) {
        this.game = game;
        this.cli = cli;
    }

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

        Main main = new Main(new GameController(), new CLIController());

        if (autoGame)
            main.setupAutoGame();
        else
            main.setup();

        System.out.println("========================================");
        main.gameLoop();
    }

    private void gameLoop() {
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("-- Current Player: " + game.getCurrentPlayer() + " --");
        System.out.println("----------------------------------------");
        cli.displayStartBalance(game.getCurrentPlayer());

        // Call aPlayerhasWon() and stop the main method if a player has won the game.
        if (game.getWinner() != null) {
            System.out.println(game.getWinner() + " has won the game.");
            aPlayerHasWon();
            return;
        }

        // TODO: Remove below
//        for (ChanceCard c : ChanceCard.getChanceCards())
//            if (c instanceof FreeBailCard && game.getCurrentPlayer().getPlayerID() == 1)
//                ((FreeBailCard) c).setOwner(game.getCurrentPlayer());

        // If a player hasn't won the game
        if (game.playNormalTurn()) {
            showButtOptions();

            playNormalTurn();
        } else {
            playJailTurn();
        }

        // Then restart the game loop (until a player has won the game)
        gameLoop();
    }

    private void playNormalTurn() {
        int doubleRollCount = game.getCurrentPlayer().getDiceCup().getDoublesRolled();

        // if player gets 3 double rolls, throw player in jail
//        System.out.println("[playNormalTurn]: Double roll = " + game.getCurrentPlayer().getDiceCup().getDoublesRolled());
        if (doubleRollCount == 3) {
            game.throwInJail();
            gui.moveCars(game.getCurrentPlayer());
            cli.displayThreeDoubleRoll();
            cli.displayEndTurn();
            cli.displayEndBalance(game.getCurrentPlayer());
            game.nextPlayer();
            return;
        }

        Field playerLandedOn = game.playerLandedOn();
        // Player landed on a field
        game.playerLandsOnField();
        cli.displayLandedOn(game.getCurrentPlayer());

        // Move the player's car
        gui.moveCars(game.getCurrentPlayer());

//        System.out.println("   [Main 1]: " + game.getCurrentPlayer() + " has kr. " + game.getCurrentPlayer().getPlayerAccount().getBalance());

        // Purchase field if the player can and want to
        if (game.canPurchaseField()) {
            cli.displayCanPurchaseField();
            if (gui.getPurchaseChoice(game.getCurrentPlayer())) {
                game.purchaseCurrentField();
                cli.displayPlayerBoughtField(((Ownable) Field.getFieldByID(game.getCurrentPlayer().getCurrentField())));
            }
        }

        if (playerLandedOn.getFieldId() != game.getCurrentPlayer().getCurrentField())
            gui.moveCars(game.getCurrentPlayer());

        if (playerLandedOn instanceof Rest)
            if (((Rest) playerLandedOn).isJail())
                gui.moveCars(game.getCurrentPlayer());

        // Player passed a field
        game.playerPassedField();
        gui.updateBalance(game.getPlayers());
//        System.out.println("         [Main Balance]: " + game.getCurrentPlayer() + " has kr. " + game.getCurrentPlayer().getPlayerAccount().getBalance());

        // Give extra turn if player rolled a double
        if (game.getCurrentPlayer().getDiceCup().wasRollDouble() && doubleRollCount < 3) {
            showButtOptions();
            playNormalTurn();
        } else {
            // Next Player
            cli.displayEndBalance(game.getCurrentPlayer());
            game.nextPlayer();
        }
    }

    private void playJailTurn() {
        game.getCurrentPlayer().incrementTurnsInJail();

        GUI.GUI.removeAllCars(game.getCurrentPlayer().getPlayerName());
        GUI.GUI.setCar(game.getCurrentPlayer().getCurrentField(), game.getCurrentPlayer().getPlayerName());

        showButtOptions();
//        Show the options for getting out of jail
//        String answer = gui.getJailButtons(
//                game.getJailButtons().contains(Jail.buttons.PAY_BAIL_OUT),
//                game.getJailButtons().contains(Jail.buttons.FREE_BAIL_CARD));
//        if (answer.equals("Roll")answer.equals("Pay bail out. 1000,-")) {
//            game.payBailOut();
//            gui.updateBalance(game.getPlayers());
//            showButtOptions();
//            cli.displayRolled(game.getCurrentPlayer());
//            grantFreedom();
//        }
//
//        if (answer.equals("Use Free Bail Card")) {
//            game.useOwnableCard(FreeBailCard.class);
//            showButtOptions();
//            grantFreedom();
//        }
//
//        if (answer.equals("Roll a double to get out")) {
//            game.rollDice();
//            GUI.GUI.setDice(
//                    game.getCurrentPlayer().getDiceCup().getResultArr()[0],
//                    game.getCurrentPlayer().getDiceCup().getResultArr()[1]);
//            if (game.getCurrentPlayer().getDiceCup().wasRollDouble())
//                grantFreedom();
//            else if (game.getCurrentPlayer().getTurnsInJail() == 3 && gui.getPayBailOut()) {
//                grantFreedom();
//            } else {
//                cli.displayEndBalance(game.getCurrentPlayer());
//                game.nextPlayer();
//            }
//        }
        if (game.getCurrentPlayer().getTurnsInJail() == 3 && gui.getPayBailOut()) {
            grantFreedom();
        } else {
            cli.displayEndBalance(game.getCurrentPlayer());
            game.nextPlayer();
        }
    }

    private void setup() {
        gui = new GUIController();

        int players = gui.selectPlayerCount();

        for (int i = 1; i <= players; i++)
            getPlayerName();

        gui.createPlayers(game.getPlayers());
    }

    private void aPlayerHasWon() {
        if (game.getWinner() != null)
            System.out.println(game.getWinner() + " has won the game!");
    }

    private void getPlayerName() {
        new Player(GUI.GUI.getUserString("Please type your name."));
    }

    private void showButtOptions() {
        String answer = gui.getButtOption(game.getCurrentPlayer().getPlayerName(), game.getButtOptions());

        switch (answer) {
            case "Roll":
                playerRoll();
                break;
            case "Pay bail out. 1000,-":
                payBail();
                break;
            case "Use Free Bail Card":
                freeBail();
                break;
            case "Roll a double to get out":
                checkJailDoubleRoll();
                break;
            case "Buy house/hotel":
                buyBuilding();
                break;
            case "Sell house/hotel":
                sellBuilding();
                break;
            default:
                System.out.println("YOU BROKE IT! WHAT A GOOF!");
                break;
            }
    }

    private void playerRoll() {
        Player currentPlayer = game.getCurrentPlayer();
//        GUI.GUI.getUserButtonPressed(currentPlayer.getPlayerName(), "Roll");
        game.rollDice();

        cli.displayRolled(game.getCurrentPlayer());

        GUI.GUI.setDice(
                currentPlayer.getDiceCup().getResultArr()[0],
                currentPlayer.getDiceCup().getResultArr()[1]);
    }

    private void checkJailDoubleRoll() {
        playerRoll();
        if (game.getCurrentPlayer().getDiceCup().wasRollDouble())
            grantFreedom();
    }

    private void payBail() {
        game.payBailOut();
        gui.updateBalance(game.getPlayers());
        showButtOptions();
        cli.displayRolled(game.getCurrentPlayer());
        grantFreedom();
    }

    private void freeBail() {
        game.useOwnableCard(FreeBailCard.class);
        showButtOptions();
        grantFreedom();
    }

    private void grantFreedom() {
        game.grantFreedom();
        playNormalTurn();
    }

    private void buyBuilding() {
        String answer1 = gui.getLandPlotToBuildOn(game.getAvailablePlotsToBuildOn());

        if (answer1.equals("Back")) {
            showButtOptions();
            return;
        }

        PurchaseLogic.buyHouse(((LandPlot) Field.getFieldByName(answer1)));

        if (((LandPlot) Field.getFieldByName(answer1)).getHouseCount() < 5)
            GUI.GUI.setHouses((LandPlot.getFieldByName(answer1)).getFieldId(), ((LandPlot) Field.getFieldByName(answer1)).getHouseCount());
        else
            GUI.GUI.setHotel((LandPlot.getFieldByName(answer1)).getFieldId(), true);
    }

    private void sellBuilding() {
        String answer2 = gui.getLandPlotToBuildOn(game.getPlayersDevelopedPlots());

        if (answer2.equals("Back")) {
            showButtOptions();
            return;
        }

        PurchaseLogic.sellHouse(((LandPlot) Field.getFieldByName(answer2)));

        if (((LandPlot) Field.getFieldByName(answer2)).getHouseCount() < 5)
            GUI.GUI.setHouses((LandPlot.getFieldByName(answer2)).getFieldId(), ((LandPlot) Field.getFieldByName(answer2)).getHouseCount());
        else
            GUI.GUI.setHotel((LandPlot.getFieldByName(answer2)).getFieldId(), false);
    }

    private void setupAutoGame() {
        gui = new GUIController();

        int[] autoRolls1 = { 6, 2, 1, 3, 7, 5, 6, 7, 7, 6, 2, 3 };
        int[] autoRolls2 = { 2, 5, 4, 11, 5, 3, 4, 5, 5, 5 };
        int[] autoRolls3 = { 3, 6, 6, 6, 7, 7, 6, 2, 3 };

        getAutomatedPlayerName("Dirch", new test_models.AutoDiceCup(autoRolls1));
        getAutomatedPlayerName("Inger", new test_models.AutoDiceCup(autoRolls2));
//        getAutomatedPlayerName("Ove", new test_models.AutoDiceCup(autoRolls3));

        gui.createPlayers(game.getPlayers());
    }

    private void getAutomatedPlayerName(String name, test_models.AutoDiceCup diceCup) {
        new Player(name, diceCup);
    }

}
