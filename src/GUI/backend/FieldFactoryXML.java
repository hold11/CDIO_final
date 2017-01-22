package GUI.backend;

import java.awt.Color;
import java.util.ArrayList;

import GUI.fields.*;
import GUI.fields.Field;
import GUI.fields.Jail;
import GUI.fields.Tax;
import fields.*;
import lang.*;

public class FieldFactoryXML {
    public static ArrayList<Field> fields = null;
    private static final fields.Field[] xmlFields = models.ReadFields.readFields();

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

        // 75% > 40 so increase in capacity is required
        fields = new ArrayList<>(54);

        for (fields.Field field : xmlFields) {
            if (field instanceof fields.Business)
                createBrewery(((Business) field));
            else if (field instanceof fields.ChanceField)
                createChance();
            else if (field instanceof fields.Jail)
                createJail(((fields.Jail) field));
            else if (field instanceof fields.Rest) {
                if (((Rest) field).isJail())
                    createJail(((fields.Rest) field));
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
        return true;
    }

    private void createBrewery(fields.Business field) {
        String picture = "Default";
        String title = field.toString();
        String subText = Lang.msg("price") + ": " + Lang.msg("currency") + " " + field.getPrice();
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
//        String leje = Lang.msg("currency") + " " + field.getRent();
        Field f = new Brewery.Builder()
                .setPicture(picture)
                .setTitle(title)
                .setSubText(subText)
                .setDescription(description)
//                .setRent(leje)
                .build();
        fields.add(f);
    }

    private void createChance() {
        Field f = new Chance.Builder().build(); // TODO: Check out this method to make sure it gets the right text
        fields.add(f);
    }

    private void createJail(fields.Field field) {
        String picture = "Default";
        String title = field.toString();
        String subText = Lang.msg("Field" + field.getFieldId() + "_sub");
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
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
        String subText = Lang.msg("price") + ": " + Lang.msg("currency") + " " + field.getPrice();
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
        String leje = Lang.msg("currency") + " " + field.getRent();
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
        String subText = Lang.msg("Field" + field.getFieldId() + "_sub") + ": " + field.getReward();
        String description = Lang.msg("Field" + field.getFieldId() + "_desc") + " " + Lang.msg("currency") + " " +
                field.getReward() + Lang.msg("Field" + field.getFieldId() + "_desc2");
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
        String subText = Lang.msg("price") + ": " + Lang.msg("currency") + " " + field.getPrice();
        String description = Lang.msg("Field" + field.getFieldId() + "_desc");
        String leje = Lang.msg("currency") + " " + field.getRent();
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

    private void createTax(fields.Tax field) {
        String title = field.toString();
        String subText = Lang.msg("Field" + field.getFieldId() + "_sub");
        String description;
        if (field.getPercentageInPercent() == 0)
            description = Lang.msg("Field" + field.getFieldId() + "_desc") + ": " + Lang.msg("currency") + " " + field.getAmount();
        else
            description = Lang.msg("Field" + field.getFieldId() + "_desc") + " " + field.getPercentageInPercent() + "% " + Lang.msg("or") + " " +Lang.msg("currency") + " " + field.getAmount();
        Field f = new Tax.Builder()
                .setTitle(title)
                .setSubText(subText)
                .setDescription(description)
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

    private Color toColor(String str) {
        int r = Integer.parseInt(str.split(",,")[0]);
        int g = Integer.parseInt(str.split(",,")[1]);
        int b = Integer.parseInt(str.split(",,")[2]);
        return new Color(r, g, b);
    }
}
