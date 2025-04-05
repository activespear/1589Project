package miem.projects.vulnerabilities.MINOR.FB;

public class NM_METHOD_CONSTRUCTOR_CONFUSION {
    public static void main(String[] args) {
        incorrectExample();
        correctExample();
    }

    // Некорректный пример: метод с именем класса и возвращаемым значением
    public static void incorrectExample() {
        Document doc = new Document();
        Document result = doc.Document(); // Выглядит как конструктор, но это метод
        System.out.println("Incorrect method call: " + result.getTitle());
    }

    // Корректный пример: правильное именование методов
    public static void correctExample() {
        Document doc = Document.createDefault();
        Document updated = doc.withTitle("New Title");
        System.out.println("Correct method call: " + updated.getTitle());
    }
}

class Document {
    private String title;
    
    // Конструктор (правильно)
    public Document(String title) {
        this.title = title;
    }
    
    // Некорректный метод: имя совпадает с классом
    public Document Document() { // FB.NM_METHOD_CONSTRUCTOR_CONFUSION
        return new Document("Default");
    }
}