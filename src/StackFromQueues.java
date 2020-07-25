import java.util.LinkedList;
import java.util.Queue;

public class StackFromQueues <T> {

    private Queue<T> q1, q2;
    private long size;

    public StackFromQueues() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /**
     * Adds an element to the stack.
     *
     * Enqueue x to q2. One by one dequeue everything from q1 and
     * enqueue to q2. Swap the names of q1 and q2
     *
     * Time complexity: O(n)
     *
     * @param t position of the element.
     */
    public void push(T t) {
        size++;

        q2.add(t);

        while (!q1.isEmpty()) {
            q2.add(q1.peek());
            q1.remove();
        }

        Queue<T> q = q1;
        q1 = q2;
        q2 = q;
    }

    /**
     * Removes an element from the stack.
     *
     * Dequeue an item from q1.
     *
     * Time complexity: O(1)
     *
     */
    public void pop() {
        if (q1.isEmpty()) {
            return;
        }
        q1.remove();
        size--;
    }

    /**
     * Returns the size of the stack.
     *
     * Time complexity: O(1)
     *
     * @return the size of the stack.
     */
    public long size() {
        return size;
    }

    /**
     * Returns the top element of the stack.
     *
     * Time complexity: O(1)
     *
     * @return the top element of the stack.
     */
    public T top() {
        if (q1.isEmpty()) {
            return null;
        }
        return q1.peek();
    }

    /**
     * Returns true if the stack is currently empty.
     *
     * Time complexity: O(1)
     *
     * @return true if the stack is currently empty.
     */
    public boolean isEmpty() {
        return q1.isEmpty();
    }

}
