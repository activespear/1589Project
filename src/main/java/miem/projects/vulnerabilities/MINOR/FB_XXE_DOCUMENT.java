package miem.projects.vulnerabilities.MINOR.FB;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.File;

public class FB_XXE_DOCUMENT {

    // Потенциально небезопасное использование
    public static void unsafeParse() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("input.xml"));
        System.out.println("Unsafe Document parsing done");
    }

    // Корректная конструкция
    public static void safeParse() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Отключаем обработку внешних сущностей
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        factory.setExpandEntityReferences(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("input.xml"));
        System.out.println("Safe Document parsing done");
    }

    public static void main(String[] args) throws Exception {
        unsafeParse();
        safeParse();
    }
}
