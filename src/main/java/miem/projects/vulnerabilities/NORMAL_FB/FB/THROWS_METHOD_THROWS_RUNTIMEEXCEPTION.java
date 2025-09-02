package miem.projects.vulnerabilities.NORMAL.FB;

public class THROWS_METHOD_THROWS_RUNTIMEEXCEPTION {
    public static void main(String[] args) {
        try {
            incorrectTest();
        } catch (RuntimeException e) {
            System.out.println("Caught RuntimeException: " + e.getMessage());
        }

        try {
            correctTest();
        } catch (InvalidFileException e) {
            System.out.println("Caught InvalidFileException: " + e.getMessage());
        }
    }

    public static void incorrectTest() {
        class FileParser {
            public void parse(String filePath) {
                if (!isValid(filePath)) {
                    throw new RuntimeException("Invalid file");
                }
            }

            private boolean isValid(String filePath) {
                return filePath != null && filePath.endsWith(".txt");
            }
        }

        new FileParser().parse("invalid.pdf");
    }

    public static void correctTest() throws InvalidFileException {
        class FileParser {
            public void parse(String filePath) throws InvalidFileException {
                if (!isValid(filePath)) {
                    throw new InvalidFileException("Invalid file");
                }
            }

            private boolean isValid(String filePath) {
                return filePath != null && filePath.endsWith(".txt");
            }
        }

        new FileParser().parse("invalid.pdf");
    }
}

class InvalidFileException extends Exception {
    public InvalidFileException(String message) {
        super(message);
    }
}
