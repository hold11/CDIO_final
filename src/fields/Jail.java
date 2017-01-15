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

public class Jail extends Field
{
    public Jail(int fieldID) {
        super(fieldID);
    }

    @Override
    public void landOnField(Player player) {
        player.incrementTurnsInJail();
        player.setPlayerField(findRestJail().getFieldId()); // sets player on the "in jail" Field
    }

    // This finds the Rest field where the player is jailed.
    private Rest findRestJail() {
        boolean isJail = false;
        Field f = null;
        int index = 0;
        while (!isJail) {
            f = Field.getNextFieldOfType(index, Rest.class);
            if (((Rest) f).isJail())
                isJail = true;
            index = f.getFieldId();
        }
        return ((Rest) f);
    }
}
