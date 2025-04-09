package miem.projects.vulnerabilities.MINOR.FB;

import java.util.Hashtable;

public class MS_MUTABLE_HASHTABLE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    // Уязвимый пример
    public static void incorrectTest() {
        Hashtable<MutableKey, String> table = new Hashtable<>();
        MutableKey key = new MutableKey("ID1");
        table.put(key, "Confidential Data");
        
        // Изменение ключа после вставки
        key.setValue("MODIFIED_ID");
        System.out.println("Поиск по измененному ключу: " + table.get(key)); // null
    }

    // Исправленный пример
    public static void correctTest() {
        Hashtable<ImmutableKey, String> table = new Hashtable<>();
        ImmutableKey key = new ImmutableKey("ID1");
        table.put(key, "Secure Data");
        
        // Попытка изменения ключа невозможна (компиляция запрещена)
        System.out.println("Корректный поиск: " + table.get(key)); // Secure Data
    }
}

// Уязвимый изменяемый ключ
class MutableKey {
    private String value;

    public MutableKey(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MutableKey && 
               ((MutableKey) obj).value.equals(this.value);
    }
}

// Безопасный неизменяемый ключ
final class ImmutableKey {
    private final String value;

    public ImmutableKey(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ImmutableKey && 
               ((ImmutableKey) obj).value.equals(this.value);
    }
}
