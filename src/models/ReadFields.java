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

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList nodeFieldList = doc.getElementsByTagName("field");

            // Look through each node in the XML document for field
            for (int temp = 0; temp < nodeFieldList.getLength(); temp++) {
                Node nodeField = nodeFieldList.item(temp);

                // If the node is an element node:
                if (nodeField.getNodeType() == Node.ELEMENT_NODE) {
                    fields.add(createLandPlot((Element) nodeField)); // add the field to the fields list
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fields.toArray(new Field[fields.size()]); // return fields list as an array of Field objects
    }

    private static LandPlot createLandPlot(Element element) {
        int fieldId = getTagValue(element, "id");
        int groupId = getAttrValue(element, "groupId");
        int price = getTagValue(element, "price");
        int housePrice = getTagValue(element, "housePrice");

        // Seperate each rent by ", "
        String rentsString = element.getElementsByTagName("rent").item(0).getTextContent();
        if (rentsString.startsWith("[") && rentsString.endsWith("]"))
            rentsString = rentsString.replaceAll("\\[", "").replaceAll("]", ""); // Remove the [ and ]

        String[] rentStrs = rentsString.split(", ");
        int[] rents = new int[rentStrs.length];

        for (int i = 0; i < 6; i++)
            rents[i] = Integer.parseInt(rentStrs[i]);

        LandPlot field = new LandPlot(fieldId, groupId, price, housePrice, rents);

        return field;
    }

    private static int getTagValue(Element element, String tagName) {
        return Integer.parseInt(element.getElementsByTagName(tagName).item(0).getTextContent());
    }

    private static int getAttrValue(Element element, String attrName) {
        return Integer.parseInt(element.getAttribute(attrName));
    }
}
