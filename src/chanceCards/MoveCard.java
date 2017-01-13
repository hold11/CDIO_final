package chanceCards;/*
           ,                                             |
          /#\         _         _     _    ___   ___     | Project: Matador - CDIO_final
         /###\       | |__   _ | | __| |  /_  | /_  |    | Version: v0.1.0
        /#####\      | '_ \ / \| |/ _  |    | |   | |    |
       /##,-,##\     | | | | O | | ( | |   _| |_ _| |_   | Anders Wiberg Olsen (s165241), Valentin Leon Christensen (s152735),
      /##(   )##\    |_| |_|\_/|_|\__,_|  |_____|_____|  | Iman Chelhi (s165228), Troels Just Christoffersen (s120052),
     /#.--   --.#\                                       | Sebastian Tibor Bakonyvári (s145918)
    /`           ´\                                      |
 */

import fields.Field;
import fields.Jail;
import fields.Transportation;
import models.Player;

public class MoveCard extends ChanceCard
{
    Field destination;
    int moveCount;

    public MoveCard(int chanceCardID, Field field) {
        super(chanceCardID);
        this.destination = field;
    }
    public MoveCard(int chanceCardID, int moveCount) {
        super(chanceCardID);
        this.moveCount = moveCount;
    }

    @Override
    public void receiveCard(Player player) {
        if (destination == null) {
            player.moveCurrentField(this.moveCount);
            Field.getFieldByID(player.getCurrentField()).landOnField(player);
        } else if (destination != null) {
            if (destination instanceof Jail){
                player.setCurrentField(destination.getFieldId());
                Field.getFieldByID(player.getCurrentField()).landOnField(player);
            } else if (destination instanceof Transportation) {
                player.setCurrentField(Field.getNextFieldOfType(player, Transportation.class).getFieldId());
                Field.getFieldByID(player.getCurrentField()).landOnField(player);
            } else {
            player.setCurrentField(destination.getFieldId());
            Field.getFieldByID(player.getCurrentField()).landOnField(player);
            }
        }
    }
}
