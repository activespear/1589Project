package miem.projects.vulnerabilities.NORMAL;

import java.nio.ByteBuffer;

public class INFO_PARSED_BUFFER {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        buffer.putInt(0xCAFEBABE);
        buffer.putShort((short) 1);
        buffer.flip();

        incorrectTest(buffer);
        correctTest(buffer);
    }

    // Потенциально небезопасное
    public static void incorrectTest(ByteBuffer buffer) {
        // Может вызвать BufferUnderflowException, если позиция или лимит некорректны
        buffer.position(0);
        int magicNumber = buffer.getInt();
        int version = buffer.getShort() & 0xFFFF;
        System.out.println("Parsed version: " + version);
    }

    // Корректная конструкция
    public static void correctTest(ByteBuffer buffer) {
        if (buffer.remaining() < 6) { // Проверка, что достаточно данных для чтения
            throw new IllegalArgumentException("Buffer too small");
        }
        buffer.position(0);
        int magicNumber = buffer.getInt();
        int version = buffer.getShort() & 0xFFFF;
        System.out.println("Parsed version safely: " + version);
    }
}
