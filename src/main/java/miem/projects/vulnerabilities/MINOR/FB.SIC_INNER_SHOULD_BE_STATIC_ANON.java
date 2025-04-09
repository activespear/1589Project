package miem.projects.vulnerabilities.MINOR.FB;

public class FB_SIC_INNER_SHOULD_BE_STATIC_ANON {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class OuterClass {
            private String data = "SensitiveData";

            void createAnonymous() {
                // Некорректно: нестатический анонимный класс
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(data); // Неявная ссылка на внешний класс
                    }
                };
                r.run();
            }
        }
        new OuterClass().createAnonymous();
    }

    public static void correctTest() {
        class OuterClass {
            private String data = "SensitiveData";

            void createAnonymous() {
                // Корректно: использование лямбды (неявно static)
                Runnable r = () -> System.out.println(data);
                r.run();
                
                // Или вынос в статический вложенный класс
                class StaticNested implements Runnable {
                    final String message;
                    
                    StaticNested(String msg) {
                        this.message = msg;
                    }
                    
                    @Override
                    public void run() {
                        System.out.println(message);
                    }
                }
                new StaticNested(data).run();
            }
        }
        new OuterClass().createAnonymous();
    }
}