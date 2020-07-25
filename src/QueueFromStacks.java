import java.util.Stack;

public class QueueFromStacks<T> {

    private Stack<T> s1, s2;
    private long size;

    public QueueFromStacks() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /**
     * Adds an element to the queue.
     *
     * Move all elements from s1 to s2. Push the element to s1.
     * Move all elements from s2 to s1.
     *
     * Time complexity: O(n)
     *
     * @param t an element.
     */
    public void enqueue(T t) {
        size++;
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        s1.push(t);

        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    /**
     * Dequeue an element from the queue.
     *
     * Pop element from s1.
     *
     * Time complexity: O(1)
     */
    public void dequeue() {
        if (s1.empty()) {
            return;
        }
        s1.pop();
        size--;
    }

    /**
     * Returns the size of the queue.
     *
     * Time complexity: O(1)
     *
     * @return the size of the queue.
     */
    public long size() {
        return size;
    }

    /**
     * Returns the front element of the queue.
     *
     * Time complexity: O(1)
     *
     * @return the front element of the queue.
     */
    public T front() {
        if (s1.empty()) {
            return null;
        }
        return s1.peek();
    }

    /**
     * Returns true if the queue is currently empty.
     *
     * Time complexity: O(1)
     *
     * @return true if the queue is currently empty.
     */
    public boolean isEmpty() {
        return s1.isEmpty();
    }

}
