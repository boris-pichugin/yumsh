package org.yumsh.club.chat.console;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class ChatClient {
    public final String name;
    private final OutputStreamWriter writer;

    public ChatClient(String name, OutputStreamWriter writer) {
        this.name = name;
        this.writer = writer;
    }

    public void sendMessage(String msg) throws IOException {
        writer.write(msg + '\n');
        writer.flush();
    }
}
