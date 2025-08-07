package org.yumsh.collections;

public class AvlTreeYMap implements YMap {
    private int size = 0;
    private Node root = null;

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
        root = put(root, key, value);
    }

    private Node put(Node node, Object key, Object value) {
        if (node == null) {
            size += 1;
            return new Node(key, value);
        }
        int cmp = compareTo(node.key, key);
        if (cmp == 0) {
            node.value = value;
            return node;
        }
        if (cmp < 0) {
            // node.key < key
            node.right = put(node.right, key, value);
        } else {
            node.left = put(node.left, key, value);
        }

        return checkBalance(node);
    }

    @Override
    public Object get(Object key) {
        Node node = root;
        while (node != null) {
            int cmp = compareTo(key, node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void remove(Object key) {
        root = remove(root, key);
    }

    private Node remove(Node node, Object key) {
        if (node == null) {
            return null;
        }
        int cmp = compareTo(node.key, key);
        if (cmp == 0) {
            node = removeNode(node);
            size -= 1;
        } else if (cmp < 0) {
            // node.key < key
            node.right = remove(node.right, key);
        } else {
            node.left = remove(node.left, key);
        }
        return checkBalance(node);
    }

    private Node removeNode(Node node) {
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }
        // TODO Проверить какое поддерево длиннея и брать донора из него
        node.left = removeNodeLeft(node.left, node);
        return node;
    }

    private Node removeNodeLeft(Node current, Node node) {
        if (current.right == null) {
            node.key = current.key;
            node.value = current.value;
            return current.left;
        }
        current.right = removeNodeLeft(current.right, node);
        return checkBalance(current);
    }

    protected int compareTo(Object key1, Object key2) {
        return key1.toString().compareTo(key2.toString());
    }

    public void testBalance() {
        testBalance(root);
    }

    private static void testBalance(Node node) {
        if (node == null) {
            return;
        }
        int balance = h(node.left) - h(node.right);
        if (balance < -1 || 1 < balance) {
            throw new IllegalStateException("Node " + node.key + " is disbalanced.");
        }
        testBalance(node.left);
        testBalance(node.right);
    }

    private static Node checkBalance(Node node) {
        if (node == null) {
            return null;
        }
        int hL = h(node.left);
        int hR = h(node.right);
        int balance = hR - hL;
        if (balance < -1) {
            Node b = node.left;
            if (h(b.left) >= h(b.right)) {
                node = rotateRightSmall(node);
            } else {
                node = rotateRightBig(node);
            }
        } else if (balance <= 1) {
            node.updateHeight();
        } else {
            Node b = node.right;
            if (h(b.right) >= h(b.left)) {
                node = rotateLeftSmall(node);
            } else {
                node = rotateLeftBig(node);
            }
        }

        return node;
    }

    private static Node rotateRightSmall(Node a) {
        Node b = a.left;
        Node c = b.right;
        b.right = a;
        a.left = c;
        a.updateHeight();
        b.updateHeight();
        return b;
    }

    private static Node rotateRightBig(Node a) {
        Node b = a.left;
        Node c = b.right;
        b.right = c.left;
        a.left = c.right;
        c.left = b;
        c.right = a;
        a.updateHeight();
        b.updateHeight();
        c.updateHeight();
        return c;
    }

    private static Node rotateLeftSmall(Node a) {
        Node b = a.right;
        Node c = b.left;
        b.left = a;
        a.right = c;
        a.updateHeight();
        b.updateHeight();
        return b;
    }

    private static Node rotateLeftBig(Node a) {
        Node b = a.right;
        Node c = b.left;
        b.left = c.right;
        a.right = c.left;
        c.right = b;
        c.left = a;
        a.updateHeight();
        b.updateHeight();
        c.updateHeight();
        return c;
    }

    private static int h(Node node) {
        return node == null ? 0 : node.h;
    }

    private static final class Node {
        public Object key;
        public Object value;
        public Node left = null;
        public Node right = null;
        public int h = 1;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public void updateHeight() {
            h = Math.max(1 + h(left), 1 + h(right));
        }
    }
}
