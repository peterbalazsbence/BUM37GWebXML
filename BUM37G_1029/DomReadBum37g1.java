package dombum37g1029;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.File;

public class DOMRead1 {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("orarendBum37g.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            NodeList oraList = doc.getElementsByTagName("ora");

            for (int i = 0; i < oraList.getLength(); i++) {
                Node oraNode = oraList.item(i);

                if (oraNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element oraElem = (Element) oraNode;
                    System.out.println("\n--------------------------------");
                    System.out.println("Óra ID: " + oraElem.getAttribute("id"));
                    System.out.println("Típus: " + oraElem.getAttribute("tipus"));
                    System.out.println("Tárgy: " + oraElem.getElementsByTagName("targy").item(0).getTextContent());

                    Element idopontElem = (Element) oraElem.getElementsByTagName("idopont").item(0);
                    System.out.println("Nap: " + idopontElem.getAttribute("nap"));
                    System.out.println("Időpont: " + idopontElem.getAttribute("tol") + " - " + idopontElem.getAttribute("ig"));

                    System.out.println("Helyszín: " + oraElem.getElementsByTagName("helyszin").item(0).getTextContent());
                    System.out.println("Oktató: " + oraElem.getElementsByTagName("oktato").item(0).getTextContent());
                    System.out.println("Szak: " + oraElem.getElementsByTagName("szak").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
