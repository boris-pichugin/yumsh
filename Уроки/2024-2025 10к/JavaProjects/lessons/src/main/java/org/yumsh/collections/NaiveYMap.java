package org.yumsh.collections;

public class NaiveYMap implements YMap {
    YList keys = new ArrayYList();
    YList values = new ArrayYList();

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public void put(Object key, Object value) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(key)) {
                values.set(i, value);
                return;
            }
        }
        keys.add(key);
        values.add(value);
    }

    @Override
    public Object get(Object key) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(key)) {
                return values.get(i);
            }
        }
        return null;
    }

    @Override
    public void remove(Object key) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(key)) {
                keys.remove(i);
                values.remove(i);
                return;
            }
        }
    }
}
