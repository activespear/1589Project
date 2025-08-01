package miem.projects.vulnerabilities.CRITICAL;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class BUFFER_OVERFLOW_DFA {

    // Unsafe buffer copy method with potential overflow
    public static void bufferOverflow(byte[] dest, int n) {
        System.out.println("\n[UNSAFE] Executing bufferOverflow()");
        int[] buf = new int[10];
        ByteBuffer src = ByteBuffer.allocate(buf.length * 4);
        for (int value : buf) {
            src.putInt(value);
        }

        // Potential buffer overflow - trying to copy more than available
        byte[] srcSlice = Arrays.copyOfRange(src.array(), 3 * 4, 10 * 4 + 1);
        System.arraycopy(srcSlice, 0, dest, 0, srcSlice.length);
    }

    // Safe buffer copy method with bounds checking
    public static void bufferSafeCopy(byte[] dest) {
        System.out.println("\n[SAFE] Executing bufferSafeCopy()");
        int[] buf = new int[10];
        ByteBuffer src = ByteBuffer.allocate(buf.length * 4);
        for (int value : buf) {
            src.putInt(value);
        }

        // Safe copy with proper bounds checking
        byte[] srcSlice = Arrays.copyOfRange(src.array(), 3 * 4, 3 * 4 + 28);
        int lengthToCopy = Math.min(srcSlice.length, dest.length);
        System.arraycopy(srcSlice, 0, dest, 0, lengthToCopy);
    }

    public static void main(String[] args) {
        System.out.println("BUFFER_OVERFLOW.DFA DEMONSTRATION");

        // Test with properly sized buffer (28 bytes)
        byte[] properBuffer = new byte[28];

        // Test with undersized buffer (20 bytes)
        byte[] smallBuffer = new byte[20];

        // 1. Demonstrate safe operation with proper buffer
        System.out.println("\nTEST 1: Safe copy with properly sized buffer");
        bufferSafeCopy(properBuffer);
        System.out.println("Completed successfully");

        // 2. Demonstrate safe operation with undersized buffer
        System.out.println("\nTEST 2: Safe copy with undersized buffer");
        bufferSafeCopy(smallBuffer);
        System.out.println("Completed safely (truncated copy)");

        // 3. Demonstrate unsafe operation with proper buffer
        System.out.println("\nTEST 3: Unsafe copy with properly sized buffer");
        try {
            bufferOverflow(properBuffer, properBuffer.length);
            System.out.println("Completed (no overflow occurred)");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getClass().getSimpleName());
        }

        // 4. Demonstrate unsafe operation with undersized buffer
        System.out.println("\nTEST 4: Unsafe copy with undersized buffer");
        try {
            bufferOverflow(smallBuffer, smallBuffer.length);
            System.out.println("Completed (unexpected - overflow should occur)");
        } catch (Exception e) {
            System.out.println("CAUGHT EXCEPTION: " + e.getClass().getSimpleName());
            System.out.println("This is expected behavior for buffer overflow");
        }
    }
}
