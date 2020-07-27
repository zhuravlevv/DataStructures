/**
 * Singly-linked list
 *
 * To satisfy the requirements for this class are used types: long, Object.
 *
 */
public class PythonList {

    private Node head;
    private long size;

    public PythonList() {
        head = null;
        size = 0;
    }

    /**
     * Returns the size of the list.
     * <p>
     * Time complexity: O(1).
     * Space complexity: O(1).
     *
     * @return the size of the list.
     */
    public long length() {
        return size;
    }

    /**
     * Returns true if the list is currently empty.
     * <p>
     * Time complexity: O(1)
     * Space complexity: O(1)
     *
     * @return true if the list is currently empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Appends the specified element to the end of this list.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param x element to be appended to this list.
     */
    public void append(Object x) {
        Node node = new Node(x, null);
        if (head == null) {
            head = node;
        } else {
            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = node;
        }
        size++;
    }

    /**
     * Removes all elements from the list.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public void clear() {
        for (Node tmp = head; tmp != null; ) {
            Node next = tmp.next;
            tmp.item = null;
            tmp.next = null;
            tmp = next;
        }
        head = null;
        size = 0;
    }

    /**
     * Returns the number of times x appears in the list.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param x the required element.
     * @return number of times x appears in the list.
     */
    public int count(Object x) {
        Node tmp = head;
        int count = 0;
        while (tmp != null) {
            if (tmp.item.equals(x)) {
                count++;
            }
            tmp = tmp.next;
        }
        return count;
    }

    /**
     * Returns element stored at i elements away from the list.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param i position of the element.
     * @return the required element.
     */
    public Object get(int i) {
        if (i >= size) {
            return null;
        }
        Node tmp = head;
        while (tmp != null && i != 0) {
            tmp = tmp.next;
            i--;
        }
        assert tmp != null;
        return tmp.item;
    }

    /**
     * Add an item to the list at position first element.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param i position of the element.
     * @param x element to be appended to this list.
     */
    public void insert(int i, Object x) {
        if (i < size) {
            Node node = new Node(x, null);
            if (i == 0) {
                node.next = head;
                head = node;
            } else {
                Node tmp = head;
                while (i != 1) {
                    tmp = tmp.next;
                    i--;
                }
                node.next = tmp.next;
                tmp.next = node;
            }
            size++;
        }
    }

    /**
     * Remove the first item from the list whose value is equal to x.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param x value.
     */
    public void remove(Object x) {
        if (head.item.equals(x)) {
            head = head.next;
        } else {
            Node tmp = head;
            while (tmp.next != null) {
                if (tmp.next.item.equals(x)) {
                    tmp.next = tmp.next.next;
                    break;
                }
                tmp = tmp.next;
            }
        }
    }

    /**
     * Reverse the elements of the list in place.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public void reverse() {
        Node tmp = head;
        Node prev = null;
        while (tmp.next != null) {
            Node next = tmp.next;
            tmp.next = prev;
            prev = tmp;
            tmp = next;
        }
        tmp.next = prev;
        head = tmp;
    }

    private static class Node {
        Object item;
        PythonList.Node next;

        public Node(Object item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
