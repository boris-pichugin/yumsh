package org.yumsh.collections;

public class Main {
    public static void main(String[] args) {
        YList list = new DirectArrayYList();

        list.add("Hello!");
        list.add("World!");
        list.add(1);
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(2));
    }
}
