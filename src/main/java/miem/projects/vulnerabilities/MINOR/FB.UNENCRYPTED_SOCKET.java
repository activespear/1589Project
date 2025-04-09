package miem.projects.vulnerabilities.MINOR.FB;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;

public class UNENCRYPTED_SOCKET {
    public static void main(String[] args) {
        try {
            incorrectTest();
            correctTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Небезопасное соединение через обычный сокет
    public static void incorrectTest() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Незащищенная передача данных");
        
        socket.close();
        serverSocket.close();
        System.out.println("Небезопасная передача завершена");
    }

    // Безопасное SSL-соединение
    public static void correctTest() throws IOException {
        SSLServerSocketFactory sslFactory = 
            (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket sslServerSocket = 
            (SSLServerSocket) sslFactory.createServerSocket(8443);
        
        SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
        
        PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
        out.println("Защищенная передача данных");
        
        sslSocket.close();
        sslServerSocket.close();
        System.out.println("Безопасная передача завершена");
    }
}