package org.yumsh.collections;

public class HashYMap implements YMap {
    private int size = 0;
    private YList[] table = new YList[16];

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(Object key, Object value) {
        if (value == null) {
            remove(key);
            return;
        }

        Entry entry = getEntry(key);
        if (entry != null) {
            entry.value = value;
            return;
        }

        // Ключ key - новый.
        size += 1;
        if ((table.length >> 2) * 3 <= size) {
            extendTable();
        }
        int i = key.hashCode() % table.length;
        if (table[i] == null) {
            table[i] = new ArrayYList();
        }
        table[i].add(new Entry(key, value));
    }

    @Override
    public Object get(Object key) {
        Entry entry = getEntry(key);
        return entry == null ? null : entry.value;
    }

    @Override
    public void remove(Object key) {
        int i = key.hashCode() % table.length;
        YList entries = table[i];
        if (entries == null) {
            return;
        }
        int size = entries.size();
        for (int j = 0; j < size; j++) {
            Entry entry = (Entry) entries.get(j);
            if (entry.key.equals(key)) {
                entries.remove(j);
                this.size -= 1;
                return;
            }
        }
    }

    private Entry getEntry(Object key) {
        int i = key.hashCode() % table.length;
        YList entries = table[i];
        if (entries == null) {
            return null;
        }
        int size = entries.size();
        for (int j = 0; j < size; j++) {
            Entry entry = (Entry) entries.get(j);
            if (entry.key.equals(key)) {
                return entry;
            }
        }
        return null;
    }

    private void extendTable() {
        int newM = table.length * 2;
        YList[] newTable = new YList[newM];
        for (int i = 0; i < table.length; i++) {
            YList entries = table[i];
            if (entries != null) {
                int size = entries.size();
                for (int j = 0; j < size; j++) {
                    Entry entry = (Entry) entries.get(j);
                    int k = entry.key.hashCode() % newM;
                    if (newTable[k] == null) {
                        newTable[k] = new ArrayYList();
                    }
                    newTable[k].add(entry);
                }
            }
        }
        table = newTable;
    }

    private static final class Entry {
        private final Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
