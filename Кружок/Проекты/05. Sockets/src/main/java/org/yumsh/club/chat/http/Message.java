package org.yumsh.club.chat.http;

public class Message {
    public final long sendTime;
    public final String content;

    // 01.01.1970
    public Message(String content) {
        this.sendTime = System.currentTimeMillis();
        this.content = content;
    }
}
