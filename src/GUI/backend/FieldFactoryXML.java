package GUI.backend;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import GUI.fields.*;
import GUI.fields.Field;
import GUI.fields.Jail;
import GUI.fields.Tax;
import fields.*;
import lang.*;

/**
 * Created by AndersWOlsen on 08-01-2017.
 */
public class FieldFactoryXML {
    public static String path = null;
//    private enum Type {
//        BREWERY, CHANCE, JAIL, REFUGE, SHIPPING, START, STREET, TAX
//    }
//    private enum Type {
//        //                      Refuge = parking, start = gone.
//        BUSINESS, CHANCEFIELD, JAIL, REST, TRANSPORTATION, LANDPLOT, TAX
//    }
    public static ArrayList<Field> fields = null;
    private static fields.Field[] xmlFields = models.ReadFields.readFields();

    private FieldFactoryXML() {

    }

    public static void makeFields() {
        FieldFactoryXML fact = new FieldFactoryXML();
        if (fields == null || fields.size() == 0) {
            try {
                fact.parse();
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
            }
        }
    }

    private boolean parse() {
//        String str = readFile();
//
//        if (str == null) {
//            return false;
//        }

        // 75% > 40 so increase in capacity is required
        fields = new ArrayList<Field>(54);

        for (fields.Field field : xmlFields) {
            if (field instanceof fields.Business)
                createBrewery(((Business) field));
            else if (field instanceof fields.ChanceField)
                createChance();
            else if (field instanceof fields.Jail) // TODO: check if a rest field is the visit jail field
                createJail(((fields.Jail) field), false);
            else if (field instanceof fields.Rest) {
                if (((Rest) field).isJail())
                    createJail(((fields.Rest) field), true);
                else if (!((Rest) field).isJail() && ((Rest) field).getReward() > 0) // if not jail and reward is greater than 0 = start field
                    createStart(((Rest) field));
                else
                    createRefuge(((Rest) field));
            }
            else if (field instanceof fields.Transportation)
                createShipping(((Transportation) field));
            else if (field instanceof fields.LandPlot)
                createStreet(((LandPlot) field));
            else if (field instanceof fields.Tax)
                createTax(((fields.Tax) field));
        }

//        for (String field : str.split("\\|\\|")) {
//            String[] attributes = field.split(";;");
//            // determine type
//            String type = valueOf("type", attributes);
//            // Verify type
//            Type T = validType(type);
//            if (T == null) {
//                return false;
//            }
//            switch(T) {
//                case BUSINESS:
//                    createBrewery(attributes);
//                    break;
//                case CHANCECARD:
//                    createChance(/* attributes */);
//                    break;
//                case JAIL:
//                    createJail(attributes);
//                    break;
//                case REST:
//                    createRefuge(attributes);
//                    break;
//                case TRANSPORTATION:
//                    createShipping(attributes);
//                    break;
////                case START:
////                    createStart(attributes);
////                    break;
//                case LANDPLOT:
//                    createStreet(attributes);
//                    break;
//                case TAX:
//                    createTax(attributes);
//                    break;
//                default:
//                    break;
//            }
//        }
        return true;
    }

//    private String readFile() {
//        BufferedReader in = null;
//        String str = "";
//        File file;
//        try {
//            if (path == null) {
//                InputStream is = getClass().getResourceAsStream("/GUI/resources/fields.txt");
//                in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            } else {
//                file = new File(path);
//                in = new BufferedReader(new FileReader(file));
//            }
//
//            String line;
//            while ((line = in.readLine()) != null) {
//                str += line.trim() + "\n";
//            }
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//            return null;
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        try {
//            in.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return str;
//    }
    private void createBrewery(fields.Business field) {
        String picture = "Default";
        String title = field.toString();
        String subText = Lang.msg("Field" + field.getFieldId() + "_sub");
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
        String leje = Lang.msg("currency") + " " + field.getRent();
//        String picture = valueOf("picture", attributes);
//        String title = valueOf("title", attributes);
//        String subText = valueOf("subText", attributes);
//        String description = valueOf("description", attributes);
//        String leje = valueOf("leje", attributes);
        Field f = new Brewery.Builder()
                .setPicture(picture)
                .setTitle(title)
                .setSubText(subText)
                .setDescription(description)
                .setRent(leje)
                .build();
        fields.add(f);
    }
    private void createChance() {
        Field f = new Chance.Builder().build(); // TODO: Check out this method to make sure it gets the right text
        fields.add(f);
    }
    private void createJail(fields.Field field, boolean visitJailField) {
        String picture = "Default";
        String title = field.toString();
        String subText = Lang.msg("Field" + field.getFieldId() + "_sub");
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
//        String picture = valueOf("picture", attributes);
//        String title = valueOf("title", attributes);
//        String subText = valueOf("subText", attributes);
//        String description = valueOf("description", attributes);
        Field f = new Jail.Builder()
                .setPicture(picture)
                .setTitle(title)
                .setSubText(subText)
                .setDescription(description)
                .build();
        fields.add(f);
    }
    private void createRefuge(fields.Rest field) { // Parking
        String picture = "Default";
        String title = field.toString();
        String subText = Lang.msg("Field" + field.getFieldId() + "_sub");
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
//        String picture = valueOf("picture", attributes);
//        String title = valueOf("title", attributes);
//        String subText = valueOf("subText", attributes);
//        String description = valueOf("description", attributes);
        Field f = new Refuge.Builder()
                .setPicture(picture)
                .setTitle(title)
                .setSubText(subText)
                .setDescription(description)
                .build();
        fields.add(f);
    }
    private void createShipping(fields.Transportation field) {
        String picture = "Default";
        String title = field.toString();
        String subText = Lang.msg("Field" + field.getFieldId() + "_sub");
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
        String leje = Lang.msg("currency") + " " + field.getRent();
//        String picture = valueOf("picture", attributes);
//        String title = valueOf("title", attributes);
//        String subText = valueOf("subText", attributes);
//        String description = valueOf("description", attributes);
//        String leje = valueOf("leje", attributes);
        Field f = new Shipping.Builder()
                .setPicture(picture)
                .setTitle(title)
                .setSubText(subText)
                .setDescription(description)
                .setRent(leje)
                .build();
        fields.add(f);
    }
    private void createStart(fields.Rest field) {
        String title = field.toString();
        Color bgColor = toColor("255,,0,,0");
        Color fgColor = toColor("0,,0,,0");
        String subText = Lang.msg("Field" + field.getFieldId() + "_sub");
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
//        Color bgColor = toColor(valueOf("backgroundColor", attributes));
//        Color fgColor = toColor(valueOf("foregroundColor", attributes));
//        String title = valueOf("title", attributes);
//        String subText = valueOf("subText", attributes);
//        String description = valueOf("description", attributes);
        Field f = new Start.Builder()
                .setBgColor(bgColor)
                .setFgColor(fgColor)
                .setTitle(title)
                .setSubText(subText)
                .setDescription(description)
                .build();
        fields.add(f);
    }
    private void createStreet(fields.LandPlot field) {
        String title = field.toString();
        Color bgColor = getBgColor(field);
        Color fgColor = getFgColor(field);
        String subText = Lang.msg("Field" + field.getFieldId() + "_sub");
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
        String leje = Lang.msg("currency") + " " + field.getRent();
//        String title = valueOf("title", attributes);
//        Color bgColor = toColor(valueOf("backgroundColor", attributes));
//        Color fgColor = toColor(valueOf("foregroundColor", attributes));
//        String subText = valueOf("subText", attributes);
//        String description = valueOf("description", attributes);
//        String leje = valueOf("leje", attributes);
        Field f = new Street.Builder()
                .setTitle(title)
                .setBgColor(bgColor)
                .setFgColor(fgColor)
                .setSubText(subText)
                .setDescription(description)
                .setRent(leje)
                .build();
        fields.add(f);
    }
    private Color getBgColor(fields.LandPlot field) {
        switch  (field.getGroupID()) {
            case 1: return toColor("75,,155,,255");
            case 2: return toColor("255,,135,,120");
            case 3: return toColor("102,,204,,0");
            case 4: return toColor("153,,153,,153");
            case 5: return toColor("255,,0,,0");
            case 6: return toColor("255,,255,,255");
            case 7: return toColor("255,,255,,50");
            case 8: return toColor("150,,60,,150");
            default: return toColor("0,,0,,0");
        }
    }
    private Color getFgColor(fields.LandPlot field) {
        if (field.getGroupID() == 8)
            return toColor("255,,255,,255");
        else
            return toColor("0,,0,,0");
    }
    private void createTax(fields.Tax field) {
        String title = field.toString();
        String subText = Lang.msg("Field" + field.getFieldId() + "_sub");
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
//        String title = valueOf("title", attributes);
//        String subText = valueOf("subText", attributes);
//        String description = valueOf("description", attributes);
        Field f = new Tax.Builder()
                .setTitle(title)
                .setSubText(subText)
                .setDescription(description)
                .build();
        fields.add(f);
    }
//    private Type validType(String type) {
//        for (Type t : Type.values()) {
//            if (t.toString().equalsIgnoreCase(type)) {
//                return t;
//            }
//        }
//        return null;
//    }
//    private String valueOf(String label, String[] attributes) {
//        for (String a : attributes) {
//            if (a.split("::")[0].trim().equalsIgnoreCase(label)) {
//                return a.split("::")[1];
//            }
//        }
//        System.err.println("GUI - Missing attribute: " + label);
//        return null;
//    }
    private Color toColor(String str) {
        int r = Integer.parseInt(str.split(",,")[0]);
        int g = Integer.parseInt(str.split(",,")[1]);
        int b = Integer.parseInt(str.split(",,")[2]);
        return new Color(r, g, b);
    }
}
