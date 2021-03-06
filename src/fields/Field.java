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
    private final String fieldName;
    private final int fieldId;
    private static final Field[] fields = models.ReadFields.readFields();
    public abstract void landOnField(Player player);

    protected Field(int fieldID) {
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

    public static Field getFieldByID(int ID) {
        for (Field f: fields)
            if (f.getFieldId() == ID)
                return f;
        return null;
    }

    public static Field getFieldByName(String name) {
        for (Field f: fields)
            if (f.toString().equals(name))
                return f;
        return null;
    }

    public static Field getNextFieldOfType(Player player, Class c) {
        for (int i = player.getCurrentField(); i < Field.getFields().length; i++) {
            if (Field.getFields()[i].getClass().equals(c)) {
                return Field.getFields()[i];
            }
        }
        return null;
    }

    public static Field getNextFieldOfType(int index, Class c) {
        for (int i = index; i < Field.getFields().length; i++) {
            if (Field.getFields()[i].getClass().equals(c)) {
                return Field.getFields()[i];
            }
        }
        return null;
    }
}
