package miem.projects.vulnerabilities.MINOR.FB;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.core.Response;

// Заглушка для проверки токена
class AuthManager {
    public static boolean isValidToken(String token) {
        return token != null && token.equals("valid-token");
    }
}

// Заглушка для валидации данных
class InputValidator {
    public static boolean isValid(String data) {
        return data != null && !data.trim().isEmpty();
    }
}

@Path("/user")
public class FB_JAXRS_ENDPOINT {

    // Потенциально небезопасный метод
    @POST
    @Path("/update")
    public Response updateUserIncorrect(String data) {
        return Response.ok("Updated").build();
    }

    // Корректный метод с проверкой токена и данных
    @POST
    @Path("/update")
    public Response updateUserCorrect(@HeaderParam("Authorization") String token, String data) {
        if (!AuthManager.isValidToken(token)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if (!InputValidator.isValid(data)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok("Updated").build();
    }

    // Тестовый main для демонстрации вызова методов
    public static void main(String[] args) {
        FB_JAXRS_ENDPOINT service = new FB_JAXRS_ENDPOINT();

        // Некорректный вызов
        System.out.println("Incorrect call:");
        Response r1 = service.updateUserIncorrect("some data");
        System.out.println("Status: " + r1.getStatus());

        // Корректный вызов с валидным токеном и данными
        System.out.println("\nCorrect call:");
        Response r2 = service.updateUserCorrect("valid-token", "some data");
        System.out.println("Status: " + r2.getStatus());

        // Корректный вызов с неверным токеном
        System.out.println("\nCorrect call with invalid token:");
        Response r3 = service.updateUserCorrect("bad-token", "some data");
        System.out.println("Status: " + r3.getStatus());

        // Корректный вызов с некорректными данными
        System.out.println("\nCorrect call with invalid data:");
        Response r4 = service.updateUserCorrect("valid-token", "");
        System.out.println("Status: " + r4.getStatus());
    }
}
