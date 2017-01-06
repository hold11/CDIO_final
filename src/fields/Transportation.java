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

import models.DiceCup;
import models.Player;

public class Transportation extends Ownable
{
    public Transportation(String fieldName, int price) {
        super(fieldName, price);
    }

    @Override
    public void landOnField(Player player) {

    }

    @Override
    public int getRent() {
        throw new IllegalArgumentException("Use getRent(DiceCup) instead of getRent().");
    }

    public int getRent(DiceCup diceCup) {
        return 0;
    }
}
