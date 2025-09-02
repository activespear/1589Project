package miem.projects.vulnerabilities.MINOR.FB;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

public class FB_XXE_XMLREADER {

    // Потенциально небезопасное использование
    public static void unsafeParse() throws Exception {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        reader.setContentHandler(new DefaultHandler());
        reader.parse("input.xml");
        System.out.println("Unsafe XMLReader parsing done");
    }

    // Корректная конструкция
    public static void safeParse() throws Exception {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        // Отключаем внешние сущности
        reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
        reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        reader
