package org.yumsh.oop;

public class Demo extends Object {
    private int count;

    public Demo(int a) {
        count = a;
    }

    public int inc() {
        return count++;
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        return "count = " + count;
    }
}
