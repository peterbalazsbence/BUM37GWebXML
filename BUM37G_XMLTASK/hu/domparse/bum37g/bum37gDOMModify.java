package hu.domparse.bum37g;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class bum37gDOMModify {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("Bum37g_xml.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // 1. uj konyv hozzaadasa
            addNewBook(doc);

            // 2. egy olvaso email cimenek modositasa
            updateOlvasoEmail(doc, "100", "uj.anna@example.com");

            // 3. kolcsonzes hataridenek modositas
            updateKolcsonzesHatarido(doc, "9000", "2024-03-01");

            // 4. egy kolcsonzes torlese
            removeKolcsonzes(doc, "9001");

            // Az eredmeny kiirasa konzolra
            System.out.println("Modositott dokumentum:");
            printDocument(doc);

            // Az eredmeny mentese uj fajlba
            saveDocument(doc, "bum37g_xml_modified.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1. Uj konyv beszurasa
    private static void addNewBook(Document doc) {
        System.out.println("\n[MOD] Uj konyv hozzaadasa...");

        Element root = doc.getDocumentElement();
        Element konyvekElem = (Element) root.getElementsByTagName("Konyvek").item(0);

        // Uj Konyv elem letrehozasa
        Element ujKonyv = doc.createElement("Konyv");

        Element id = doc.createElement("KonyvID");
        id.setTextContent("3");
        ujKonyv.appendChild(id);

        Element cim = doc.createElement("Cim");
        cim.setTextContent("XML programozas");
        ujKonyv.appendChild(cim);

        Element szerzo = doc.createElement("Szerzo");
        szerzo.setTextContent("Bum Developer");
        ujKonyv.appendChild(szerzo);

        Element ev = doc.createElement("KiadasiEv");
        ev.setTextContent("2024");
        ujKonyv.appendChild(ev);

        Element kat = doc.createElement("Kategoria");
        Element nev = doc.createElement("Nev");
        nev.setTextContent("Szakirodalom");
        Element leiras = doc.createElement("Leiras");
        leiras.setTextContent("XML es DOM");
        Element prior = doc.createElement("Prioritas");
        prior.setTextContent("3");
        kat.appendChild(nev);
        kat.appendChild(leiras);
        kat.appendChild(prior);

        ujKonyv.appendChild(kat);

        // hozzaadas a Konyvek listahoz
        konyvekElem.appendChild(ujKonyv);
    }

    // 2. Olvaso email modositasa
    private static void updateOlvasoEmail(Document doc, String olvasoId, String newEmail) {
        System.out.println("\n[MOD] Olvaso (" + olvasoId + ") email modositasa...");

        NodeList list = doc.getElementsByTagName("Olvaso");
        for (int i = 0; i < list.getLength(); i++) {
            Element o = (Element) list.item(i);
            String id = getText(o, "OlvasoID");
            if (olvasoId.equals(id)) {
                Element emailElem = (Element) o.getElementsByTagName("Email").item(0);
                emailElem.setTextContent(newEmail);
                break;
            }
        }
    }

    // 3. Kolcsonzes hataridenek modositas
    private static void updateKolcsonzesHatarido(Document doc, String kolcsonzesId, String newHatarido) {
        System.out.println("\n[MOD] Kolcsonzes (" + kolcsonzesId + ") hatarido modositasa...");

        NodeList list = doc.getElementsByTagName("Kolcsonzes");
        for (int i = 0; i < list.getLength(); i++) {
            Element k = (Element) list.item(i);
            String id = getText(k, "KolcsonzesID");
            if (kolcsonzesId.equals(id)) {
                Element hataridoElem = (Element) k.getElementsByTagName("Hatarido").item(0);
                hataridoElem.setTextContent(newHatarido);
                break;
            }
        }
    }

    // 4. Kolcsonzes torlese
    private static void removeKolcsonzes(Document doc, String kolcsonzesId) {
        System.out.println("\n[MOD] Kolcsonzes (" + kolcsonzesId + ") torlese...");

        NodeList list = doc.getElementsByTagName("Kolcsonzes");
        for (int i = 0; i < list.getLength(); i++) {
            Element k = (Element) list.item(i);
            String id = getText(k, "KolcsonzesID");
            if (kolcsonzesId.equals(id)) {
                Node parent = k.getParentNode();
                parent.removeChild(k);
                break;
            }
        }
    }

    //kiiras es mentes

    private static void saveDocument(Document doc, String fileName) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        // kis formazas a jobb olvashatosagert
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);

        System.out.println("\nA modositott dokumentum elmentve ide: " + fileName);
    }

    // Dokumentum kiirasa a konzolra
    private static void printDocument(Document doc) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
    }

    // Segedfuggveny
    private static String getText(Element parent, String tagName) {
        NodeList list = parent.getElementsByTagName(tagName);
        if (list.getLength() == 0) return "";
        return list.item(0).getTextContent().trim();
    }
}
