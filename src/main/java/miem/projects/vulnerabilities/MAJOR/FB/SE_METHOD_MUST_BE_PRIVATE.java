package miem.projects.vulnerabilities.MAJOR.FB;

import java.io.*;

public class SE_METHOD_MUST_BE_PRIVATE {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class A implements Serializable {
            private static final long serialVersionUID = 1L;
            private String field;

            void writeObject(ObjectOutputStream out) throws IOException {
                out.writeObject(field);
            }

            void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
                field = (String) in.readObject();
            }
        }
    }

    public static void correctTest() {
        class A implements Serializable {
            private static final long serialVersionUID = 1L;
            private String field;

            @Serial
            private void writeObject(ObjectOutputStream out) throws IOException {
                out.writeObject(field);
            }

            @Serial
            private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
                field = (String) in.readObject();
            }
        }
    }
}