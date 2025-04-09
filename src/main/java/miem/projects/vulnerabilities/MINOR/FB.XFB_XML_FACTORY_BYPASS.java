package miem.projects.vulnerabilities.MINOR.FB;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class FB_XFB_XML_FACTORY_BYPASS {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: уязвимая конфигурация XML парсера
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("input.xml");  // Уязвимость!
            System.out.println("XML parsed (INSECURE)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void correctTest() {
        // Корректно: безопасная конфигурация XML парсера
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("input.xml");
            System.out.println("XML parsed securely");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}