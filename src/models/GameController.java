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

    }

    public boolean playNormalTurn() {
        return (getCurrentPlayer().getTurnsInJail() == 0);
    }

    public void rollDice() {
//        if (getCurrentPlayer().getDiceCup().getDoublesRolled() == 3) {
//            throwInJail();
//            nextPlayer();
//        }

        getCurrentPlayer().getDiceCup().roll();
    }

    public List<Jail.buttons>getJailButtons() {
        List<Jail.buttons> jailButtons = new ArrayList<>();

        if (getCurrentPlayer().getPlayerAccount().getBalance() >= 1000) {
            jailButtons.add(Jail.buttons.PAY_BAIL_OUT);
        }
        if (OwnableCard.playerHasCard(getCurrentPlayer(), FreeBailCard.class)) {
            jailButtons.add(Jail.buttons.FREE_BAIL_CARD);
        }

        return jailButtons;
    }

    public boolean playerHasOwnableCard(Class c) {
        return (OwnableCard.playerHasCard(getCurrentPlayer(), c));
    }

    public boolean playerIsInJail() { return (getCurrentPlayer().getTurnsInJail() != 0); }

    public void useOwnableCard(Class c) {
        if (playerIsInJail())
            OwnableCard.useChanceCard(getCurrentPlayer(), c);
    }

    public void payBailOut() {
        if (playerIsInJail())
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

    public void playerPassedField() {
        int currentPlayerFieldId = getCurrentPlayer().getCurrentField();
        int previousPlayerFieldId = getCurrentPlayer().getPreviousField();

        if (previousPlayerFieldId > currentPlayerFieldId)
            ((Rest) Field.getFields()[0]).passedField(getCurrentPlayer());
    }

    public Field playerLandedOn() {
        int totalRolled = getCurrentPlayer().getDiceCup().getTotalEyes();
        getCurrentPlayer().moveCurrentField(totalRolled);
        return Field.getFieldByID(getCurrentPlayer().getCurrentField()); // TODO: Wait for Field.getFieldById(int);
    }

    public void nextPlayer() {
        getCurrentPlayer().getDiceCup().setDoublesRolled(0);
        if (playerTurn + 1 < Player.getPlayers().size()) {
            playerTurn++;
        } else {
            playerTurn = 0;
        }
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public Player getCurrentPlayer() {
        return Player.getPlayers().get(getPlayerTurn());
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

    public LandPlot[] getAvailablePlotsToBuildOn() {
        return PurchaseLogic.getAvailablePlotsToBuildOn(getCurrentPlayer()).stream().toArray(LandPlot[]::new);
    }
}
