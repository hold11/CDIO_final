package models;

import fields.*;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndersWOlsen on 07-01-2017.
 */
public class ReadFields {
    public static Field[] readFields() {
        List<Field> fields = new ArrayList<>();

        try {
            File fieldsXMLFile = new File("src/fields.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fieldsXMLFile);

            // this formats the node correctly
            // i.e.:
            //    <foo>Hel
            //    lo
            //     World</foo>
            // gets represented (denormalized):
            //    Element foo
            //       Text node: Hel
            //       Text node: lo
            //       Text node:  World
            // (normalized):
            //    Element foo
            //       Text node: Hello world
            doc.getDocumentElement().normalize();

            NodeList nodeFieldList = doc.getElementsByTagName("field");

            // Look through each node in the XML document for field
            for (int temp = 0; temp < nodeFieldList.getLength(); temp++) {
                Node nodeField = nodeFieldList.item(temp);
                Element elmField = (Element) nodeField;

                // If the node is an element node:
                if (nodeField.getNodeType() == Node.ELEMENT_NODE) {
                    if (getAttrValueStr(elmField, "type").equals("landPlot"))
                        fields.add(createLandPlot(elmField)); // add the field to the fields list
                    else if (getAttrValueStr(elmField, "type").equals("business"))
                        fields.add(createBusiness(elmField));
                    else if (getAttrValueStr(elmField, "type").equals("chanceField"))
                        fields.add(createChanceField(elmField));
                    else if (getAttrValueStr(elmField, "type").equals("jail"))
                        fields.add(createJail(elmField));
                    else if (getAttrValueStr(elmField, "type").equals("rest"))
                        fields.add(createRest(elmField));
                    else if (getAttrValueStr(elmField, "type").equals("tax"))
                        fields.add(createTax(elmField));
                    else if (getAttrValueStr(elmField, "type").equals("transportation"))
                        fields.add(createTransportation(elmField));
                    else
                        System.out.println("[ReadFields] : Field type does not exist.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fields.toArray(new Field[fields.size()]); // return fields list as an array of Field objects
    }

    private static LandPlot createLandPlot(Element element) {
        // Fetch all the data for the field:
        int fieldId = getTagValue(element, "id");
        int groupId = getAttrValueInt(element, "groupId");
        int price = getTagValue(element, "price");
        int housePrice = getTagValue(element, "housePrice");

        // Get the rent element:
        String rentsString = element.getElementsByTagName("rent").item(0).getTextContent();

        // Remove [ and ] from the string:
        if (rentsString.startsWith("[") && rentsString.endsWith("]")) {
            rentsString = rentsString.replaceAll("\\[", "").replaceAll("]", "");
        }

        // Seperate each rent by ", ":
        String[] rentStrs = rentsString.split(", ");
        int[] rents = new int[rentStrs.length];

        // Put all the int values in the rents variable:
        for (int i = 0; i < 6; i++)
            rents[i] = Integer.parseInt(rentStrs[i]);

        // Return a LandPlot based on the given data:
        return  new LandPlot(fieldId, groupId, price, housePrice, rents);
    }

    private static Business createBusiness(Element element) {
        int fieldId = getTagValue(element, "id");
        int price = getTagValue(element, "price");
        return new Business(fieldId, price);
    }

    private static ChanceField createChanceField(Element element) {
        int fieldId = getTagValue(element, "id");
        return new ChanceField(fieldId);
    }

    private static Jail createJail(Element element) {
        int fieldId = getTagValue(element, "id");
        return new Jail(fieldId);
    }

    private static Rest createRest(Element element) {
        int fieldId = getTagValue(element, "id");
        int passingReward = 0;

        try {
            passingReward = getTagValue(element, "reward");
        } catch (Exception e) { /* Reward doesn't exist = field not equal start field. */}

        return new Rest(fieldId, passingReward);
    }

    private static Tax createTax(Element element) {
        int fieldId = getTagValue(element, "fieldId");
        int amount = getTagValue(element, "amount");
        int percentage = 0;

        try {
            percentage = getTagValue(element, "percentage");
        } catch (Exception e) { /* Percentage doesn't exist on this field */ }

        return new Tax(fieldId, percentage, amount);
    }

    private static Transportation createTransportation(Element element) {
        int fieldId = getTagValue(element, "id");
        int price = getTagValue(element, "price");
        return new Transportation(fieldId, price);
    }

    private static int getTagValue(Element element, String tagName) {
        return Integer.parseInt(element.getElementsByTagName(tagName).item(0).getTextContent());
    }

    private static int getAttrValueInt(Element element, String attrName) {
        return Integer.parseInt(element.getAttribute(attrName));
    }

    private static String getAttrValueStr(Element element, String attrName) {
        return element.getAttribute(attrName);
    }
}
