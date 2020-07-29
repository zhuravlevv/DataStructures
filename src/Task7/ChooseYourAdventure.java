package Task7;

import java.util.Scanner;

/**
 * A class to create Choose Your Adventure App and using it.
 *
 * To satisfy the requirements for this class are used types: int, String.
 *
 */
public class ChooseYourAdventure {

    private Node head;
    private Element root;
    private int size;
    private int maxId;

    public ChooseYourAdventure() {
        head = null;
        root = null;
        size = 0;
        maxId = 0;
    }

    /**
     * A method to start play.
     * <p>
     * Prints starting story. Then checks the user's selection and
     * prints the next stories until the user reaches the ending story.
     * <p>
     * Time complexity: O(log(n))
     * Space complexity: O(n).
     */
    public void play() {
        System.out.println(root.story);
        root.timesVisited++;
        Element.addStoryLine(root.story);
        Element tmp = root;
        while (tmp != null) {
            if (tmp.isEnding()) {
                System.out.println("\nThe end of the story!");
                break;
            }
            if (tmp.left != null) {
                System.out.print("Choose next branch from");
                System.out.print(" (1)");
                if (tmp.right != null) {
                    System.out.print(", (2)");
                }
                System.out.print(": ");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                if (choice == 1) {
                    tmp = tmp.left;
                    System.out.println(tmp.story);
                    tmp.timesVisited++;
                    Element.addStoryLine(" -> \n" + tmp.story);
                } else if (choice == 2) {
                    if (tmp.right != null) {
                        tmp = tmp.right;
                        System.out.println(tmp.story);
                        tmp.timesVisited++;
                        Element.addStoryLine(" -> \n" + tmp.story);
                    } else {
                        System.out.println("Branch (" + choice + ") doesn't exist.");
                    }
                } else {
                    System.out.println("Branch (" + choice + ") doesn't exist.");
                }
            } else {
                System.out.println("\nThe end of the story!");
                break;
            }
        }

    }

    /**
     * A method to print not ending nodes.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     *
     * @return number of not ending elements.
     */
    public int printNotEndingNodes() {
        int count = 0;
        Node tmp = null;
        if (head != null) {
            tmp = head.next;
        }
        while (tmp != null) {
            if (!tmp.element.isEnding()) {
                System.out.print("Element #" + tmp.id);
                System.out.println(": " + tmp.element.story);
                count++;
            }
            tmp = tmp.next;
        }
        return count;
    }

    /**
     * A method to update element.
     * <p>
     * Checks if there are elements to update. Take element id.
     * Update element with that id.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     */
    public void update() {
        if (size != 0) {
            int count = printNotEndingNodes();
            if (count != 0) {
                int id;
                System.out.print("Enter element number: ");
                Scanner scanner = new Scanner(System.in);
                id = scanner.nextInt();
                if (id <= maxId) {
                    if (size == 1 || id == 1) {
                        System.out.println("You can't update starting element.");
                    } else {
                        Node tmp = head;
                        while (tmp.next.id != id) {
                            tmp = tmp.next;
                            if (tmp.next == null) {
                                System.out.println("Element number " + id + " doesn't exist.");
                                return;
                            }
                        }
                        if (!tmp.next.element.isEnding()) {
                            System.out.print("Enter new story: ");
                            Scanner updateScanner = new Scanner(System.in);
                            String story = updateScanner.nextLine();
                            tmp.next.element.updateStory(story);
                        } else {
                            System.out.println("You can't update ending element.");
                        }
                    }
                } else {
                    System.out.println("Element number " + id + " doesn't exist.");
                }
            } else {
                System.out.println("There are no elements to update.");
            }
        } else {
            System.out.println("There are no elements.");
        }
    }

    /**
     * A method to print nodes.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     */
    public void printNodes() {
        Node tmp = head;
        while (tmp != null) {
            System.out.print("Element #" + tmp.id);
            if (tmp.element.isEnding()) {
                System.out.print(" (Ending)");
            }
            System.out.println(": " + tmp.element.story);
            tmp = tmp.next;
        }
    }

