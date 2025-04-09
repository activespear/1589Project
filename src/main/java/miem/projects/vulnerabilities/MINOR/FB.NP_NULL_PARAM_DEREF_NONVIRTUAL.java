package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Objects;

public class NP_NULL_PARAM_DEREF_NONVIRTUAL {
    // Некорректные примеры
    private void privateUnsafe(String data) {
        System.out.println(data.toUpperCase()); // NP_NULL_PARAM_DEREF_NONVIRTUAL
    }
    
    public static void staticUnsafe(String data) {
        System.out.println(data.isEmpty()); // NP_NULL_PARAM_DEREF_NONVIRTUAL
    }

    // Корректные примеры
    private void privateSafe(String data) {
        Objects.requireNonNull(data, "Data parameter");
        System.out.println(data.toUpperCase());
    }
    
    public static boolean staticSafe(String data) {
        return data != null && data.isEmpty(); // Null-safe проверка
    }

    public static void main(String[] args) {
        // privateUnsafe(null); // Выбросит NPE
        // staticUnsafe(null);  // Выбросит NPE
        
        try {
            new NP_NULL_PARAM_DEREF_NONVIRTUAL().privateSafe("test");
            System.out.println(staticSafe(null)); // false
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}