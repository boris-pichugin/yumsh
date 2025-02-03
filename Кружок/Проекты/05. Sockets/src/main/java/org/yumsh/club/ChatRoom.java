package org.yumsh.club;

import java.io.IOException;
import java.util.*;

public class ChatRoom {
    private final Set<ChatClient> clients = new HashSet<>();
    private final List<String> messages = new ArrayList<>();

    public void add(ChatClient client) throws IOException {
        synchronized (clients) {
            clients.add(client);

            for (String message : messages) {
                client.sendMessage(message);
            }
        }
    }

    public void remove(ChatClient client) {
        synchronized (clients) {
            clients.remove(client);
        }
    }

    public void sendToAll(ChatClient fromClient, String message) {
        String fullMessage = fromClient.name + ": " + message;

        synchronized (clients) {
            messages.add(fullMessage);

            Iterator<ChatClient> iterator = clients.iterator();
            while (iterator.hasNext()) {
                ChatClient client = iterator.next();
                if (client != fromClient) {
                    try {
                        client.sendMessage(fullMessage);
                    } catch (IOException e) {
                        iterator.remove();
                    }
                }
            }
        }
    }
}
