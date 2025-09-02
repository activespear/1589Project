package miem.projects.vulnerabilities.MINOR.FB;

class MyResource {
    public void close() {
        System.out.println("Resource closed");
    }
}

public class FB_NP_CLOSING_NULL {

    static class Unsafe {
        public void closeResource() {
            MyResource resource = null;
            resource.close();
        }
    }

    static class Safe {
        public void closeResource() {
            MyResource resource = new MyResource();
            if (resource != null) {
                resource.close();
            } else {
                System.out.println("Ресурс не инициализирован");
            }
        }
    }

    public static void main(String[] args) {
        Safe safe = new Safe();
        safe.closeResource();
    }
}
