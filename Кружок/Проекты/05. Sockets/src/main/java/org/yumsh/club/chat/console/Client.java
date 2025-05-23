package org.yumsh.club.chat.console;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ваше имя: ");
        String name = scanner.nextLine();
        if (name.isBlank()) {
            System.out.print("Ваше имя пусто. Пока!");
            return;
        }
        try (Socket socket = new Socket("127.0.0.1", 5333)) {
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);
            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);

            writer.write(name + '\n');
            writer.flush();

            startReadThread(name, br);
            while (true) {
                System.out.print(name + ": ");
                String msgToSend = scanner.nextLine();
                if (msgToSend.isEmpty()) {
                    System.out.println("Пока!");
                    break;
                }

                writer.write(msgToSend + "\n");
                writer.flush();
            }
        }
    }

    private static void startReadThread(String name, BufferedReader reader) {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    String msg = reader.readLine();
                    if (msg == null) {
                        return;
                    }
                    System.out.print("\r" + msg + "\n" + name + ": ");
                }
            } catch (Exception ignored) {
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
