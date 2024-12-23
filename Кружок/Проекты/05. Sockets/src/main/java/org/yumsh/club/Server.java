package org.yumsh.club;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5333, 3)) {
            int i = 0;
            while (true) {
                Socket socket = server.accept();
                Thread thread = new Thread(
                    () -> handleClientSocket(socket),
                    "MyThread-" + (++i)
                );
                thread.setDaemon(true);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void handleClientSocket(Socket socket) {
        try (socket) {
            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);

            String clientName = br.readLine();
            if (clientName == null) {
                return;
            }
            sendPingMessages(writer, clientName);
            while (true) {
                String msg = br.readLine();
                if (msg == null) {
                    break;
                }
                System.out.println(clientName + ": " + msg);

                writer.write(clientName + ": " + msg + '\n');
                writer.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void sendPingMessages(OutputStreamWriter writer, String clientName) {
        Thread thread = new Thread(() -> {
            try {
                long sleep = 5_000L;
                long startTime = System.currentTimeMillis();
                while (true) {
                    Thread.sleep(sleep);
                    long duraton = System.currentTimeMillis() - startTime;
                    String msg = "%s: Мы общаемся уже %d ms\n".formatted(clientName, duraton);
                    System.out.print(msg);
                    writer.write(msg);
                    writer.flush();
                }
            } catch (Exception ignored) {
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}