package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class SE_PRIVATE_READ_RESOLVE_NOT_INHERITED {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("incorrect.ser"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("incorrect.ser"));

            ChildIncorrect child = new ChildIncorrect();
            oos.writeObject(child);
            oos.close();

            Object deserialized = ois.readObject();
            ois.close();

            System.out.println("Объект десериализован: " + deserialized.getClass().getName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void correctTest() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("correct.ser"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("correct.ser"));

            ChildCorrect child = new ChildCorrect();
            oos.writeObject(child);
            oos.close();

            Object deserialized = ois.readObject();
            ois.close();

            System.out.println("Объект десериализован: " + deserialized.getClass().getName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // private readResolve() не наследуется
    static class ParentIncorrect implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Serial
        private Object readResolve() throws ObjectStreamException {
            return this;
        }
    }

    static class ChildIncorrect extends ParentIncorrect {
        @Serial
        private static final long serialVersionUID = 1L;
    }

    // readResolve() объявлен protected
    static class ParentCorrect implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Serial
        protected Object readResolve() throws ObjectStreamException {
            return this;
        }
    }

    static class ChildCorrect extends ParentCorrect {
        @Serial
        private static final long serialVersionUID = 1L;
    }
}