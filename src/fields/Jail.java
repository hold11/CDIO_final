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
    private static List<Player> playersInJail = new ArrayList<>();
    public Jail(int fieldID) {
        super(fieldID);
    }

    @Override
    public void landOnField(Player player) {
        player.setInJail(true);
        // TODO: Move player to the jail parking area.
    }

    public static void putPlayerInJail(Player player) {
        playersInJail.add(player);
    }

    public static void removePlayerFromJail(Player player) {
        playersInJail.remove(player);
    }

    public static List<Player> getPlayersInJail() {
        return playersInJail;
    }
}
