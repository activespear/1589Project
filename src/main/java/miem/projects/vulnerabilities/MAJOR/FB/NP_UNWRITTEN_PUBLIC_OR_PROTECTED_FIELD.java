package miem.projects.vulnerabilities.MAJOR.FB;

public class NP_UNWRITTEN_PUBLIC_OR_PROTECTED_FIELD {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class Example {
            // Поле не инициализируется
            public String value;

            public void printValue() {
                // может быть NullPointerException
                System.out.println(value.length());
            }
        }

        Example example = new Example();
        example.printValue();
    }

    public static void correctTest() {
        class Example {
            // Инициализация поля в конструкторе
            public String value;

            public Example(String value) {
                this.value = value;
            }

            public void printValue() {
                if (value != null) {
                    System.out.println(value.length());
                }
            }
        }

        Example example = new Example("Hello");
        example.printValue();
    }
}