package org.yumsh.club.chat.http;

import java.util.ArrayDeque;
import java.util.Deque;

public class ChatRoom {
    private static final int LIMIT = 10;

    private final Deque<Message> messages = new ArrayDeque<>(LIMIT);

    public synchronized void addMessage(String message) {
        if (messages.size() == LIMIT) {
            messages.removeFirst();
        }
        messages.addLast(new Message(message));
    }

    public synchronized Message[] getMessages() {
        return messages.toArray(Message[]::new);
    }
}
