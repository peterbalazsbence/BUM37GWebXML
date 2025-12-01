package hu.domparse.bum37g;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class bum37gDOMQuery {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("Bum37g_xml.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // 1. lekerdezes: 2000 utan kiadott konyvek
            queryKonyvek2000Utan(doc);

            // 2. lekerdezes: adott olvaso (ID=100) kolcsonzesei
            queryKolcsonzesekOlvasoSzerint(doc, "100");

            // 3. lekerdezes: ervenyes olvasokartyaval rendelkezo olvasok
            queryErvenyesOlvasokartyak(doc, "2024-06-01");

            // 4. lekerdezes: konyvtarosok email cimei
            queryKonyvtarosEmail(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1. lekerdezes: 2000 utan kiadott konyvek
    private static void queryKonyvek2000Utan(Document doc) {
        System.out.println("\n[1] 2000 utan kiadott konyvek:");

        NodeList konyvList = doc.getElementsByTagName("Konyv");
        for (int i = 0; i < konyvList.getLength(); i++) {
            Element k = (Element) konyvList.item(i);
            String evStr = getText(k, "KiadasiEv");
            if (!evStr.isEmpty()) {
                int ev = Integer.parseInt(evStr);
                if (ev > 2000) {
                    System.out.println("  - " + getText(k, "Cim") + " (" + ev + ")");
                }
            }
        }
    }

    // 2. lekerdezes: megadott OlvasoID osszes kolcsonzese
    private static void queryKolcsonzesekOlvasoSzerint(Document doc, String olvasoId) {
        System.out.println("\n[2] Kolcsonzesek az OlvasoID=" + olvasoId + " szamara:");

        NodeList kolcsonList = doc.getElementsByTagName("Kolcsonzes");
        for (int i = 0; i < kolcsonList.getLength(); i++) {
            Element k = (Element) kolcsonList.item(i);
            if (olvasoId.equals(getText(k, "OlvasoID"))) {
                String konyvId = getText(k, "KonyvID");
                String datum = getText(k, "Datum");
                String hatarido = getText(k, "Hatarido");
                System.out.println("  - KonyvID=" + konyvId +
                        ", datum=" + datum +
                        ", hatarido=" + hatarido);
            }
        }
    }

    // 3. lekerdezes: azon olvasok, akiknek a kartya ervenyes egy adott datumon
    private static void queryErvenyesOlvasokartyak(Document doc, String datum) {
        System.out.println("\n[3] Ervenyes olvasokartyak " + datum + " datumon:");

        NodeList kartyaList = doc.getElementsByTagName("Olvasokartya");
        for (int i = 0; i < kartyaList.getLength(); i++) {
            Element kartya = (Element) kartyaList.item(i);
            Element erv = (Element) kartya.getElementsByTagName("Ervenyes").item(0);
            String tol = getText(erv, "Datumtol");
            String ig = getText(erv, "Datumig");

            if (datum.compareTo(tol) >= 0 && datum.compareTo(ig) <= 0) {
                String olvasoId = getText(kartya, "OlvasoID");
                String nev = findOlvasoNev(doc, olvasoId);
                System.out.println("  - OlvasoID=" + olvasoId + " (" + nev + ")");
            }
        }
    }

    // 4. lekerdezes: konyvtarosok email cimei
    private static void queryKonyvtarosEmail(Document doc) {
        System.out.println("\n[4] Konyvtarosok email cimei:");

        NodeList list = doc.getElementsByTagName("Konyvtaros");
        for (int i = 0; i < list.getLength(); i++) {
            Element e = (Element) list.item(i);
            String nev = getText(e, "Nev");
            String email = getText(e, "Email");
            System.out.println("  - " + nev + " <" + email + ">");
        }
    }

    //Segedfuggvenyek

    private static String getText(Element parent, String tagName) {
        NodeList list = parent.getElementsByTagName(tagName);
        if (list.getLength() == 0) return "";
        return list.item(0).getTextContent().trim();
    }

    // Olvaso neve OlvasoID alapjan
    private static String findOlvasoNev(Document doc, String olvasoId) {
        NodeList list = doc.getElementsByTagName("Olvaso");
        for (int i = 0; i < list.getLength(); i++) {
            Element o = (Element) list.item(i);
            if (olvasoId.equals(getText(o, "OlvasoID"))) {
                return getText(o, "Nev");
            }
        }
        return "ismeretlen";
    }
}