    /**
     * A method to initialise Choose Your Adventure App.
     * <p>
     * Creates start element. Take number of ending elements.
     * Creates ending elements.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     */
    public void init() {
        System.out.print("Enter story for start element: ");
        Scanner scanner = new Scanner(System.in);
        String story = scanner.nextLine();
        root = new Element(null, null, story, false);
        head = new Node(null, root, ++maxId);
        size++;
        System.out.print("Enter the number of ending elements: ");
        int num = scanner.nextInt();
        size += num;
        for (int i = 0; i < num; i++) {
            Scanner scanner1 = new Scanner(System.in);
            int number = i + 1;
            System.out.print("Enter story for ending element #" + number + ": ");
            String endingStory = scanner1.nextLine();
            Element element = new Element(null, null, endingStory, true);
            Node node = new Node(null, element, ++maxId);

            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = node;
        }
    }

    /**
     * A method to print nodes with free links.
     * <p>
     * Goes through the list of nodes. Prints nodes with free links and keeps
     * their ids.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     *
     * @return ids nodes with free links.
     */
    public int[] printNodesWithFreeLinks() {
        Node tmp = head;
        int[] ids = new int[maxId];
        int i = 0;
        while (tmp != null) {
            if ((tmp.element.left == null || tmp.element.right == null) && !tmp.element.isEnding()) {
                System.out.println("Element #" + tmp.id + ": " + tmp.element.story);
                ids[i] = tmp.id;
                i++;
            }
            tmp = tmp.next;
        }
        return ids;
    }

    /**
     * A method to add link.
     * <p>
     * Finds nodes with ids from and to.
     * Adds link between them.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     *
     * @param from - node id from the element of which the link will go.
     * @param to   - node id to the element of which the link will go.
     */
    private void addLink(int from, int to) {
        Element fromElement = null, toElement = null;
        Node tmp = head;
        while (tmp != null) {
            if (tmp.id == from) {
                fromElement = tmp.element;
            }
            if (tmp.id == to) {
                toElement = tmp.element;
            }
            tmp = tmp.next;
        }
        assert fromElement != null;
        if (fromElement.left == null) {
            fromElement.left = toElement;
        } else {
            fromElement.right = toElement;
        }
    }

    /**
     * A method to remove link.
     * <p>
     * Finds nodes with ids from and to.
     * Removes link between them.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     *
     * @param from - node id from the element of which the link will be removed.
     * @param to   - node id to the element of which the link will be removed.
     */
    private void removeLink(int from, int to) {
        Node tmp = head;
        Element elementFrom = null, elementTo = null;
        while (tmp != null) {
            if (tmp.id == from) {
                elementFrom = tmp.element;
            }
            if (tmp.id == to) {
                elementTo = tmp.element;
            }
            tmp = tmp.next;
        }
        if (elementFrom != null) {
            if (elementFrom.right != null) {
                if (elementFrom.right == elementTo) {
                    elementFrom.right = null;
                }
            }
            if (elementFrom.left != null) {
                if (elementFrom.left == elementTo) {
                    elementFrom.left = null;
                }
            }
        }
    }

