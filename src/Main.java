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

public class Main {

    public static void main(String[] args) {
        Lang.setLanguage(args);
        CLIController cli = new CLIController(); // For testing purposes

        // A few testing fields (all LandPlots)
        Field fields[] = new Field[5];
        fields[0] = new LandPlot(1, 1200, 1000, new int[] {50, 250, 750, 2250, 4000, 6000});
        fields[1] = new LandPlot(2, 1200, 1000, new int[] {50, 250, 750, 2250, 4000, 6000});
        fields[2] = new LandPlot(3, 2000, 1000, new int[] {100, 600, 1800, 5400, 8000, 11000});
        fields[3] = new LandPlot(4, 2000, 1000, new int[] {100, 600, 1800, 5400, 8000, 11000});
        fields[4] = new LandPlot(5, 2000, 1000, new int[] {150, 800, 2000, 6000, 9000, 12000});

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
