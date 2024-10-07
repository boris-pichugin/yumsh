package org.yumsh.club;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("192.168.1.163", 5333)) {

            OutputStream out = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write("Привет, мир!\n");
            writer.flush();

            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(reader);
            String msg = br.readLine();
            System.out.println("Клиент получил: " + msg);
        }
    }
}
