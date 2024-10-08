package org.yumsh.club;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5333, 5)) {
            try (Socket socket = server.accept()) {
                InputStream in = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(reader);
                String msg = br.readLine();
                System.out.println("Сервер получил: " + msg);

                OutputStream out = socket.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(out);
                writer.write(msg + '\n');
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}