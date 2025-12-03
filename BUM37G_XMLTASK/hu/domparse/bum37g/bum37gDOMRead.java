package hu.domparse.bum37g;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class bum37gDOMRead {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("Bum37g_xml.xml");

            // DOM dokumentum betoltese
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Gyokerelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("============================================");

            // Konyvek kiirasa
            listKonyvek(doc);

            // Olvasok kiirasa
            listOlvasok(doc);

            // Konyvtarosok kiirasa
            listKonyvtarosok(doc);

            // Kolcsonzesek kiirasa
            listKolcsonzesek(doc);

            // Olvasokartyak kiirasa
            listOlvasokartyak(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Segdfuggveny

    private static void listKonyvek(Document doc) {
        System.out.println("\n--- Konyvek ---");

        NodeList konyvList = doc.getElementsByTagName("Konyv");
        for (int i = 0; i < konyvList.getLength(); i++) {
            Node node = konyvList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element konyv = (Element) node;
                System.out.println("Konyv #" + (i + 1));
                System.out.println("  KonyvID   : " + getText(konyv, "KonyvID"));
                System.out.println("  Cim       : " + getText(konyv, "Cim"));
                System.out.println("  Szerzo    : " + getText(konyv, "Szerzo"));
                System.out.println("  KiadasiEv : " + getText(konyv, "KiadasiEv"));

                Element kat = (Element) konyv.getElementsByTagName("Kategoria").item(0);
                if (kat != null) {
                    System.out.println("  Kategoria:");
                    System.out.println("    Nev      : " + getText(kat, "Nev"));
                    System.out.println("    Leiras   : " + getText(kat, "Leiras"));
                    System.out.println("    Prioritas: " + getText(kat, "Prioritas"));
                }
                System.out.println();
            }
        }
    }

    private static void listOlvasok(Document doc) {
        System.out.println("\n--- Olvasok ---");

        NodeList list = doc.getElementsByTagName("Olvaso");
        for (int i = 0; i < list.getLength(); i++) {
            Element o = (Element) list.item(i);
            System.out.println("Olvaso #" + (i + 1));
            System.out.println("  OlvasoID   : " + getText(o, "OlvasoID"));
            System.out.println("  Nev        : " + getText(o, "Nev"));
            System.out.println("  Email      : " + getText(o, "Email"));
            System.out.println("  Telefonszam: " + getText(o, "Telefonszam"));
            System.out.println();
        }
    }

    private static void listKonyvtarosok(Document doc) {
        System.out.println("\n--- Konyvtarosok ---");

        NodeList list = doc.getElementsByTagName("Konyvtaros");
        for (int i = 0; i < list.getLength(); i++) {
            Element k = (Element) list.item(i);
            System.out.println("Konyvtaros #" + (i + 1));
            System.out.println("  DolgozoID  : " + getText(k, "DolgozoID"));
            System.out.println("  Nev        : " + getText(k, "Nev"));
            System.out.println("  Email      : " + getText(k, "Email"));
            System.out.println("  Telefonszam: " + getText(k, "Telefonszam"));
            System.out.println();
        }
    }

    private static void listKolcsonzesek(Document doc) {
        System.out.println("\n--- Kolcsonzesek ---");

        NodeList list = doc.getElementsByTagName("Kolcsonzes");
        for (int i = 0; i < list.getLength(); i++) {
            Element e = (Element) list.item(i);
            System.out.println("Kolcsonzes #" + (i + 1));
            System.out.println("  KolcsonzesID: " + getText(e, "KolcsonzesID"));
            System.out.println("  KonyvID     : " + getText(e, "KonyvID"));
            System.out.println("  OlvasoID    : " + getText(e, "OlvasoID"));
            System.out.println("  Datum       : " + getText(e, "Datum"));
            System.out.println("  Hatarido    : " + getText(e, "Hatarido"));
            System.out.println();
        }
    }

    private static void listOlvasokartyak(Document doc) {
        System.out.println("\n--- Olvasokartyak ---");

        NodeList list = doc.getElementsByTagName("Olvasokartya");
        for (int i = 0; i < list.getLength(); i++) {
            Element e = (Element) list.item(i);
            System.out.println("Olvasokartya #" + (i + 1));
            System.out.println("  OlvasoID: " + getText(e, "OlvasoID"));
            System.out.println("  Ar      : " + getText(e, "Ar"));

            Element erv = (Element) e.getElementsByTagName("Ervenyes").item(0);
            if (erv != null) {
                System.out.println("  Ervenyes:");
                System.out.println("    Datumtol: " + getText(erv, "Datumtol"));
                System.out.println("    Datumig : " + getText(erv, "Datumig"));
            }
            System.out.println();
        }
    }

    // Segedfuggveny: elem gyerek-tagjainak szovege
    private static String getText(Element parent, String tagName) {
        NodeList list = parent.getElementsByTagName(tagName);
        if (list.getLength() == 0) return "";
        return list.item(0).getTextContent().trim();
    }
}
