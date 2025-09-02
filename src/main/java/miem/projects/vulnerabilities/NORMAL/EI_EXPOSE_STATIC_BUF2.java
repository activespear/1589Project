package miem.projects.vulnerabilities.NORMAL.FB;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class EI_EXPOSE_STATIC_BUF2 {
    public static void main(String[] args) {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() {
        class GlobalCache {
            private static ByteBuffer sharedBuffer;

            public static void init(byte[] externalData) {
                sharedBuffer = ByteBuffer.wrap(externalData); // Потенциально небезопасно
            }

            public static void initFromBuffer(ByteBuffer externalBuffer) {
                sharedBuffer = externalBuffer.slice(); // Потенциально небезопасно
            }

            public static ByteBuffer getBuffer() {
                return sharedBuffer;
            }
        }

        byte[] data = new byte[10];
        GlobalCache.init(data);
        ByteBuffer buffer = GlobalCache.getBuffer();
        System.out.println("Buffer capacity: " + buffer.capacity());
    }

    public static void correctTest() {
        class GlobalCache {
            private static ByteBuffer sharedBuffer;

            public static void initSafe(byte[] externalData) {
                byte[] internalCopy = Arrays.copyOf(externalData, externalData.length);
                sharedBuffer = ByteBuffer.wrap(internalCopy); // Безопасно
            }

            public static ByteBuffer getBufferSafe() {
                return sharedBuffer.asReadOnlyBuffer();
            }
        }

        byte[] data = new byte[10];
        GlobalCache.initSafe(data);
        ByteBuffer buffer = GlobalCache.getBufferSafe();
        System.out.println("Read-only buffer capacity: " + buffer.capacity());
    }
}
