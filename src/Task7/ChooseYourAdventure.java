package Task7;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChooseYourAdventure {

    private Node head;
    private Element root;
    private int size;
    private int maxId;

    public ChooseYourAdventure() {
        head = null;
        root = null;
        size = 0;
        maxId = 1;
    }

    public void printNodes(){
        Node tmp = head;
        while(tmp!=null){
            System.out.println("Element #" + tmp.id + ": " + tmp.element.story);
            tmp = tmp.next;
        }
    }

    public List<Integer> printNodesWithFreeLinks(){
        Node tmp = head;
        List<Integer> ids = new ArrayList<>();
        while(tmp!=null){
            if(tmp.element.left!=null || tmp.element.right!=null){
                System.out.println("Element #" + tmp.id + ": " + tmp.element.story);

            }
            tmp = tmp.next;
        }
    }


    public void addLink(){
        printNodes();

    }

    public int numberOfElements(){
        return size;
    }

    public void addElement() {
        String story;
        System.out.print("Enter story: ");
        Scanner scanner = new Scanner(System.in);
        story = scanner.nextLine();
        Element element = new Element(null, null, story, false);
        Node node = new Node(null, element, maxId++);
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

    public void dropElement(){
        printNodes();
        int id;
        System.out.print("Enter element number: ");
        Scanner scanner = new Scanner(System.in);
        id = scanner.nextInt();
        if(id<=maxId) {
            if (id == 1) {
                head = head.next;
            } else {
                Node tmp = head;
                while(tmp.next.id != id){
                    tmp = tmp.next;
                    if(tmp.next == null) {
                        System.out.println("Element number " + id + " doesn't exist.");
                        return;
                    }
                }
                tmp.next = tmp.next.next;
                size--;
            }
        }
        else {
            System.out.println("Element number " + id + " doesn't exist.");
        }
    }


    private static class Element{
        private Element left;
        private Element right;
        private String story;
        private boolean ending;

        public Element(Element left, Element right, String story, boolean ending) {
            this.left = left;
            this.right = right;
            this.story = story;
            this.ending = ending;
        }
    }

    private static class Node{
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
