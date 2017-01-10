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

    public GameController() {

    }

    public boolean playNormalTurn() {
        return (!getCurrentPlayer().isInJail());
    }

    public List<Jail.buttons>getJailButtons() {
        List<Jail.buttons> jailButtons = new ArrayList<>();

        if (getCurrentPlayer().getPlayerAcct().getBalance() >= 1000) {
            jailButtons.add(Jail.buttons.PAY_BAIL_OUT);
        }
        if (OwnableCard.playerHasCard(getCurrentPlayer(), FreeBailCard.class)) {
            jailButtons.add(Jail.buttons.FREE_BAIL_CARD);
        }

        return jailButtons;
    }

    public boolean canPurchaseField() {
        int playerBalance = getCurrentPlayer().getPlayerAcct().getBalance();
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
                ((Ownable) currentPlayerField).purchaseField(getCurrentPlayer()); // Current players buys the current field
    }

    public void playerLandsOnField() {
        Field currentPlayerField = Field.getFieldByID(getCurrentPlayer().getCurrentField());
        currentPlayerField.landOnField(getCurrentPlayer());
    }

    public void playerPassedField() {
        int currentPlayerFieldId = getCurrentPlayer().getCurrentField();
        int previousPlayerFieldId = getCurrentPlayer().getPreviousField();
        Field currentPlayerField = Field.getFieldByID(currentPlayerFieldId);

        if (previousPlayerFieldId > currentPlayerFieldId)
            ((Rest) Field.getFields()[0]).passedField(getCurrentPlayer());
    }

    public Field playerLandedOn() {
        int totalRolled = getCurrentPlayer().getDiceCup().getTotalEyes();
        getCurrentPlayer().moveCurrentField(totalRolled);
        return null; // TODO: Wait for Field.getFieldById(int);
    }

    public void nextPlayer() {
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

    public List<Player> getPlayers() { return Player.getPlayers(); }
}
