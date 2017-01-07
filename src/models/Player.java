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
    private int playerID;
    private String playerName;
    private BankAcct playerAcct;
    private DiceCup diceCup;
    private int currentField;
    private int previousField;
    private static final int STARTING_BALANCE = 10000;

    private static int nextPlayerID = 1; // This variable is for defining the player ID for the next player that gets constructed.
    private static List<Player> players = new ArrayList<>();

    /*
    Anders made this. Not reused from del3.
     */
    public Player() {
        this.playerID = nextPlayerID;
        nextPlayerID++;

        this.playerName = String.format("Player %s", this.playerID);
        this.diceCup = new DiceCup();
        this.playerAcct = Bank.createBankAcct(STARTING_BALANCE);
        players.add(this);
    }

    //Reused from del3
    public Player(DiceCup diceCup) {
        this.playerName = String.format("Player %s", nextPlayerID);
        this.playerID = nextPlayerID;
        this.diceCup = diceCup;

        this.playerAcct = Bank.createBankAcct(STARTING_BALANCE);
        nextPlayerID++;
        players.add(this);

        currentField = 0;
        previousField = 0;
    }

    //Reused from del3
    public Player(String playerName) {
        this.playerName = playerName;
        this.playerID = nextPlayerID;
        this.diceCup = new DiceCup();

        this.playerAcct = new BankAcct(STARTING_BALANCE);

        nextPlayerID++;

        players.add(this);
    }

    // Reused from del3
    public Player(String playerName, DiceCup diceCup) {
        this.playerName = playerName;
        this.playerID = nextPlayerID;
        this.diceCup = diceCup;

        this.playerAcct = new BankAcct(STARTING_BALANCE);

        nextPlayerID++;

        players.add(this);
    }

    // Reused from del3
    public BankAcct getPlayerAcct() {
        return playerAcct;
    }

    // Reused from del3
    public int getPlayerID() {
        return playerID;
    }

    // Reused from del3
    public String getPlayerName() {
        return playerName;
    }

    // Reused from del3
    public static List<Player> getPlayers()
    {
        return players;
    }

    // Reused from del3
    public String toString() {
        return this.playerName;
    }

    // Reused from del3
    public DiceCup getDiceCup() {
        return this.diceCup;
    }

    // Reused from del3
    public int getPreviousField() {
        return this.previousField;
    }

    // Reused from del3
    public int getCurrentField() {
        return this.currentField;
    }

    // Reused from del3
    public void moveCurrentField(int diceCount) {
        /*models.GameBoard board = new models.GameBoard();
        if (this.currentField + diceCount > board.getFields().length)
            this.setCurrentField(diceCount - board.getFields().length + this.currentField);
        else
            this.setCurrentField(this.currentField + diceCount);*/
    }

    // Reused from del3
    public void setCurrentField(int fieldID) {
        if (this.currentField == 0)
            this.previousField = this.currentField + 1;
        else
            this.previousField = this.currentField;
        this.currentField = fieldID;
    }

    public int getOwnedHouseCount() {
        return 0;
    }

    public int getOwnedHotelCount() {
        return 0;
    }
}
