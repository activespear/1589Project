package miem.projects.vulnerabilities.MINOR.FB;

public class NM_CLASS_NAMING_CONVENTION {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Демонстрация класса с некорректным именем
    public static void incorrectTest() {
        // ОШИБКА: имя класса не соответствует PascalCase
        class invalid_class_name {
            public void show() {
                System.out.println("This class has a bad name!");
            }
        }
        new invalid_class_name().show();
    }

    // Демонстрация класса с корректным именем
    public static void correctTest() {
        class ValidClassName {
            public void show() {
                System.out.println("This class follows naming conventions!");
            }
        }
        new ValidClassName().show();
    }
}