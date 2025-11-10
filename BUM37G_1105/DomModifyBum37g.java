package dombum37g1105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomModifyBum37g {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("bum37ghallgato.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList hallgatok = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < hallgatok.getLength(); i++) {
                Element hallgato = (Element) hallgatok.item(i);
                if (hallgato.getAttribute("id").equals("01")) {
                    hallgato.getElementsByTagName("keresztnev")
                            .item(0).setTextContent("János");
                    hallgato.getElementsByTagName("vezeteknev")
                            .item(0).setTextContent("Tóth");
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
