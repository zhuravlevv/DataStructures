package base.structrures;

public class Stack<E> {

    private int size;
    private Node<E> root;

    public Stack() {
        size = 0;
        root = null;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void push(E e){
        Node<E> node = new Node<>(e, null);
        if(root == null){
            root = node;
        }
        else{
            node.next = root;
            root = node;
        }
        size++;
    }

    public E pop(){
        E e = root.item;
        root = root.next;
        size--;
        return e;
    }

    public E peek(){
        return root.item;
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