    /**
     * A method to add link.
     * <p>
     * Checks of there are elements to link.
     * Asks the user between which elements he wants to add a link.
     * Calls private addLink method.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     */
    public void addLink() {
        int[] ids = printNodesWithFreeLinks();
        if (ids.length == 0) {
            System.out.println("There are no elements to link.");
            return;
        }
        if (ids[0] == 0) {
            System.out.println("There are no elements to link.");
            return;
        }
        System.out.println("Choose which element you want to add the link to: ");
        System.out.print("Enter element number: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        boolean idExists = false;
        for (int i = 0; i < ids.length; i++) {
            if (id == ids[i]) {
                idExists = true;
            }
        }
        if (idExists) {
            System.out.println("Choose which element you want to link to: ");
            printNodes();
            System.out.print("Enter element number: ");
            int nextId = scanner.nextInt();
            if (nextId != id) {
                addLink(id, nextId);
            } else {
                System.out.println("You cannot link an element to itself");
            }
        } else {
            System.out.println("Element with id = " + id + " doesn't exist.");
        }
    }

    /**
     * A method to remove link.
     * <p>
     * Asks the user between which elements he wants to remove a link.
     * Calls private removeLink method.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     */
    public void removeLink() {
        System.out.println("Select the elements between which you want to remove the link: ");
        printNodes();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the element number the link you want to remove from: ");
        int from = scanner.nextInt();
        System.out.print("Enter the element number the link you want to remove: ");
        int to = scanner.nextInt();
        removeLink(from, to);
    }

    /**
     * A method to return number of elements.
     * <p>
     * Time complexity: O(1)
     * Space complexity: O(1).
     *
     * @return number of elements.
     */
    public int numberOfElements() {
        return size;
    }

    /**
     * A method to add element.
     * <p>
     * Takes story from creator. Creates element and adds it to list.
     * <p>
     * Time complexity: O(n)
     * Space complexity: O(n).
     */
    public void addElement() {
        String story;
        System.out.print("Enter story: ");
        Scanner scanner = new Scanner(System.in);
        story = scanner.nextLine();
        Element element = new Element(null, null, story, false);
        Node node = new Node(null, element, ++maxId);
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
     * A method to remove element.
     * <p>
     * Checks if there are elements to remove.
     * Takes element number to remove. Removes if element exists.
     * Remove links connected with that element.
     * <p>
     * Time complexity: O(n^2)
     * Space complexity: O(n).
     */
    public void removeElement() {
        if (size != 0) {
            int count = printNotEndingNodes();
            if (count != 0) {
                int id;
                System.out.print("Enter element number: ");
                Scanner scanner = new Scanner(System.in);
                id = scanner.nextInt();
                if (id <= maxId) {
                    if (size == 1 || id == 1) {
                        System.out.println("You can't remove starting element.");
                    } else {
                        Node tmp = head;
                        while (tmp.next.id != id) {
                            tmp = tmp.next;
                            if (tmp.next == null) {
                                System.out.println("Element number " + id + " doesn't exist.");
                                return;
                            }
                        }
                        if (!tmp.next.element.isEnding()) {
                            for (int i = 1; i <= maxId; i++) {
                                if (i != id) {
                                    removeLink(id, i);
                                    removeLink(i, id);
                                }
                            }
                            tmp.next = tmp.next.next;
                        } else {
                            System.out.println("You can't remove ending element.");
                        }
                    }
                    size--;
                } else {
                    System.out.println("Element number " + id + " doesn't exist.");
                }
            } else {
                System.out.println("There are no elements to remove.");
            }
        } else {
            System.out.println("There are no elements.");
        }
    }


    private static class Element {
        private Element left;
        private Element right;
        private String story;
        private boolean ending;
        private static String storyLine;
        private int timesVisited;

        public Element(Element left, Element right, String story, boolean ending) {
            this.left = left;
            this.right = right;
            this.story = story;
            this.ending = ending;
        }

        /**
         * A method to add story to storyLine.
         * <p>
         * Time complexity: O(1)
         * Space complexity: O(1).
         *
         * @param story - story to add.
         */
        public static void addStoryLine(String story) {
            storyLine = storyLine.concat(story);
        }

        /**
         * A method to return isEnding.
         * <p>
         * Time complexity: O(1)
         * Space complexity: O(1).
         *
         * @return is ending element.
         */
        public boolean isEnding() {
            return ending;
        }

        /**
         * A method to update story.
         * <p>
         * Time complexity: O(1)
         * Space complexity: O(1).
         *
         * @param story - new story.
         */
        public void updateStory(String story) {
            this.story = story;
        }

        /**
         * A method to remove story.
         * <p>
         * Time complexity: O(1)
         * Space complexity: O(1).
         */
        public void removeStory() {
            story = "";
        }

        /**
         * A method to print times visited.
         * <p>
         * Time complexity: O(1)
         * Space complexity: O(1).
         */
        public void printTimesVisited() {
            System.out.println("This element was " + timesVisited + " times visited.");
        }

    }

    private static class Node {
        private Node next;
        private Element element;
        private int id;

        public Node(Node next, Element element, int id) {
            this.next = next;
            this.element = element;
            this.id = id;
        }
    }
}
