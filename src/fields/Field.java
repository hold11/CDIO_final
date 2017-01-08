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
    private int fieldId;
    private static final Field[] fields = models.ReadFields.readFields();
    public abstract void landOnField(Player player);

    public Field(int fieldID) {
        this.fieldId = fieldID;
        this.fieldName = Lang.msg("Field" + this.fieldId);
    }

    public int getFieldId() {
        return fieldId;
    }

    @Override
    public String toString() { return this.fieldName; }

    public static Field[] getFields() {
        return fields;
    }
}
