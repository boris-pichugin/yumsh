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

        int hL = h(node.left);
        int hR = h(node.right);
        int balance = hR - hL;
        if (balance < -1) {
            // TODO Надо вращать: левое длиннее
            Node b = node.left;
            if (h(b.left) >= h(b.right)) {
                node = rotateRightSmall(node);
            } else {
                node = rotateRightBig(node);
            }
        } else if (balance <= 1) {
            node.updateHeight();
        } else {
            node.updateHeight();
            // TODO Надо вращать: правое длиннее
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


    private static int h(Node node) {
        return node == null ? 0 : node.h;
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
        Node parent = null;
        int parentDirection = 0;
        Node node = root;
        while (node != null) {
            int cmp = compareTo(key, node.key);
            if (cmp < 0) {
                parent = node;
                parentDirection = -1;
                node = node.left;
            } else if (cmp > 0) {
                parent = node;
                parentDirection = 1;
                node = node.right;
            } else {
                size -= 1;
                if (node.left == null) {
                    if (parentDirection == -1) {
                        parent.left = node.right;
                    } else if (parentDirection == 1) {
                        parent.right = node.right;
                    } else {
                        root = node.right;
                    }
                } else if (node.right == null) {
                    if (parentDirection == -1) {
                        parent.left = node.left;
                    } else if (parentDirection == 1) {
                        parent.right = node.left;
                    } else {
                        root = node.left;
                    }
                } else {
                    if (parentDirection == -1) {
                        parent.left = node.left;
                    } else if (parentDirection == 1) {
                        parent.right = node.left;
                    } else {
                        root = node.left;
                    }
                    Node nodeRight = node.right;
                    Node deepNode = node.left.right;
                    while (deepNode.right != null) {
                        deepNode = deepNode.right;
                    }
                    deepNode.right = nodeRight;
                }
                return;
            }
        }
    }

    protected int compareTo(Object key1, Object key2) {
        return key1.toString().compareTo(key2.toString());
    }

    private static final class Node {
        public final Object key;
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
