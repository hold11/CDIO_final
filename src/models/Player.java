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

import java.util.ArrayList;
import java.util.List;

public class Player
{
    private int playerID;
    private String playerName;
    private BankAcct playerAcct;
    private DiceCup diceCup;
    private fields.Field currentField;
    private fields.Field previousField;
    private final int STARTING_BALANCE = 10000;

    private static int nextPlayerID = 1; // This variable is for defining the player ID for the next player that gets constructed.
    private static List<Player> players = new ArrayList<>();

    public Player() {
        this.playerID = nextPlayerID;
        nextPlayerID++;

        this.playerName = String.format("Player %s", this.playerID);
        this.diceCup = new DiceCup();
        this.playerAcct = Bank.createBankAcct();
        players.add(this);
    }

    public BankAcct getPlayerAcct() { return this.playerAcct; }

    public static List<Player> getPlayers() { return players; }
}
