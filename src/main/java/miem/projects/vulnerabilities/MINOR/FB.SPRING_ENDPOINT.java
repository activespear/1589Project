package miem.projects.vulnerabilities.MINOR.FB;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

// Потенциально небезопасное использование
@RestController
@RequestMapping("/admin")
class UnsafeAdminController {

    private final UserService userService = new UserService();

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam String username) {
        // без проверки авторизации
        userService.delete(username);
        return "Deleted (unsafe)";
    }
}

// Корректная конструкция
@RestController
@RequestMapping("/admin")
class SafeAdminController {

    private final UserService userService = new UserService();

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam String username) {
        userService.delete(username);
        return "Deleted (safe)";
    }
}

// Простейший заглушечный сервис для примера
class UserService {
    public void delete(String username) {
        System.out.println("User deleted: " + username);
    }
}
