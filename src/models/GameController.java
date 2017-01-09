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
import fields.Jail;

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
}
