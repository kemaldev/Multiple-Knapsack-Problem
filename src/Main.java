import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        Main main = new Main();

        //Setting up knapsacks and items.
        ArrayList<Knapsack> knapsacks = new ArrayList<>();
        knapsacks.add(new Knapsack(120, "s1"));
        knapsacks.add(new Knapsack(80, "s2"));
        knapsacks.add(new Knapsack(35, "s3"));

        ArrayList<KnapsackItem> items = new ArrayList<>();
        items.add(new KnapsackItem(23, 92, "i1"));
        items.add(new KnapsackItem(31, 57, "i2"));
        items.add(new KnapsackItem(29, 49, "i3"));
        items.add(new KnapsackItem(44, 68, "i4"));
        items.add(new KnapsackItem(53, 60, "i5"));
        items.add(new KnapsackItem(38, 43, "i6"));
        items.add(new KnapsackItem(63, 67, "i7"));
        items.add(new KnapsackItem(85, 84, "i8"));
        items.add(new KnapsackItem(89, 87, "i9"));
        items.add(new KnapsackItem(82, 72, "i10"));

        main.solveMultipleKnapsack(items, knapsacks);
        main.printResult(knapsacks);

    }

    public void solveMultipleKnapsack(ArrayList<KnapsackItem> items, ArrayList<Knapsack> knapsacks) {
        Collections.sort(items, new Comparator<KnapsackItem>() {
            @Override
            public int compare(KnapsackItem i1, KnapsackItem i2) {
                if(i1.getValueByWeight() > i2.getValueByWeight()) {
                    return -1;
                } else if (i1.getValueByWeight() < i2.getValueByWeight()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        Knapsack bestKnapsack;
        double bestWeightDifference;
        double currentWeightDifference;
        for(KnapsackItem item : items) {
            bestWeightDifference = Integer.MAX_VALUE;
            bestKnapsack = null;
            for(Knapsack knapsack : knapsacks) {
                if(knapsack.getCap() > item.getWeight()) {
                    currentWeightDifference = knapsack.getCap() - item.getWeight();
                    if(currentWeightDifference < bestWeightDifference && currentWeightDifference > 0) {
                        bestWeightDifference = currentWeightDifference;
                        bestKnapsack = knapsack;
                    }
                }
            }
            if(bestKnapsack != null) {
                bestKnapsack.addItem(item);
            }
        }

    }

    public void printResult(ArrayList<Knapsack> knapsacks) {
        for (Knapsack knapsack : knapsacks) {
            System.out.println("Knapsack\n" + "Name: " + knapsack.getName()
                    + "\nStart weight: " + knapsack.getStartWeight() + "\nWeight-Cap: " + knapsack.getCap() + "\n");
            for(KnapsackItem item : knapsack.getItems()) {
                System.out.println("Item\n" + "Name: " + item.getName()
                        + "\nValue: " + item.getValue() + "\nWeight: " + item.getWeight());
            }
            System.out.println("---------------------------");
        }
    }
}
