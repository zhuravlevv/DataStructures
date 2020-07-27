/**
 * A self-balancing binary tree (AVL tree).
 *
 * To satisfy the requirements for this class are used types: generic T.
 *
 */
public class AVLTree <T> {

    private Node root;

    /**
     * A method to insert the element.
     *
     * Time complexity: O(log(n)).
     * Space complexity: O(n).
     *
     * @param t - new element.
     */
    public void insert(T t) {
        root = insert(root, t);
    }

    /**
     * A method to delete the element.
     * <p>
     * Time complexity: O(log(n)).
     * Space complexity: O(n).
     *
     * @param t - element.
     */
    public void delete(T t) {
        deleteNode(root, t);
    }

    /**
     * A method to get the height of the tree.
     *
     * Time complexity: O(1)
     * Space complexity: O(1).
     *
     * @param node - node.
     * @return the height.
     */
    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    /**
     * A utility function to get maximum of two integers.
     *
     * Time complexity: O(1)
     * Space complexity: O(1).
     *
     * @param a - the first number.
     * @param b - the second number.
     * @return maximum of two integers.
     */
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     * A method to right rotate subtree rooted with y.
     *
     * Time complexity: O(1)
     * Space complexity: O(1).
     *
     * @param y - root.
     * @return the height.
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    /**
     * A method to left rotate subtree rooted with x.
     *
     * Time complexity: O(1)
     * Space complexity: O(1).
     *
     * @param x - root.
     * @return the y.
     */
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    /**
     * A method to get balance factor of node.
     *
     * Time complexity: O(1)
     * Space complexity: O(1)
     *
     * @param node - node.
     * @return the balance factor.
     */
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    /**
     * A recursive method to insert node.
     *
     * Check if node is null and if it is - create node.
     * Depend on key insert on right or left node or return node
     * Update height of this ancestor node.
     * Get the balance factor of this ancestor
     * node to check whether this node became
     * unbalanced.
     * If this node becomes unbalanced, then there
     * are 4 cases: Left Left, Right Right, Left Right,
     * Right Left.
     *
     * Time complexity: O(log(n))
     * Space complexity: O(n)
     *
     * @param node - node to insertion.
     * @param key  - key.
     * @return the node.
     */
    private Node insert(Node node, T key) {

        if (node == null) {
            return (new Node(key));
        }

        if (key.hashCode() < node.key.hashCode()) {
            node.left = insert(node.left, key);
        }
        else if (key.hashCode() > node.key.hashCode()) {
            node.right = insert(node.right, key);
        }
        else {
            return node;
        }

        node.height = 1 + max(height(node.left),
                height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.hashCode() < node.left.key.hashCode()) {
            return rightRotate(node);
        }

        if (balance < -1 && key.hashCode() > node.right.key.hashCode()) {
            return leftRotate(node);
        }

        if (balance > 1 && key.hashCode() > node.left.key.hashCode()) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.hashCode() < node.right.key.hashCode()) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * A method to get min value of node.
     *
     * Time complexity: O(log(n))
     * Space complexity: O(n).
     *
     * @param node - node.
     * @return left node.
     */
    private Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    /**
     * A method to delete node.
     *
     * Check if root is null and if it is - return it.
     * If the key to be deleted is smaller than the root's key,
     * then it lies in left subtree.
     * If the key to be deleted is greater than the root's key,
     * then it lies in right subtree.
     * If key is same as root's key, then this is the node to
     * be deleted.
     * Then update height of the current node.
     * Get the balance factor if this node (to check whether
     * this node became unbalanced).
     * If this node becomes unbalanced, then there are 4 cases:
     * Left Left, Left Right, Right Right, Right Left.
     *
     * Time complexity: O(log(n))
     * Space complexity: O(n).
     *
     * @param root - root node.
     * @param key  - value.
     * @return root.
     */
    private Node deleteNode(Node root, T key) {
        if (root == null)
            return root;

        if (key.hashCode() < root.key.hashCode()) {
            root.left = deleteNode(root.left, key);
        }

        else if (key.hashCode() > root.key.hashCode()) {
            root.right = deleteNode(root.right, key);
        }

        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = deleteNode(root.right, (T) temp.key);
            }
        }

        if (root == null) {
            return root;
        }

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private static class Node<T> {
        T key;
        int height;
        Node left, right;

        Node(T d) {
            key = d;
            height = 1;
        }
    }
}
