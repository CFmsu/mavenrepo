package com.gfg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();

        String html = """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Maven Calculator</title>
                </head>
                <body>
                    <h1>Maven Calculator</h1>
                    <p>Sum of 5 and 3: %d</p>
                    <p>Difference of 5 and 3: %d</p>
                </body>
                </html>
                """.formatted(
                    calculator.add(5, 3),
                    calculator.subtract(5, 3)
                );

        try (ServerSocket server = new ServerSocket(18080)) {
            System.out.println("Calculator server running on port 18080");

            while (true) {
                try (Socket client = server.accept()) {
                    BufferedReader reader = new BufferedReader(
                        new InputStreamReader(client.getInputStream())
                    );

                    String line;
                    while ((line = reader.readLine()) != null && !line.isEmpty()) {
                        // Read the HTTP request headers.
                    }

                    byte[] body = html.getBytes(StandardCharsets.UTF_8);

                    String headers = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html; charset=UTF-8\r\n"
                        + "Content-Length: " + body.length + "\r\n"
                        + "Connection: close\r\n\r\n";

                    OutputStream output = client.getOutputStream();
                    output.write(headers.getBytes(StandardCharsets.UTF_8));
                    output.write(body);
                    output.flush();
                }
            }
        }
    }
}
