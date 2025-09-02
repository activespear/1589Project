package miem.projects.vulnerabilities.NORMAL.FB;

import java.nio.ByteBuffer;

public class MS_EXPOSE_BUF {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class BufferHolder {
            private static byte[] data = new byte[100];

            public static ByteBuffer getBuffer() {
                return ByteBuffer.wrap(data); // Потенциально небезопасно
            }
        }

        ByteBuffer buffer = BufferHolder.getBuffer();
        System.out.println("Buffer capacity: " + buffer.capacity());
    }

    public static void correctTest() {
        class BufferHolder {
            private static byte[] data = new byte[100];

            public static ByteBuffer getBufferSafe() {
                return ByteBuffer.wrap(data).asReadOnlyBuffer(); // Безопасно
            }
        }

        ByteBuffer buffer = BufferHolder.getBufferSafe();
        System.out.println("Read-only buffer capacity: " + buffer.capacity());
    }
}