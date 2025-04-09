package miem.projects.vulnerabilities.MINOR.FB;

import javax.xml.xpath.*;
import org.xml.sax.InputSource;

public class XPATH_INJECTION {
    public static void main(String[] args) throws Exception {
        incorrectTest("' or '1'='1");
        correctTest("normalUser");
    }

    // Некорректный уязвимый запрос
    public static void incorrectTest(String userInput) throws Exception {
        String xml = "<users><user><login>admin</login><password>qwerty</password></user></users>";
        String query = "//user[login='" + userInput + "' and password='any']"; // Инъекция возможна
        
        XPath xpath = XPathFactory.newInstance().newXPath();
        InputSource source = new InputSource(new StringReader(xml));
        
        String result = xpath.evaluate(query, source);
        System.out.println("Результат уязвимого запроса: " + result);
    }

    // Защищенный запрос с параметризацией
    public static void correctTest(String userInput) throws Exception {
        String xml = "<users><user><login>admin</login><password>qwerty</password></user></users>";
        
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = xpath.compile("//user[login=$login and password=$pass]");
        
        InputSource source = new InputSource(new StringReader(xml));
        expr.setXPathVariable("login", userInput);
        expr.setXPathVariable("pass", "any");
        
        String result = expr.evaluate(source);
        System.out.println("Результат безопасного запроса: " + result);
    }
}