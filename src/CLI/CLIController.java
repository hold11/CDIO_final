package CLI;

/**
 * Created by AndersWOlsen on 07-01-2017.
 */

import models.*;
import fields.*;

/**
 * This class is for testing purposes only.
 * It is created to simplify some functions when displaying data.
 */
public class CLIController {
    public void displayBalance(Player player) {
        System.out.println(player + "'s blanace is " + player.getPlayerAcct().getBalance());
    }

    public void displayOwnerOfField(Field field) {
        if (field instanceof Ownable)
            System.out.println(field + " is currently owned by " + ((Ownable) field).getOwner());
        else
            System.out.println("This field type doesn't have an owner.");
    }
}
