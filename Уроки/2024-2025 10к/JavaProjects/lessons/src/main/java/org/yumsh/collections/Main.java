package org.yumsh.collections;

public class Main {
    public static void main(String[] args) {
        YList list = new DirectArrayYList();

        list.add("Hello!");
        list.add("World!");
        list.add(1);

        for (int i = 0; i < 1024; i++) {
            list.add(i);
        }
    }
}
