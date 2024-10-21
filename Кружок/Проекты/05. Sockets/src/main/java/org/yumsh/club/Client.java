package org.yumsh.club;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
//        try (Socket socket = new Socket("192.168.1.163", 5333)) {
        try (Socket socket = new Socket("127.0.0.1", 5333)) {
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);
            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Ваше сообщение: ");
                String msgToSend = scanner.nextLine();
                if (msgToSend.isEmpty()) {
                    System.out.println("Пока!");
                    break;
                }

                writer.write(msgToSend + "\n");
                writer.flush();

                String msg = br.readLine();
                System.out.println("Клиент получил: " + msg);
            }
        }
    }
}
