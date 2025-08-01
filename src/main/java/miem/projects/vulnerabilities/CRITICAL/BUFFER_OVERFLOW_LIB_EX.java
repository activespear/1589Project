package miem.projects.vulnerabilities.CRITICAL;

public class BUFFER_OVERFLOW_LIB_EX {

    // **UNSAFE VERSION** - Potential buffer overflow
    public static class UnsafeExample {
        public static void overflow(byte[] msg, int cnt) {
            System.out.println("[UNSAFE] Running overflow()");
            byte[] buf = new byte[10];  // Small fixed-size buffer (vulnerable)
            byte[] otherBuf = new byte[100];

            // Determine copy length (but doesn't check against 'buf' size!)
            int n = (cnt < otherBuf.length - 1) ? cnt : otherBuf.length - 1;

            // **DANGER**: May overflow 'buf' if 'n > buf.length'
            System.arraycopy(msg, 0, buf, 0, n);
            System.out.println("Unsafe copy completed (may have overflowed silently)");
        }
    }

    // **SAFE VERSION** - Prevents overflow
    public static class SafeExample {
        public static void overflow(byte[] msg, int cnt) {
            System.out.println("[SAFE] Running overflow()");
            byte[] buf = new byte[100];  // Adequate buffer size

            // Ensure 'n' does not exceed destination buffer
            int n = (cnt < buf.length - 1) ? cnt : buf.length - 1;

            // Safe copy (guaranteed to fit)
            System.arraycopy(msg, 0, buf, 0, n);
            System.out.println("Safe copy completed (no overflow possible)");
        }
    }

    // **Test Cases**
    public static void main(String[] args) {
        System.out.println("BUFFER_OVERFLOW.LIB.EX DEMONSTRATION\n");

        // Test data (small and large input)
        byte[] smallMsg = "Hello".getBytes();
        byte[] largeMsg = new byte[150];  // Larger than 'buf' in UnsafeExample

        // **1. Test SafeExample (should always work)**
        System.out.println("--- Testing SafeExample ---");
        SafeExample.overflow(smallMsg, smallMsg.length);  // Normal case
        SafeExample.overflow(largeMsg, largeMsg.length);  // Large input (truncated safely)

        // **2. Test UnsafeExample (may crash or corrupt memory)**
        System.out.println("\n--- Testing UnsafeExample ---");
        try {
            UnsafeExample.overflow(smallMsg, smallMsg.length);  // Fits (no issue)
        } catch (Exception e) {
            System.out.println("ERROR (smallMsg): " + e);
        }

        try {
            UnsafeExample.overflow(largeMsg, largeMsg.length);  // **OVERFLOW RISK**
        } catch (Exception e) {
            System.out.println("ERROR (largeMsg): " + e + " (Expected buffer overflow)");
        }
    }
}
