import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        //Setting up knapsacks and items.
        MultipleKnapsack knapsacks = new MultipleKnapsack();
        knapsacks.addKnapsack(new Knapsack(20, "s1"));
        knapsacks.addKnapsack(new Knapsack(15, "s2"));
        knapsacks.addKnapsack(new Knapsack(12, "s3"));
        knapsacks.addKnapsack(new Knapsack(5, "s4"));

        LinkedList<KnapsackItem> items = new LinkedList<>();
        items.add(new KnapsackItem(5, 10, "i1"));
        items.add(new KnapsackItem(10, 5, "i2"));
        items.add(new KnapsackItem(5, 2, "i3"));
        items.add(new KnapsackItem(7, 17, "i4"));
        items.add(new KnapsackItem(15, 8, "i5"));
        items.add(new KnapsackItem(20, 30, "i6"));
        items.add(new KnapsackItem(15, 8, "i7"));
        items.add(new KnapsackItem(20, 30, "i8"));


        knapsacks.greedyMultipleKnapsack(items);
        knapsacks.calculateValue();
        MultipleKnapsack result = knapsacks.neighborSearch(knapsacks);
        result.printResult();

    }

}
