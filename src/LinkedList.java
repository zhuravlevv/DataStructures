public class LinkedList {

    private Node first;
    private Node last;
    private long size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void printFirst() {
        Node tmp = first;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    public void printLast() {
        Node tmp = last;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.prev;
        }
    }

    /**
     * Add e as first element.
     * <p>
     * Time complexity: O(1)
     * Space complexity: O(1).
     *
     * @param e - element.
     */
    public void addFirst(int e) {
        Node f = first;
        Node newNode = new Node(e, f, null);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    /**
     * Add e as last element.
     * <p>
     * Time complexity: O(1)
     * Space complexity: O(1).
     *
     * @param e - element.
     */
    public void addLast(int e) {
        Node l = last;
        Node newNode = new Node(e, null, l);
        last = newNode;
        if (l == null) {
            first = newNode;
        }
        else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * Function to start recursive quickSort sort.
     * <p>
     * Time complexity: O(n^2)
     * Space complexity: O(log(n)).
     */
    public void quickSort() {
        quickSort(first, last);
    }

    /**
     * Function to start recursive merge sort.
     * <p>
     * Time complexity: O(n*log(n))
     * Space complexity: O(n).
     */
    public void mergeSort() {
        if (size == 0) {
            return;
        }
        first = mergeSort(first);
        Node tmp = first;
        if (size == 1) {
            last = tmp;
        } else {
            while (tmp.next.next != null) {
                tmp = tmp.next;
            }
            last = tmp.next;
            last.prev = tmp;
        }
    }

    /**
     * Function to split nodes of the given doubly linked list into
     * two halves using the fast/slow pointer strategy.
     * <p>
     * Advance 'fast' by two nodes, and advance 'slow' by single node.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     *
     * @param head - head to split it.
     * @return the slow pointer.
     */
    private Node split(Node head) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }

        return slow;
    }

    /**
     * Recursive function to merge nodes of two sorted lists together
     * into a single sorted list.
     * <p>
     * Checks if a or b is null and return them.
     * Pick either a or b, and recursively calls itself.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     *
     * @param a - the first list.
     * @param b - the second list.
     * @return the head of the result list.
     */
    private Node merge(Node a, Node b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        if (a.data <= b.data) {
            a.next = merge(a.next, b);
            a.next.prev = a;
            a.prev = null;
            return a;
        } else {
            b.next = merge(a, b.next);
            b.next.prev = b;
            b.prev = null;
            return b;
        }
    }

    /**
     * Function to sort a doubly linked list using merge sort algorithm.
     * <p>
     * Split head into 'a' and 'b' sub-lists. Recursively sort the sub-lists.
     * Merge the two sorted lists together.
     * <p>
     * Time complexity: O(n*log(n))
     * Space complexity: O(n).
     *
     * @param head - the head of the list.
     * @return the head of the result list.
     */
    private Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node a = head, b;

        Node slow = split(head);
        b = slow.next;
        slow.next = null;

        a = mergeSort(a);
        b = mergeSort(b);

        head = merge(a, b);
        return head;
    }

    /**
     * A function to swap elements in the wrong positions.
     * <p>
     * Considers last element as pivot, places the pivot element at its
     * correct position in sorted array, and places all smaller (smaller than
     * pivot) to left of pivot and all greater elements to right of pivot.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     *
     * @param f - the first node.
     * @param l - the last node.
     * @return the result node.
     */
    private Node partition(Node f, Node l) {
        int x = l.data;

        Node i = f.prev;

        for (Node j = f; j != l; j = j.next) {
            if (j.data <= x) {
                i = (i == null) ? f : i.next;
                int temp = i.data;
                i.data = j.data;
                j.data = temp;
            }
        }
        i = (i == null) ? f : i.next;
        int temp = i.data;
        i.data = l.data;
        l.data = temp;
        return i;
    }

    /**
     * A recursive implementation of quick sort for linked list.
     *
     * Goes through the list. Calls partition from f to l and the
     * calls itself with list halves.
     *
     * Time complexity: O(n^2)
     * Space complexity: O(log(n)).
     *
     * @param f - the first node.
     * @param l - the last node.
     */
    private void quickSort(Node f, Node l) {
        if (l != null && f != l && f != l.next) {
            Node temp = partition(f, l);
            quickSort(f, temp.prev);
            quickSort(temp.next, l);
        }
    }

    private static class Node {
        int data;
        LinkedList.Node next;
        LinkedList.Node prev;

        public Node(int data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
