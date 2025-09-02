package miem.projects.vulnerabilities.MINOR.FB;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.DefaultHandler;
import java.io.File;

public class FB_XXE_SAXPARSER {

    // Потенциально небезопасное использование
    public static void unsafeParse() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new File("input.xml"), new DefaultHandler());
        System.out.println("Unsafe parsing done");
    }

    // Корректная конструкция
    public static void safeParse() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // Отключаем внешние сущности и DTD
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        factory.setXIncludeAware(false);
        factory.setNamespaceAware(true);

        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new File("input.xml"), new DefaultHandler());
        System.out.println("Safe parsing done");
    }

    public static void main(String[] args) throws Exception {
        unsafeParse(); // небезопасный пример
        safeParse();   // безопасный пример
    }
}
