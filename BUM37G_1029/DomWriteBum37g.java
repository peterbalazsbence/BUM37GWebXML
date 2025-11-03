package domhallgato1029;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMWrite {

    public static void main(String[] args) {
        try {
            File inputFile = new File("bum37ghallgato.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("=== Fastruktúra kiírása ===");
            printNode(doc.getDocumentElement(), 0);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult fileResult = new StreamResult(new File("bum37g1hallgato.xml"));
            transformer.transform(source, fileResult);

            System.out.println("\n Sikeresen kiirva fajlba ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNode(Node node, int indent) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            for (int i = 0; i < indent; i++) System.out.print("  ");

            System.out.print("<" + node.getNodeName());
            NamedNodeMap attributes = node.getAttributes();
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
                System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
            }
            System.out.println(">");

            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    printNode(child, indent + 1);
                } else if (child.getNodeType() == Node.TEXT_NODE && !child.getTextContent().trim().isEmpty()) {
                    for (int j = 0; j < indent + 1; j++) System.out.print("  ");
                    System.out.println(child.getTextContent().trim());
                }
            }

            for (int i = 0; i < indent; i++) System.out.print("  ");
            System.out.println("</" + node.getNodeName() + ">");
        }
    }
}
