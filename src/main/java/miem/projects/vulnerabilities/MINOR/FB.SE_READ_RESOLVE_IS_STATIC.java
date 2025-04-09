package miem.projects.vulnerabilities.MINOR.FB;

import java.io.*;

public class SE_READ_RESOLVE_IS_STATIC {

    public static void main(String[] args) {
        try {
            // Тестируем некорректную реализацию
            byte[] serialized = serialize(new IncorrectSingleton());
            IncorrectSingleton deserialized = (IncorrectSingleton) deserialize(serialized);
            System.out.println("Incorrect: " + (deserialized == IncorrectSingleton.INSTANCE));
            
            // Тестируем корректную реализацию
            serialized = serialize(new CorrectSingleton());
            CorrectSingleton correctDeserialized = (CorrectSingleton) deserialize(serialized);
            System.out.println("Correct: " + (correctDeserialized == CorrectSingleton.INSTANCE));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Некорректная реализация (static readResolve)
    static class IncorrectSingleton implements Serializable {
        static final IncorrectSingleton INSTANCE = new IncorrectSingleton();
        
        private static Object readResolve() {  // Ошибка: метод static
            return INSTANCE;
        }
    }

    // Корректная реализация
    static class CorrectSingleton implements Serializable {
        static final CorrectSingleton INSTANCE = new CorrectSingleton();
        
        private Object readResolve() {  // Правильно: не-static
            return INSTANCE;
        }
    }

    // Вспомогательные методы сериализации
    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        return baos.toByteArray();
    }

    private static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
}