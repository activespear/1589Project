package miem.projects.vulnerabilities.NORMAL.FB;

import java.nio.ByteBuffer;

public class EI_EXPOSE_BUF2 {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class DataHolder {
            private byte[] internalData = new byte[1024];

            public ByteBuffer getBuffer() {
                return ByteBuffer.wrap(internalData); // Потенциально небезопасно
            }
        }

        DataHolder holder = new DataHolder();
        ByteBuffer buffer = holder.getBuffer();
        System.out.println("Buffer capacity: " + buffer.capacity());
    }

    public static void correctTest() {
        class DataHolder {
            private byte[] internalData = new byte[1024];

            public ByteBuffer getBufferSafe() {
                return ByteBuffer.wrap(internalData).asReadOnlyBuffer(); // Безопасно
            }
        }

        DataHolder holder = new DataHolder();
        ByteBuffer buffer = holder.getBufferSafe();
        System.out.println("Read-only buffer capacity: " + buffer.capacity());
    }
}