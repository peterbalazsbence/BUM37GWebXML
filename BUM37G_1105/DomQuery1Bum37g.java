package dombum37g1105;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomQuery1Bum37g {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("orarendBum37g.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList orak = doc.getElementsByTagName("ora");
            List<String> kurzusNevek = new ArrayList<>();

            for (int i = 0; i < orak.getLength(); i++) {
                Element ora = (Element) orak.item(i);
                String targy = ora.getElementsByTagName("targy").item(0).getTextContent();
                kurzusNevek.add(targy);
            }

            System.out.println("kurzusnév lista: " + kurzusNevek);
            System.out.println("----");

            Element elsoOra = (Element) orak.item(0);
            System.out.println("Első óra példány adatai:");
            System.out.println("Tárgy: " + elsoOra.getElementsByTagName("targy").item(0).getTextContent());
            System.out.println("Nap: " + ((Element) elsoOra.getElementsByTagName("idopont").item(0)).getAttribute("nap"));
            System.out.println("Idő: " + ((Element) elsoOra.getElementsByTagName("idopont").item(0)).getAttribute("tol")
                    + " - " + ((Element) elsoOra.getElementsByTagName("idopont").item(0)).getAttribute("ig"));
            System.out.println("Helyszín: " + elsoOra.getElementsByTagName("helyszin").item(0).getTextContent());
            System.out.println("Oktató: " + elsoOra.getElementsByTagName("oktato").item(0).getTextContent());
            System.out.println("Szak: " + elsoOra.getElementsByTagName("szak").item(0).getTextContent());

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(elsoOra);
            StreamResult fileResult = new StreamResult(new File("elsoOraBum37g.xml"));
            transformer.transform(source, fileResult);

            System.out.println("---");
            List<String> oktatok = new ArrayList<>();

            for (int i = 0; i < orak.getLength(); i++) {
                Element ora = (Element) orak.item(i);
                String oktato = ora.getElementsByTagName("oktato").item(0).getTextContent();
                if (!oktatok.contains(oktato)) {
                    oktatok.add(oktato);
                }
            }

            System.out.println("Oktatók listája: " + oktatok);
            System.out.println("--");

            System.out.println("összetett lekérdezés - Web technológiák 1. időpontjai:");
            for (int i = 0; i < orak.getLength(); i++) {
                Element ora = (Element) orak.item(i);
                String targy = ora.getElementsByTagName("targy").item(0).getTextContent();
                if (targy.equals("Web technológiák 1.")) {
                    Element idopont = (Element) ora.getElementsByTagName("idopont").item(0);
                    System.out.println("- " + idopont.getAttribute("nap") + ": " +
                            idopont.getAttribute("tol") + "-" + idopont.getAttribute("ig") +
                            " (" + ora.getElementsByTagName("helyszin").item(0).getTextContent() + ")");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
