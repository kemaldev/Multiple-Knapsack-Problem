import java.util.ArrayList;

public class Knapsack {

    private int cap;
    private int startWeight;
    private String name;
    private ArrayList<KnapsackItem> items;

    public Knapsack(int cap, String name) {
        this.cap = cap;
        this.name = name;
        this.startWeight = cap;
        items = new ArrayList<>();
    }

    public void addItem(KnapsackItem item) {
        if(item != null) {
            items.add(item);
            cap = cap - item.getWeight();
        }
    }

    public int getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(int startWeight) {
        this.startWeight = startWeight;
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

    public ArrayList<KnapsackItem> getItems() {
        return items;
    }
}
