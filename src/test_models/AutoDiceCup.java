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
    private List<Integer> rolls1;
    private List<Integer> rolls2;

    public AutoDiceCup() {
        this.rolls1 = new ArrayList<>();
        this.rolls2 = new ArrayList<>();
    }

    public AutoDiceCup(int[] rolls1, int[] rolls2) {
        for (int i : rolls1)
            this.rolls1.add(i);
        for (int i : rolls2)
            this.rolls2.add(i);
//        Arrays.stream(rolls1).forEach(roll -> this.rolls1.add(roll));
//        Arrays.stream(rolls2).forEach(roll -> this.rolls2.add(roll));
    }

    public void addRoll(int roll1, int roll2) {
        this.rolls1.add(roll1);
        this.rolls2.add(roll2);
    }

    @Override
    public void roll() {
        this.results.clear();
        this.hasRolled = true;

        if (this.rolls1.size() > 0) {
            this.results.add(rolls1.get(0));
            this.results.add(rolls2.get(0));
            this.rolls1.remove(0);
            this.rolls2.remove(0);
        }

        if (getResultArr()[0] == getResultArr()[1]) {
            this.doublesRolled++;
        }
    }
}