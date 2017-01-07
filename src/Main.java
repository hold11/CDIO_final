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

import fields.*;
import models.Player;

public class Main {

    public static void main(String[] args) {
        LandPlot plot = new LandPlot("testPlot", 32000);
        System.out.println(plot.getOwner() + " owns " + plot);
        Player p1 = new Player();
        System.out.println(p1.getPlayerAcct().getBalance());
        plot.purchaseField(p1);
        System.out.println(p1.getPlayerAcct().getBalance());
    }

    public static boolean testBool() {
        return true;
    }
}
