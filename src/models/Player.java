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

import fields.Field;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int playerID;
    private final String playerName;
    private final BankAccount playerAccount;
    private final DiceCup diceCup;
    private int currentField;
    private int previousField;
    private int turnsInJail = 0;
    private static final int STARTING_BALANCE = 30000;

    private static int nextPlayerId = 1; // This variable is for defining the player ID for the next player that gets constructed.
    private static List<Player> players = new ArrayList<>();

    public Player() {
        this.playerID = nextPlayerId;
        nextPlayerId++;

        this.currentField = 1;
        this.previousField = 1;

        this.playerName = String.format("Player %s", this.playerID);
        this.diceCup = new DiceCup();
        this.playerAccount = Bank.createBankAcct(STARTING_BALANCE);
        players.add(this);
    }

    public Player(DiceCup diceCup) {
        this.playerName = String.format("Player %s", nextPlayerId);
        this.playerID = nextPlayerId;
        this.diceCup = diceCup;

        this.playerAccount = Bank.createBankAcct(STARTING_BALANCE);
        nextPlayerId++;

        this.currentField = 1;
        this.previousField = 1;

        players.add(this);

    }

    public Player(String playerName) {
        this.playerName = playerName;
        this.playerID = nextPlayerId;
        this.diceCup = new DiceCup();

        this.playerAccount = new BankAccount(STARTING_BALANCE);

        nextPlayerId++;

        this.currentField = 1;
        this.previousField = 1;

        players.add(this);
    }

    public Player(String playerName, DiceCup diceCup) {
        this.playerName = playerName;
        this.playerID = nextPlayerId;
        this.diceCup = diceCup;

        this.playerAccount = new BankAccount(STARTING_BALANCE);

        nextPlayerId++;

        this.currentField = 1;
        this.previousField = 1;

        players.add(this);
    }

    public void setPlayerField(int fieldID) { this.currentField = fieldID; }

    public BankAccount getPlayerAccount() {
        return playerAccount;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public static List<Player> getPlayers()
    {
        return players;
    }

    public String toString() {
        return this.playerName;
    }

    public DiceCup getDiceCup() {
        return this.diceCup;
    }

    public int getPreviousField() {
        return this.previousField;
    }

    public int getCurrentField() {
        return this.currentField;
    }

    public void moveCurrentField(int diceCount) {
        if (this.currentField + diceCount > Field.getFields().length)
            this.setCurrentField(diceCount - Field.getFields().length + this.currentField);
        else
            this.setCurrentField(this.currentField + diceCount);
    }

    public void setCurrentField(int fieldID) {
        if (this.currentField == 0)
            this.previousField = this.currentField + 1;
        else
            this.previousField = this.currentField;
        this.currentField = fieldID;
    }

    public int getTurnsInJail() {
        return this.turnsInJail;
    }

    public void incrementTurnsInJail() {
        this.turnsInJail += 1;
    }

    public void setTurnsInJail(int amount) {
        this.turnsInJail = amount;
    }

    public void removePlayer() {
        players.remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (playerID != player.playerID) return false;
        if (currentField != player.currentField) return false;
        if (previousField != player.previousField) return false;
        if (turnsInJail != player.turnsInJail) return false;
        if (playerName != null ? !playerName.equals(player.playerName) : player.playerName != null) return false;
        if (playerAccount != null ? !playerAccount.equals(player.playerAccount) : player.playerAccount != null) return false;
        return diceCup != null ? diceCup.equals(player.diceCup) : player.diceCup == null;
    }

    public static void reset() {
        players.clear();
        nextPlayerId = 1;
    }
}
