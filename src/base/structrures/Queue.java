package base.structrures;

public class Queue <E> {

    private Node<E> root;
    private long size;

    public Queue() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        Node<E> node = new Node<>(e, null);
        if (root == null) {
            root = node;
        } else {
            Node<E> tmp = root;
            while (tmp.prev != null) {
                tmp = tmp.prev;
            }
            tmp.prev = node;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove() {
        root = root.prev;
        size--;
    }

    public E peek() {
        return root.item;
    }

    private static class Node<E> {
        private E item;
        private Node<E> prev;

        public Node(E item, Node<E> prev) {
            this.item = item;
            this.prev = prev;
        }
    }
}
