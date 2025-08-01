package miem.projects.vulnerabilities.CRITICAL;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class BUFFER_OVERFLOW {

    // Unsafe buffer copy method (potential overflow)
    public static void unsafeCopy(byte[] dest, int n) {
        System.out.println("\nRunning UNSAFE buffer copy:");
        int[] buf = new int[10];
        ByteBuffer src = ByteBuffer.allocate(buf.length * 4);
        for (int value : buf) {
            src.putInt(value);
        }

        // Potential overflow - tries to copy more data than available
        byte[] srcSlice = Arrays.copyOfRange(src.array(), 3 * 4, 10 * 4 + 1);
        System.arraycopy(srcSlice, 0, dest, 0, srcSlice.length);

        System.out.println("Unsafe copy completed (may have overflowed)");
    }

    // Safe buffer copy method
    public static void safeCopy(byte[] dest) {
        System.out.println("\nRunning SAFE buffer copy:");
        int[] buf = new int[10];
        ByteBuffer src = ByteBuffer.allocate(buf.length * 4);
        for (int value : buf) {
            src.putInt(value);
        }

        // Safe range copy with length check
        byte[] srcSlice = Arrays.copyOfRange(src.array(), 3 * 4, 3 * 4 + 28);
        int lengthToCopy = Math.min(srcSlice.length, dest.length);
        System.arraycopy(srcSlice, 0, dest, 0, lengthToCopy);

        System.out.println("Safe copy completed (length checked)");
    }

    public static void main(String[] args) {
        System.out.println("BUFFER_OVERFLOW DEMONSTRATION");

        // Create target buffers
        byte[] smallBuffer = new byte[20];  // Too small for unsafe copy
        byte[] largeBuffer = new byte[40];  // Large enough

        try {
            // Test safe copy with small buffer
            safeCopy(smallBuffer);
            System.out.println("Safe copy to small buffer succeeded");

            // Test safe copy with large buffer
            safeCopy(largeBuffer);
            System.out.println("Safe copy to large buffer succeeded");

            // Test unsafe copy with large buffer (should work)
            unsafeCopy(largeBuffer, largeBuffer.length);
            System.out.println("Unsafe copy to large buffer succeeded");

            // Test unsafe copy with small buffer (should fail)
            unsafeCopy(smallBuffer, smallBuffer.length);
            System.out.println("Unsafe copy to small buffer succeeded (unexpected)");

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getClass().getSimpleName());
            System.out.println("Error message: " + e.getMessage());
        }
    }
}
