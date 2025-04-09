package miem.projects.vulnerabilities.MINOR.FB;

public class FB_SIC_INNER_SHOULD_BE_STATIC_NEEDS_THIS {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class OuterClass {
            private String data = "Data";

            void process() {
                // Некорректно: нестатический внутренний класс
                class InnerClass {
                    void print() {
                        System.out.println(OuterClass.this.data); // Явная ссылка на внешний класс
                    }
                }
                new InnerClass().print();
            }
        }
        new OuterClass().process();
    }

    public static void correctTest() {
        class OuterClass {
            private String data = "Data";

            void process() {
                // Корректно: статический вложенный класс с явной передачей данных
                class StaticNested {
                    final String message;

                    StaticNested(String msg) {
                        this.message = msg;
                    }

                    void print() {
                        System.out.println(message);
                    }
                }
                new StaticNested(data).print();
            }
        }
        new OuterClass().process();
    }
}