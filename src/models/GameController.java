package models;/*
           ,                                             |
          /#\         _         _     _    ___   ___     | Project: Matador - CDIO_final
         /###\       | |__   _ | | __| |  /_  | /_  |    | Version: v0.1.0
        /#####\      | '_ \ / \| |/ _  |    | |   | |    |
       /##,-,##\     | | | | O | | ( | |   _| |_ _| |_   | Anders Wiberg Olsen (s165241), Valentin Leon Christensen (s152735),
      /##(   )##\    |_| |_|\_/|_|\__,_|  |_____|_____|  | Iman Chelhi (s165228), Troels Just Christoffersen (s120052),
     /#.--   --.#\                                       | Sebastian Tibor Bakonyvári (s145918)
    /`           ´\                                      |
*/

import chanceCards.ChanceCard;
import chanceCards.FreeBailCard;
import chanceCards.OwnableCard;
import fields.*;

import java.util.ArrayList;
import java.util.List;

public class GameController
{
    private int playerTurn = 0;
    private final int BAIL_OUT_PRICE = 1000;

    public GameController() {
        ChanceCard.initChanceCards();
    }

    public boolean isNormalTurn() {
        return (getCurrentPlayer().getTurnsInJail() == 0);
    }

    public void rollDice() {
        getCurrentPlayer().getDiceCup().roll();
    }

    public boolean playerHasOwnableCard(Class c) {
        return (OwnableCard.playerHasCard(getCurrentPlayer(), c));
    }

    public int playerTurnsInJail() { return getCurrentPlayer().getTurnsInJail(); }

    public void useOwnableCard(Class c) {
        if (playerTurnsInJail() != 0)
            OwnableCard.useChanceCard(getCurrentPlayer(), c);
    }

    public void payBailOut() {
        if (playerTurnsInJail() != 0)
            getCurrentPlayer().getPlayerAccount().withdraw(this.BAIL_OUT_PRICE);
    }

    public void grantFreedom() {
        getCurrentPlayer().setTurnsInJail(0);
    }

    public boolean canPurchaseField() {
        int playerBalance = getCurrentPlayer().getPlayerAccount().getBalance();
        int currentPlayerFieldId = getCurrentPlayer().getCurrentField();
        Field currentPlayerField = Field.getFieldByID(currentPlayerFieldId);

        if (currentPlayerField instanceof Ownable) {
            boolean canAffordField = playerBalance >= ((Ownable) currentPlayerField).getPrice();
            boolean fieldIsOwned = ((Ownable) currentPlayerField).isOwned();
            return (canAffordField && !fieldIsOwned);
        }

        return false;
    }

    public void purchaseCurrentField() {
        Field currentPlayerField = Field.getFieldByID(getCurrentPlayer().getCurrentField());
        if (currentPlayerField instanceof Ownable)
            if (!((Ownable) currentPlayerField).isOwned())
                ((Ownable) currentPlayerField).purchaseField(getCurrentPlayer()); // Current player buys the current field
    }

    public void playerLandsOnField() {
        Field currentPlayerField = Field.getFieldByID(getCurrentPlayer().getCurrentField());
        currentPlayerField.landOnField(getCurrentPlayer());
    }

    public void playerPassedStart() {
        int currentPlayerFieldId = getCurrentPlayer().getCurrentField();
        int previousPlayerFieldId = getCurrentPlayer().getPreviousField();

        if (previousPlayerFieldId > currentPlayerFieldId)
            ((Rest) Field.getFields()[0]).passedField(getCurrentPlayer());
    }

    public Field playerLandedOn() {
        int totalRolled = getCurrentPlayer().getDiceCup().getTotalEyes();
        getCurrentPlayer().moveCurrentField(totalRolled);
        return Field.getFieldByID(getCurrentPlayer().getCurrentField());
    }

    public void nextPlayer() {
        try {
            getCurrentPlayer().getDiceCup().setDoublesRolled(0);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Player has gone bankrupt.");
        }
        if (playerTurn + 1 < Player.getPlayers().size()) {
            playerTurn++;
        } else {
            playerTurn = 0;
        }
    }

    public Player getCurrentPlayer() {
        return Player.getPlayers().get(playerTurn);
    }

    public Player getWinner() {
        if (Player.getPlayers().size() == 1)
            return Player.getPlayers().get(0);
        else
            return null;
    }

    public void throwInJail() {
        getCurrentPlayer().incrementTurnsInJail();
        getCurrentPlayer().setCurrentField(11);
    }

    public List<Player> getPlayers() {
        return Player.getPlayers();
    }

    public List<String> getButtOptions() {

        List<String> buttOpts = new ArrayList<>();

        if (getCurrentPlayer().getTurnsInJail() > 0) {
            buttOpts.add("Roll a double to get out");

            if (getCurrentPlayer().getPlayerAccount().getBalance() >= 1000)
            buttOpts.add("Pay bail out. 1000,-");

            if (OwnableCard.playerHasCard(getCurrentPlayer(), FreeBailCard.class))
                buttOpts.add("Use Free Bail Card");
        }
        else if (!getCurrentPlayer().getDiceCup().getHasRolled() || getCurrentPlayer().getDiceCup().wasRollDouble())
            buttOpts.add("Roll");

        else
            buttOpts.add("End turn");

        if (PurchaseLogic.playerCanDevelopPlots(getCurrentPlayer()))
            buttOpts.add("Buy house/hotel");

        if (PurchaseLogic.getPlayerBuildingCount(getCurrentPlayer()) != 0)
            buttOpts.add("Sell house/hotel");
        return buttOpts;
    }

    public LandPlot[] getAvailablePlotsToBuildOn() {
        return PurchaseLogic.getAvailablePlotsToBuildOn(getCurrentPlayer()).stream().toArray(LandPlot[]::new);
    }

    public void checkBankruptcy() {
        if (getCurrentPlayer().getPlayerAccount().getBalance() < 0) {
            Ownable.resetPlayersPlots(getCurrentPlayer());
            OwnableCard.resetOwnableCards(getCurrentPlayer());
            getCurrentPlayer().removePlayer();
        }
    }

    public Ownable[] getOwnedOwnables() { return Ownable.getOwnedOwnables(); }

    public LandPlot[] getPlayersDevelopedPlots() {
        return PurchaseLogic.getPlayersDevelopedPlots(getCurrentPlayer()).stream().toArray(LandPlot[]::new);
    }
}