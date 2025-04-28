package org.yumsh.club.chat.console;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5333, 3)) {
            ChatRoom chatRoom = new ChatRoom();
            int i = 0;
            while (true) {
                Socket socket = server.accept();
                Thread thread = new Thread(
                    () -> handleClientSocket(socket, chatRoom),
                    "MyThread-" + (++i)
                );
                thread.setDaemon(true);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void handleClientSocket(Socket socket, final ChatRoom chatRoom) {
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
            ChatClient currentClient = new ChatClient(clientName, writer);
            chatRoom.add(currentClient);
            while (true) {
                String msg = br.readLine();
                if (msg == null) {
                    chatRoom.remove(currentClient);
                    break;
                }
                System.out.println(clientName + ": " + msg);

                chatRoom.sendToAll(currentClient, msg);
            }
        } catch (final Exception e) {
            e.printStackTrace(System.err);
        }
    }
}