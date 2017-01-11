import CLI.CLIController;
import lang.Lang;
import models.*;
import fields.*;

/**
 * Created by awo on 11/01/17.
 */
public class MainTesting {
    public static void main(String[] args) {
        GameController game = new GameController();
        CLIController cli = new CLIController();
        Lang.setLanguage(new String[] {"da", "DK"});

        Player p1 = new Player();
        Player p2 = new Player();

        LandPlot g11 = ((LandPlot) Field.getFields()[1]);
        LandPlot g12 = ((LandPlot) Field.getFields()[3]);

        LandPlot g21 = ((LandPlot) Field.getFields()[6]);
        LandPlot g22 = ((LandPlot) Field.getFields()[8]);
        LandPlot g23 = ((LandPlot) Field.getFields()[9]);

        g11.purchaseField(p1);
        cli.displayOwnedFields(p1);
        cli.displayCanPurchaseHouse(g11);


        g12.purchaseField(p1);
        cli.displayOwnedFields(p1);
        cli.displayCanPurchaseHouse(g11);

    }
}
