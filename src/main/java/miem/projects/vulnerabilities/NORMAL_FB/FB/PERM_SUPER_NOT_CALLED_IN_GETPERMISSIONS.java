package miem.projects.vulnerabilities.NORMAL.FB;

import java.security.*;
import java.net.*;

public class PERM_SUPER_NOT_CALLED_IN_GETPERMISSIONS {
    public static void main(String[] args) throws Exception {
        incorrectTest();
        correctTest();
    }

    public static void incorrectTest() throws Exception {
        class CustomClassLoader extends SecureClassLoader {
            @Override
            protected PermissionCollection getPermissions(CodeSource codesource) {
                Permissions perms = new Permissions();
                perms.add(new AllPermission());
                return perms;
            }
        }

        CodeSource cs = new CodeSource(new URL("file:/tmp/test"), (Certificate[]) null);
        CustomClassLoader loader = new CustomClassLoader();
        PermissionCollection pc = loader.getPermissions(cs);
        System.out.println("Incorrect permissions: " + pc);
    }

    public static void correctTest() throws Exception {
        class SafeClassLoader extends SecureClassLoader {
            @Override
            protected PermissionCollection getPermissions(CodeSource codesource) {
                PermissionCollection perms = super.getPermissions(codesource);
                if (isTrusted(codesource)) {
                    perms.add(new FilePermission("/tmp/-", "read"));
                }
                return perms;
            }

            private boolean isTrusted(CodeSource cs) {
                return cs.getLocation().toString().startsWith("file:/tmp");
            }
        }

        CodeSource cs = new CodeSource(new URL("file:/tmp/test"), (Certificate[]) null);
        SafeClassLoader loader = new SafeClassLoader();
        PermissionCollection pc = loader.getPermissions(cs);
        System.out.println("Correct permissions: " + pc);
    }
}
