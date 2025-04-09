package miem.projects.vulnerabilities.MINOR.FB;

public class FB_VR_UNRESOLVABLE_REFERENCE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: неразрешаемая ссылка (закомментировано для компиляции)
        /*
        String message = getMessage();  // Метод не существует
        System.out.println(message);
        */
        
        System.out.println("This would not compile - getMessage() undefined");
    }

    public static void correctTest() {
        // Корректно: использование существующих ссылок
        String message = getExistingMessage();
        System.out.println("Resolved reference: " + message);
        
        // Альтернатива с проверкой
        java.util.Optional<String> optionalMsg = getOptionalMessage();
        optionalMsg.ifPresent(msg -> System.out.println("Optional: " + msg));
    }
    
    private static String getExistingMessage() {
        return "Hello World";
    }
    
    private static java.util.Optional<String> getOptionalMessage() {
        return java.util.Optional.of("Optional message");
    }
}