package miem.projects.vulnerabilities.NORMAL.FB;

import java.util.Arrays;

public class MC_OVERRIDABLE_METHOD_CALL_IN_CLONE {
    public static void main(String[] args) throws CloneNotSupportedException {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws CloneNotSupportedException {
        class Document implements Cloneable {
            private byte[] content = new byte[]{1, 2, 3};

            @Override
            public Document clone() throws CloneNotSupportedException {
                Document clone = (Document) super.clone();
                clone.processContent();
                return clone;
            }

            protected void processContent() {
                if (content != null) {
                    content[0] = 0xFF;
                }
            }
        }

        class SecureDocument extends Document {
            @Override
            protected void processContent() {
                if (content == null) throw new IllegalStateException();
            }
        }

        SecureDocument doc = new SecureDocument();
        doc.clone();
    }

    public static void correctTest() throws CloneNotSupportedException {
        class Document implements Cloneable {
            private byte[] content = new byte[]{1, 2, 3};

            @Override
            public Document clone() throws CloneNotSupportedException {
                Document clone = (Document) super.clone();
                clone.processContentFinal();
                return clone;
            }

            protected final void processContentFinal() {
                if (content != null) {
                    content = Arrays.copyOf(content, content.length);
                }
            }
        }

        Document doc = new Document();
        doc.clone();
    }
}
