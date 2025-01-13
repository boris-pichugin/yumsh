package org.yumsh.club;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ChatClients {
    private final Set<ChatClient> clients = new HashSet<>();

    public void add(ChatClient client) {
        synchronized (clients) {
            clients.add(client);
        }
    }

    public void remove(ChatClient client) {
        synchronized (clients) {
            clients.remove(client);
        }
    }

    public void sendToAll(ChatClient fromClient, String message) {
        synchronized (clients) {
            Iterator<ChatClient> iterator = clients.iterator();
            while (iterator.hasNext()) {
                ChatClient client = iterator.next();
                if (client != fromClient) {
                    try {
                        client.sendMessage(fromClient.name + ": " + message);
                    } catch (IOException e) {
                        iterator.remove();
                    }
                }
            }
        }
    }
}
