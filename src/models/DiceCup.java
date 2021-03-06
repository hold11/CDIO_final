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
import java.util.Collection;
import java.util.Iterator;
import java.lang.*;

/**
 * This class is a die cup (a cup holding multiple dice. It basically handles all the dice in the game.
 * @version 1.1.1
 * imported from CDIO_del3 develop branch on 2017-01-06.
 */
public class DiceCup {
    protected final Collection<Die> dice = new ArrayList<>();
    protected final Collection<Integer> results = new ArrayList<>();

    protected static int diceCount;
    protected static int faceCount;

    protected int doublesRolled;
    protected boolean hasRolled;

    public DiceCup() {
        diceCount = 2;
        faceCount = 6;
        this.doublesRolled = 0;
        this.hasRolled = false;
        initDice();
    }

    public DiceCup(int diceCount) {
        if (diceCount < 2)
            throw new IndexOutOfBoundsException("There should be at least 2 dice.");
        DiceCup.diceCount = diceCount;
        faceCount = 6;
        this.doublesRolled = 0;
        this.hasRolled = false;
        initDice();
    }

    public DiceCup(int diceCount, int faceCount) {
        if (diceCount < 2)
            throw new IndexOutOfBoundsException("There should be at least 2 dice.");
        DiceCup.diceCount = diceCount;
        DiceCup.faceCount = faceCount;
        this.doublesRolled = 0;
        this.hasRolled = false;
        initDice();
    }

    public DiceCup(int diceCount, Die die) {
        if (diceCount < 2)
            throw new IndexOutOfBoundsException("There should be at least 2 dice.");
        DiceCup.diceCount = diceCount;
        this.doublesRolled = 0;
        this.hasRolled = false;

        initDice(die);
    }

    public DiceCup(Collection<Die> dice) {
        if (dice.size() < 2)
            throw new IndexOutOfBoundsException("There should be at least 2 dice.");
        diceCount = dice.size();
        this.doublesRolled = 0;
        this.hasRolled = false;
        initDice(dice);
    }

    private void initDice() {
        for (int i = 0; i < diceCount; i++)
            this.dice.add(new Die(faceCount));
    }

    private void initDice(Die die) {
        for (int i = 0; i < diceCount; i++)
            this.dice.add(die);
    }

    private void initDice(Collection<Die> dice) {
        this.dice.addAll(dice);
    }

    public void addDice    (Die die)              { this.dice.add(die);        }
    public void addDice    (Collection<Die> dice) { this.dice.addAll(dice);    }

    public void removeDice (Die die)              { this.dice.remove(die);     }
    public void removeDice (Collection<Die> dice) { this.dice.removeAll(dice); }

    public void roll() {
        this.results.clear();
        this.hasRolled = true;

        for (Die die : dice)
            this.results.add(die.getRolledDieResult());

        if (getResultArr()[0] == getResultArr()[1]) {
            this.doublesRolled++;
        }
    }

    public Collection<Integer> getResults() {
        return results;
    }

    public Collection<Integer> getRolledDieResults() {
        roll();
        return results;
    }

    public int getTotalEyes() {
        int total = 0;

        for (int i: this.getResults())
            total += i;
        return total;
    }

    public void resetDicecup() {
        this.results.clear();
    }

    public int[] getResultArr() {
        int[] results = new int[2];
        int i = 0;
        for (Iterator<Integer> iter = this.getResults().iterator(); iter.hasNext(); i++)
            results[i] = iter.next();
        return results;
    }

    public int getDoublesRolled() {
        return doublesRolled;
    }

    public void setDoublesRolled(int val) {
        this.doublesRolled = val;
    }

    public boolean wasRollDouble() {
        return getResultArr()[0] == getResultArr()[1];
    }

    public boolean getHasRolled() {
        return this.hasRolled;
    }

    public void resetHasRolled() {
        this.hasRolled = false;
    }
}
