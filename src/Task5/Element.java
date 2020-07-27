package Task5;

public class Element {

    private int key;
    private String name;

    public Element(){
        key = 0;
        name = "";
    }

    public Element(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
