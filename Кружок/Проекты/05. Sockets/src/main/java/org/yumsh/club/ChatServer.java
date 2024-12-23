package org.yumsh.club;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5333, 3)) {
            Set<ChatClient> clients = new HashSet<>();
            int i = 0;
            while (true) {
                Socket socket = server.accept();
                Thread thread = new Thread(
                    () -> handleClientSocket(socket, clients),
                    "MyThread-" + (++i)
                );
                thread.setDaemon(true);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void handleClientSocket(Socket socket, final Set<ChatClient> clients) {
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
            synchronized (clients) {
                clients.add(currentClient);
            }
            while (true) {
                String msg = br.readLine();
                if (msg == null) {
                    synchronized (clients) {
                        clients.remove(currentClient);
                    }
                    break;
                }
                System.out.println(clientName + ": " + msg);

                synchronized (clients) {
                    Iterator<ChatClient> iterator = clients.iterator();
                    while (iterator.hasNext()) {
                        ChatClient client = iterator.next();
                        if (client != currentClient) {
                            try {
                                client.sendMessage(clientName + ": " + msg);
                            } catch (IOException e) {
                                iterator.remove();
                            }
                        }
                    }
                }
            }
        } catch (final Exception e) {
            e.printStackTrace(System.err);
        }
    }
}