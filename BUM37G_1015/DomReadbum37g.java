package domneptunkod1015;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.File;

public class DomReadNeptunkod {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("orarendBum37g.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("--------------------------------------");

            NodeList oraLista = doc.getElementsByTagName("ora");

            for (int i = 0; i < oraLista.getLength(); i++) {
                Node oraNode = oraLista.item(i);
                if (oraNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element oraElem = (Element) oraNode;
                    System.out.println("Óra ID: " + oraElem.getAttribute("id"));
                    System.out.println("Típus: " + oraElem.getAttribute("tipus"));
                    System.out.println("Tantárgy: " + oraElem.getElementsByTagName("targy").item(0).getTextContent());

                    Element idopont = (Element) oraElem.getElementsByTagName("idopont").item(0);
                    System.out.println("Nap: " + idopont.getAttribute("nap"));
                    System.out.println("Tól: " + idopont.getAttribute("tol"));
                    System.out.println("Ig: " + idopont.getAttribute("ig"));

                    System.out.println("Helyszín: " + oraElem.getElementsByTagName("helyszin").item(0).getTextContent());
                    System.out.println("Oktató: " + oraElem.getElementsByTagName("oktato").item(0).getTextContent());
                    System.out.println("Szak: " + oraElem.getElementsByTagName("szak").item(0).getTextContent());

                    System.out.println("--------------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
