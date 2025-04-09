package miem.projects.vulnerabilities.MINOR.FB;  

public class FB_MS_PKGPROTECT {  
    public static void main(String[] args) {  
        incorrectTest();  
        correctTest();  
    }  

    // Уязвимый класс с публичным доступом, но предназначенный только для внутреннего использования  
    public static class InternalUtilityIncorrect {  
        public static void performSensitiveAction() {  
            System.out.println("Critical operation executed!");  
        }  
    }  

    // Исправленный класс с ограниченной видимостью  
    static class InternalUtilityCorrect {  
        static void performSensitiveAction() {  
            System.out.println("Critical operation executed safely!");  
        }  
    }  

    public static void incorrectTest() {  
        // Вредоносный код из другого пакета может вызвать этот метод  
        InternalUtilityIncorrect.performSensitiveAction();  
    }  

    public static void correctTest() {  
        // Метод доступен только внутри пакета  
        InternalUtilityCorrect.performSensitiveAction();  

        // Попытка вызова из другого пакета приведёт к ошибке компиляции  
        // (имитация - в реальности код просто не скомпилируется в другом пакете)  
        // ExternalMaliciousCode.tryToAccess(); // Ошибка: performSensitiveAction() not visible  
    }  
}  