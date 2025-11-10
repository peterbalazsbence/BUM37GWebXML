package dombum37g1105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class DomQueryBum37g {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("bum37ghallgato.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("-------------------------------");
            NodeList hallgatok = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < hallgatok.getLength(); i++) {
                Node node = hallgatok.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element hallgato = (Element) node;

                    System.out.println("Aktuális elem: " + hallgato.getNodeName());
                    System.out.println("vezeteknev: " +
                        hallgato.getElementsByTagName("vezeteknev").item(0).getTextContent());
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
