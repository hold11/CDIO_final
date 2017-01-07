/*
           ,                                             |
          /#\         _         _     _    ___   ___     | Project: Matador - CDIO_final
         /###\       | |__   _ | | __| |  /_  | /_  |    | Version: v0.1.0
        /#####\      | '_ \ / \| |/ _  |    | |   | |    |
       /##,-,##\     | | | | O | | ( | |   _| |_ _| |_   | Anders Wiberg Olsen (s165241), Valentin Leon Christensen (s152735),
      /##(   )##\    |_| |_|\_/|_|\__,_|  |_____|_____|  | Iman Chelhi (s165228), Troels Just Christoffersen (s120052),
     /#.--   --.#\                                       | Sebastian Tibor Bakonyvári (s145918)
    /`           ´\                                      |
 */

import CLI.CLIController;
import fields.*;
import lang.Lang;
import models.Player;
import GUI.GUIController;
import models.ReadFields;

public class Main {

    public static void main(String[] args) {
        Lang.setLanguage(args);
        CLIController cli = new CLIController(); // For testing purposes

//        ReadFields.readAndDisplay();
        Field fields[] = ReadFields.readFields();
//        for (Field f : fields)
//            System.out.println(f);

        Player p1 = new Player();
        Player p2 = new Player();

        Field[] fieldsTest =  {
                new Transportation(1, 4000),
                new Transportation(2, 4000),
                new Transportation(3, 4000),
                new Transportation(4, 4000)
        };

//        setup();
    }

    private static void setup() {
        GUIController gui = new GUIController();

        int players = gui.selectPlayerCount();
            for (int i = 1; i <= players; i++)
                getPlayerName();
        gui.createPlayers();

    }

    private static void getPlayerName() {
        Player p = new Player(GUI.GUI.getUserString(" please type your name"));
    }

    private static void playerRoll(Player player) {
        GUI.GUI.getUserButtonPressed(player.getPlayerName() + "! Roll for adventure and glory!", "Roll!");
        player.getDiceCup().roll();
        GUI.GUI.setDice(player.getDiceCup().getResultArr()[0], player.getDiceCup().getResultArr()[1]);
    }
}
