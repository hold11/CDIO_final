package lang;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by AndersWOlsen on 06-01-2017.
 */
public class Lang {
    private static String lang;
    private static String country;
    private static Locale locale;

    public static ResourceBundle resourceBundle;

    public static String msg(String message) { return resourceBundle.getString(message); }

    public static void setLanguage(String[] args) {
        if (args.length != 2) {
            lang = "da";
            country = "DK";
        } else if (args.length == 2){
            lang = args[0];
            country = args[1];
        } else {
            System.out.println("Usage: java -jar game.jar <language> <country>");
        }

        try
        {
            locale = new Locale(lang, country);
            resourceBundle = ResourceBundle.getBundle("lang", locale);
        }
        catch (Exception ex)
        {
            System.out.println("You either specified some wrong arguments or you specified a language that doesn't exist in this game.");
            System.out.println("Usage: java -jar game.jar <language> <country>");
        }

    }
}
