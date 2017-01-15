package test_models;/*
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
import java.util.Arrays;
import java.util.List;

public class AutoDiceCup extends models.DiceCup {
    private List<Integer> rolls;

    public AutoDiceCup() {
        this.rolls = new ArrayList<>();
    }

    public AutoDiceCup(int[] rolls1, int[] rolls2) {
        this.rolls = new ArrayList<>();

        for (int i = 0; i < rolls1.length; i++) {
            this.rolls.add(rolls1[i]);
            this.rolls.add(rolls2[i]);
        }
    }

    public void addRoll(int roll) {
        this.rolls.add(roll);
    }

    @Override
    public void roll() {
        this.results.clear();
        this.hasRolled = true;

        if (this.rolls.size() > 0) {
            this.results.add(rolls.get(0));
            this.results.add(rolls.get(1));
            this.rolls.remove(0);
            this.rolls.remove(0);
        }

        if (getResultArr()[0] == getResultArr()[1]) {
            this.doublesRolled++;
        }
    }
}