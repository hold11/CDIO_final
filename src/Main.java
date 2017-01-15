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
import models.PurchaseLogic;
import fields.*;
import lang.Lang;
import models.GameController;
import models.Player;
import GUI.GUIController;

import java.util.Arrays;

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
        if (game.isNormalTurn())
            playNormalTurn();
        else
            playJailTurn();

        // Then restart the game loop (until a player has won the game)
        gameLoop();
    }

    private void checkBankruptcy() {
        if (game.getCurrentPlayer().getPlayerAccount().getBalance() < 0) {
            GUI.GUI.removeAllCars(game.getCurrentPlayer().getPlayerName());

            Arrays.stream(game.getOwnedOwnables())
                    .filter(o -> o instanceof LandPlot)
                    .filter(o -> o.getOwner().equals(game.getCurrentPlayer()))
                    .forEach(o -> {
                        GUI.GUI.setHouses(o.getFieldId(), 0);
                        GUI.GUI.setHotel(o.getFieldId(), false);
                    });

            Arrays.stream(game.getOwnedOwnables())
                    .filter(o -> o.getOwner().equals(game.getCurrentPlayer()))
                    .forEach(o -> GUI.GUI.removeOwner(o.getFieldId()));

            game.checkBankruptcy();
            gui.createPlayers(game.getPlayers());
        }
    }

    private void playNormalTurn() {
        showButtOptions();

        int doubleRollCount = game.getCurrentPlayer().getDiceCup().getDoublesRolled();

        // if player gets 3 double rolls, throw player in jail
        if (doubleRollCount == 3) {
            game.throwInJail();
            GUI.GUI.setCar(game.getCurrentPlayer().getCurrentField(), game.getCurrentPlayer().getPlayerName());
            cli.displayThreeDoubleRoll();
            cli.displayEndTurn();
            cli.displayEndBalance(game.getCurrentPlayer());
            game.nextPlayer();
            return;
        }

        // Player landed on a field
        Field landedOn = game.playerLandedOn();
        gui.moveCars(game.getCurrentPlayer());
        cli.displayLandedOn(game.getCurrentPlayer());
        if (landedOn instanceof ChanceField) {
            gui.showChanceCard(ChanceCard.getCurrentChanceCard().toString());
            game.playerLandsOnField();
            gui.moveCars(game.getCurrentPlayer());
        } else
            game.playerLandsOnField();




        // Purchase field if the player can and want to
        if (game.canPurchaseField()) {
            cli.displayCanPurchaseField();
            if (gui.getPurchaseChoice(game.getCurrentPlayer())) {
                game.purchaseCurrentField();
                cli.displayPlayerBoughtField(((Ownable) Field.getFieldByID(game.getCurrentPlayer().getCurrentField())));
            }
        }

//        if (playerLandedOn.getFieldId() != game.getCurrentPlayer().getCurrentField())
//            gui.moveCars(game.getCurrentPlayer());

        // Player passed start?
        game.playerPassedStart();

        // Update balance of all players
        gui.updateBalance(game.getPlayers());

        // Give extra turn if player rolled a double
        if (game.getCurrentPlayer().getDiceCup().wasRollDouble() && doubleRollCount < 3) {
            playNormalTurn();
        } else {
            // Last chance to do business then next player
            showButtOptions();
            cli.displayEndBalance(game.getCurrentPlayer());
            checkBankruptcy();
            game.getCurrentPlayer().getDiceCup().resetHasRolled();
            game.nextPlayer();
        }
    }

    private void playJailTurn() {
        game.getCurrentPlayer().incrementTurnsInJail();

        GUI.GUI.removeAllCars(game.getCurrentPlayer().getPlayerName());
        GUI.GUI.setCar(game.getCurrentPlayer().getCurrentField(), game.getCurrentPlayer().getPlayerName());

        showButtOptions();

        if (game.playerTurnsInJail() == 3 && gui.getPayBailOut()) {
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
            System.out.println(game.getWinner() + " h as won the game!");
    }

    private void getPlayerName() {
        new Player(GUI.GUI.getUserString(Lang.msg("getName")));
    }

    private void showButtOptions() {
        if (game.getButtOptions().size() == 0)
            return;

        String answer = gui.getButtOption(game.getCurrentPlayer().getPlayerName(), game.getButtOptions());

        switch (answer) {
            case "Kast terninger":
                playerRoll();
                break;
            case "Afslut tur":
                break;
            case "Betal din bøde på 1000 kr.":
                payBail();
                break;
            case "Brug Kom-Ud-Af-Fængsel-kort©":
                freeBail();
                break;
            case "Kast og få to ens":
                checkJailDoubleRoll();
                break;
            case "Køb hus/hotel":
                buyBuilding();
                showButtOptions();
                break;
            case "Sælg hus/hotel":
                sellBuilding();
                showButtOptions();
                break;
            default:
                System.out.println("YOU BROKE IT! WHAT A GOOF!");
                break;
            }
    }

    private void playerRoll() {
        Player currentPlayer = game.getCurrentPlayer();
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
        grantFreedom();
    }

    private void freeBail() {
        game.useOwnableCard(FreeBailCard.class);
        grantFreedom();
    }

    private void grantFreedom() {
        game.grantFreedom();
        playNormalTurn();
    }

    private void buyBuilding() {
        String answer = gui.getLandPlotToBuildOn(game.getAvailablePlotsToBuildOn());

        if (answer.equals(Lang.msg("back")))
            return;

        PurchaseLogic.buyHouse(((LandPlot) Field.getFieldByName(answer)));

        if (((LandPlot) Field.getFieldByName(answer)).getHouseCount() < 5)
            GUI.GUI.setHouses((LandPlot.getFieldByName(answer)).getFieldId(), ((LandPlot) Field.getFieldByName(answer)).getHouseCount());
        else
            GUI.GUI.setHotel((LandPlot.getFieldByName(answer)).getFieldId(), true);
    }

    private void sellBuilding() {
        String answer = gui.getLandPlotToBuildOn(game.getPlayersDevelopedPlots());

        if (answer.equals(Lang.msg("back")))
            return;

        PurchaseLogic.sellHouse(((LandPlot) Field.getFieldByName(answer)));

        if (((LandPlot) Field.getFieldByName(answer)).getHouseCount() < 4)
            GUI.GUI.setHouses((LandPlot.getFieldByName(answer)).getFieldId(), ((LandPlot) Field.getFieldByName(answer)).getHouseCount());
        else
            GUI.GUI.setHotel((LandPlot.getFieldByName(answer)).getFieldId(), false);
    }

    private void setupAutoGame() {
        gui = new GUIController();

        int[] d1p1 = { 1, 6, 5, 6, 5, 2, 5, 5, 1, 6 };
        int[] d2p1 = { 2, 1, 3, 2, 6, 2, 3, 4, 6, 5 };

        int[] d1p2 = { 1, 6, 5, 4, 4, 4, 4, 3, 4, 5 };
        int[] d2p2 = { 5, 4, 1, 3, 3, 3, 3, 2, 6, 3 };

        int[] d1p3 = { 5, 2, 4, 6, 5, 3, 6, 4, 6, 5 };
        int[] d2p3 = { 3, 3, 3, 4, 2, 2, 5, 2, 1, 3 };

        getAutomatedPlayerName("Dirch", new test_models.AutoDiceCup(d1p1, d2p1));
        getAutomatedPlayerName("Inger", new test_models.AutoDiceCup(d1p2, d2p2));
        getAutomatedPlayerName("Ove", new test_models.AutoDiceCup(d1p3, d2p3));

        ((Ownable) Field.getFields()[6]).purchaseField(Player.getPlayers().get(0));
        ((Ownable) Field.getFields()[8]).purchaseField(Player.getPlayers().get(0));
        ((Ownable) Field.getFields()[9]).purchaseField(Player.getPlayers().get(0));

        gui.createPlayers(game.getPlayers());
    }

    private void getAutomatedPlayerName(String name, test_models.AutoDiceCup diceCup) {
        new Player(name, diceCup);
    }

}
