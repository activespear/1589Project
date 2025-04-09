package miem.projects.vulnerabilities.MINOR.FB;

public class FB_DB_DUPLICATE_SWITCH_CLAUSES {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        String animal = "dog";
        // Некорректно: дублирующиеся case-ветки
        switch (animal) {
            case "cat":
                System.out.println("Meow");
                break;
            case "dog":
                System.out.println("Woof");
                break;
            case "dog":  // Дубликат!
                System.out.println("Bark");
                break;
            default:
                System.out.println("Unknown animal");
        }
    }

    public static void correctTest() {
        String animal = "dog";
        // Корректно: уникальные case-ветки
        switch (animal) {
            case "cat":
                System.out.println("Meow");
                break;
            case "dog":
                System.out.println("Woof/Bark"); // Объединенная логика
                break;
            default:
                System.out.println("Unknown animal");
        }
    }
}