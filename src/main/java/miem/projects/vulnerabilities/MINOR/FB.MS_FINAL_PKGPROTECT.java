package miem.projects.vulnerabilities.MINOR.FB;

public class MS_FINAL_PKGPROTECT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Уязвимое статическое поле может быть изменено из любого места
        System.out.println("Исходное значение API_KEY: " + VulnerableConfiguration.API_KEY);
        
        // Имитация изменения из другого пакета (потенциально вредоносного)
        VulnerableConfiguration.API_KEY = "compromised_key_12345";
        System.out.println("Измененное значение API_KEY: " + VulnerableConfiguration.API_KEY);
        
        // Это может привести к компрометации безопасности приложения
    }

    public static void correctTest() {
        // Доступ к безопасному финальному полю (его нельзя изменить)
        System.out.println("Безопасный API_KEY (final): " + SecureConfiguration.API_KEY);
        
        // Следующая строка вызвала бы ошибку компиляции:
        // SecureConfiguration.API_KEY = "compromised_key_12345"; // Ошибка компиляции
        
        // Доступ к защищенному пакетному полю возможен только внутри пакета
        SecureConfiguration.defaultTimeout = 30;
        System.out.println("Пакетно-защищенное поле: " + SecureConfiguration.defaultTimeout);
    }
}

class VulnerableConfiguration {
    // Уязвимость: публичное статическое поле, не объявленное как final
    public static String API_KEY = "original_secure_key_abcdef";
    public static int defaultTimeout = 60;
}

class SecureConfiguration {
    // Безопасно: final статическое поле нельзя изменить после инициализации
    public static final String API_KEY = "original_secure_key_abcdef";
    
    // Безопасно: package-private поле доступно только в пределах текущего пакета
    static int defaultTimeout = 60;
}
