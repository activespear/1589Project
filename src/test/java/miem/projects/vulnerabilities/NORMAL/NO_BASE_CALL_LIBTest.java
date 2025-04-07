package miem.projects.vulnerabilities.NORMAL;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class NO_BASE_CALL_LIBTest {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private PrintWriter writer;

    @Before
    public void setUp() throws IOException {
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        writer = mock(PrintWriter.class);

        // Мокаем метод getMethod() для HttpServletRequest
        when(req.getMethod()).thenReturn("GET");
        // Мокаем PrintWriter, чтобы проверить, что записывается в ответ
        when(resp.getWriter()).thenReturn(writer);
    }

    @Test
    public void testIncorrectTest() throws IOException {
        NO_BASE_CALL_LIB.incorrectTest(req, resp);

        verify(writer).write("...");
    }

    @Test
    public void testCorrectTest() throws IOException, ServletException {
        NO_BASE_CALL_LIB.correctTest(req, resp);

        verify(writer).write("...");
    }
}