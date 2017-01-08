package fields;/*
           ,                                             |
          /#\         _         _     _    ___   ___     | Project: Matador - CDIO_final
         /###\       | |__   _ | | __| |  /_  | /_  |    | Version: v0.1.0
        /#####\      | '_ \ / \| |/ _  |    | |   | |    |
       /##,-,##\     | | | | O | | ( | |   _| |_ _| |_   | Anders Wiberg Olsen (s165241), Valentin Leon Christensen (s152735),
      /##(   )##\    |_| |_|\_/|_|\__,_|  |_____|_____|  | Iman Chelhi (s165228), Troels Just Christoffersen (s120052),
     /#.--   --.#\                                       | Sebastian Tibor Bakonyvári (s145918)
    /`           ´\                                      |
 */

import models.Player;

public class Rest extends Field
{
    private int reward;
    private boolean isJail; // This is so that the GUI are able to differentiate between the jail and visit jail (our backend differs from the GUI backend)

    public Rest(int fieldID) {
        super(fieldID);
        this.reward = 0;
    }

    public Rest(int fieldID, int passingReward, boolean isJail) {
        super(fieldID);
        this.reward = passingReward;
        this.isJail = isJail;
    }

    @Override
    public void landOnField(Player player) {
        // Nothing at all happens. Sad right?
    }

    public void passedField(Player player) {
        player.getPlayerAcct().deposit(this.reward);
    }

    public boolean isJail() {
        return isJail;
    }

    public int getReward() {
        return reward;
    }
}
