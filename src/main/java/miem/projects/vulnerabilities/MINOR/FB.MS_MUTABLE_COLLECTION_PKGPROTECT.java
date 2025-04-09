package miem.projects.vulnerabilities.MINOR.FB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FB_MS_MUTABLE_COLLECTION_PKGPROTECT {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        // Некорректно: package-protected изменяемая коллекция
        class SharedData {
            final List<String> data = new ArrayList<>();  // Видна в пакете
            
            void addData(String item) {
                data.add(item);
            }
        }
        
        SharedData shared = new SharedData();
        shared.addData("initial");
        
        // Другой класс в пакете может модифицировать коллекцию
        shared.data.clear();
        System.out.println("Data compromised: " + shared.data);
    }

    public static void correctTest() {
        // Корректно: защита коллекции от модификации
        class SecureSharedData {
            private final List<String> data = new ArrayList<>();
            
            void addData(String item) {
                data.add(item);
            }
            
            // Возвращаем неизменяемое представление
            List<String> getData() {
                return Collections.unmodifiableList(data);
            }
            
            // Или package-private метод для контролируемого доступа
            List<String> getDataForPackage() {
                return new ArrayList<>(data);  // Защитная копия
            }
        }
        
        SecureSharedData secure = new SecureSharedData();
        secure.addData("secure");
        
        // Попытка модификации через getData() вызовет исключение
        try {
            secure.getData().clear();
        } catch (UnsupportedOperationException e) {
            System.out.println("Collection properly protected");
        }
    }
}