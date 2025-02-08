package org.yumsh.oop;

public class Main {
    public static void main(String[] args) {
        Demo demo1 = new Demo(10);

        System.out.println(demo1.inc());
        System.out.println(demo1.inc());
        System.out.println(demo1.inc());
        System.out.println(demo1.getCount());
        System.out.println(demo1.getCount());
        System.out.println(demo1.getCount());
        System.out.println(demo1.getCount());

        System.out.println(demo1.toString());

        Object obj = demo1;

        System.out.println(obj.toString());

    }
}
