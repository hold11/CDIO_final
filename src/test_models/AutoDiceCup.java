package test_models;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by awo on 10/01/17.
 */
public class AutoDiceCup extends models.DiceCup {
    private List<Integer> rolls;

    public AutoDiceCup() {
        this.rolls = new ArrayList<>();
    }

    public AutoDiceCup(int[] rolls) {
        this.rolls = new ArrayList<>();

        for (int i : rolls) {
            // TODO: Implement double rolls
            this.rolls.add(i);
        }
    }

    public void addRoll(int roll) {
        this.rolls.add(roll);
    }

    @Override
    public void roll() {
        this.results.clear();

        if (this.rolls.size() > 0) {
            this.results.add(rolls.get(0) - 1);
            this.results.add(1);
            System.out.println("[AutoDiceCup]: rolled " + rolls.get(0));
            this.rolls.remove(0);
        }
    }
}
