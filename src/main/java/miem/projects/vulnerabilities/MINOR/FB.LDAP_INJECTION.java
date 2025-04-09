package miem.projects.vulnerabilities.MINOR.FB;

import javax.naming.directory.*;
import javax.naming.ldap.*;
import javax.naming.*;

public class LDAP_INJECTION {
    public static void main(String[] args) {
        incorrectTest("admin)(objectClass=*))", "password");
        correctTest("admin", "password");
    }

    // Некорректный запрос с уязвимостью к LDAP-инъекции
    public static void incorrectTest(String username, String password) {
        try {
            String filter = "(uid=" + username + ")"; // ОШИБКА: конкатенация без экранирования
            LdapContext ctx = new InitialLdapContext();
            SearchControls ctrl = new SearchControls();
            ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> res = ctx.search("ou=users,dc=example,dc=com", filter, ctrl);

            if (res.hasMore()) {
                System.out.println("User found (VULNERABLE TO LDAP INJECTION)");
            } else {
                System.out.println("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Корректный запрос с экранированием
    public static void correctTest(String username, String password) {
        try {
            String sanitizedUsername = LdapEncoder.filterEncode(username); // Экранирование специальных символов
            String filter = "(uid=" + sanitizedUsername + ")";
            LdapContext ctx = new InitialLdapContext();
            SearchControls ctrl = new SearchControls();
            ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration<SearchResult> res = ctx.search("ou=users,dc=example,dc=com", filter, ctrl);

            if (res.hasMore()) {
                System.out.println("User found (SECURE)");
            } else {
                System.out.println("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}