import java.util.LinkedList;

public class Knapsack {

    private int cap;
    private int startWeight;
    private String name;
    private LinkedList<KnapsackItem> items;

    public Knapsack(int cap, String name) {
        this.cap = cap;
        this.name = name;
        this.startWeight = cap;
        items = new LinkedList<>();
    }

    public Knapsack(Knapsack knapsack) {
        this.cap = knapsack.getCap();
        this.startWeight = knapsack.getStartWeight();
        this.name = knapsack.getName();
        this.items = new LinkedList<>(knapsack.getItems()); //Might not work, look into this later.
    }

    public void addItem(KnapsackItem item) {
        if(item != null) {
            items.add(item);
            cap = cap - item.getWeight();
        }
    }

    public void resetCap() {
        cap = startWeight;
    }

    public int getStartWeight() {
        return startWeight;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getName() {
        return name;
    }

    public LinkedList<KnapsackItem> getItems() {
        return items;
    }
}
