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

import lang.Lang;
import models.Player;

public abstract class Field
{
    private String fieldName;
    protected int fieldID;
    public abstract void landOnField(Player player);

    public Field(int fieldID) {
        this.fieldID = fieldID;
        System.out.println("Field" + this.fieldID);
        this.fieldName = Lang.msg("Field" + this.fieldID);
    }

    @Override
    public String toString() { return this.fieldName; }
}
