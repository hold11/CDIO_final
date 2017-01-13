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

import java.util.ArrayList;
import java.util.List;

public class Jail extends Field
{
    public Jail(int fieldID) {
        super(fieldID);
    }

    public enum buttons {
        FREE_BAIL_CARD,
        PAY_BAIL_OUT
    }

    @Override
    public void landOnField(Player player) {
        player.incrementTurnsInJail();
        player.setPlayerField(11); // sets player on field 11 (the jail)
    }
}
