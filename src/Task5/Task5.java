package Task5;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * To avoid O(n) or worst complexity you should use binary tree. For example we can use TreeMap because we have a key in our elements.
 * All operations will have O(log(n)) complexity in worst case.
 */
public class Task5 {
    public static void main(String[] args) {
        List<Element> elements = new ArrayList<>();
        for (int i = 1; i <= 128; i++) {
            Element e = new Element(i, "name" + i);
            elements.add(e);
        }

        TreeMap<Integer, Element> treeMap = new TreeMap<>();

        for (Element e :elements) {
            treeMap.put(e.getKey(), e);
        }

    }
}
