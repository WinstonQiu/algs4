package chapter3;

import java.util.Iterator;

public class SequentialSearchST<Key, Value> {
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    public boolean delete(Key key) {
        if (key == null || first == null) return false;

        if (key.equals(first.key)) {
            first = first.next;
            return true;
        }

        for (Node x = first; x.next != null; x = x.next) {
            if (key.equals(x.next.key)) {
                x.next = x.next.next;
                return true;
            }
        }

        return false;
    }

    private class KeyIterator implements Iterator<Key> {
        private Node ptr;

        public KeyIterator() {
            ptr = first;
        }

        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        @Override
        public Key next() {
            Key retKey = ptr.key;
            ptr = ptr.next;
            return retKey;
        }
    }

    public Iterable<Key> keys() {
        return KeyIterator::new;
    }

}
