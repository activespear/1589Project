package miem.projects.vulnerabilities.MAJOR.FB;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class DP_CREATE_CLASSLOADER_INSIDE_DO_PRIVILEGED {
    public static void main(String[] args) {
        DP_CREATE_CLASSLOADER_INSIDE_DO_PRIVILEGED object = new DP_CREATE_CLASSLOADER_INSIDE_DO_PRIVILEGED();
        object.incorrectTest();
        object.correctTest();
    }

    public  void incorrectTest() {
        // без использования doPrivileged
        ClassLoader classLoader = this.getClass().getClassLoader();
        // ...
    }

    public void correctTest() {
        // внутри блока doPrivileged
        ClassLoader classLoader = AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
            @Override
            public ClassLoader run() {
                return this.getClass().getClassLoader();
            }
        });
        // ...
    }
}
