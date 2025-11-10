package dombum37g1105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomModify1Bum37g {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("orarendBum37g.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element newOraado = doc.createElement("oraado");
            newOraado.setTextContent("Dr. Kovács Béla");
            doc.getDocumentElement().appendChild(newOraado);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source1 = new DOMSource(doc);
            StreamResult consoleResult1 = new StreamResult(System.out);
            transformer.transform(source1, consoleResult1);

            StreamResult fileResult = new StreamResult(new File("orarendModify1Bum37g.xml"));
            transformer.transform(source1, fileResult);

            NodeList orak = doc.getElementsByTagName("ora");
            for (int i = 0; i < orak.getLength(); i++) {
                Element ora = (Element) orak.item(i);
                if (ora.getAttribute("tipus").equals("gyakorlat")) {
                    ora.setAttribute("tipus", "eloadas");
                }
            }

            System.out.println("\n\n--- Minden ora tipus gyakorlatról eloadasra módosítva ---");

            DOMSource source2 = new DOMSource(doc);
            StreamResult consoleResult2 = new StreamResult(System.out);
            transformer.transform(source2, consoleResult2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
