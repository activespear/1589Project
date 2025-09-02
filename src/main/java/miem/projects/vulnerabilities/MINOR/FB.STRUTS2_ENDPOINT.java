package miem.projects.vulnerabilities.MINOR.FB;

import com.opensymphony.xwork2.ActionSupport;

// Потенциально небезопасное использование
public class UnsafeUserAction extends ActionSupport {
    private String role;

    public void setRole(String role) {
        this.role = role; // может быть перезаписано через запрос
    }

    @Override
    public String execute() {
        // логика на основе роли
        System.out.println("Role: " + role);
        return SUCCESS;
    }
}

// Корректная конструкция
@AllowedParameters({"username", "email"})
public class SafeUserAction extends ActionSupport {
    private String username;
    private String email;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String execute() {
        // безопасная логика
        System.out.println("Username: " + username + ", Email: " + email);
        return SUCCESS;
    }
}

// Заглушка для аннотации
@interface AllowedParameters {
    String[] value();
}
