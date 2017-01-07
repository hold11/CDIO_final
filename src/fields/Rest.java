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

    public Rest(int fieldID) {
        super(fieldID);
        this.reward = 0;
    }

    public Rest(int fieldID, int passingReward) {
        super(fieldID);
        this.reward = passingReward;
    }

    @Override
    public void landOnField(Player player) {
        // Nothing at all happens. Sad right?
    }

    public void passedField(Player player) {
        player.getPlayerAcct().deposit(this.reward);
    }
}
