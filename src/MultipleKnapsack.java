import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class MultipleKnapsack {

    private LinkedList<Knapsack> knapsacks;
    private LinkedList<KnapsackItem> items;
    private int value;

    public LinkedList<MultipleKnapsack> getNeighbors(LinkedList<Knapsack> knapsacks, LinkedList<KnapsackItem> items) {
        LinkedList<MultipleKnapsack> knapsackNeighbors = new LinkedList<>();

        for (int gKnapsack = 0; gKnapsack < knapsacks.size(); gKnapsack++) {
            for (int gItem = 0; gItem < knapsacks.get(gKnapsack).getItems().size(); gItem++) {
                for (int lKnapsack = 0; lKnapsack < knapsacks.size(); lKnapsack++) {
                    for (int lItem = 0; lItem < knapsacks.get(lKnapsack).getItems().size(); lItem++) {
                        Knapsack globalKnapsack = knapsacks.get(gKnapsack);
                        Knapsack localKnapsack = knapsacks.get(lKnapsack);

                        if(!globalKnapsack.equals(localKnapsack)) {
                            LinkedList<KnapsackItem> globalItems = globalKnapsack.getItems();
                            LinkedList<KnapsackItem> localItems = localKnapsack.getItems();
                            if(globalItems.get(gItem).getWeight() <= localItems.get(lItem).getWeight() + localKnapsack.getCap()) {

                                MultipleKnapsack neighbor = new MultipleKnapsack();
                                LinkedList<Knapsack> currentKnapsack = new LinkedList<>();
                                LinkedList<KnapsackItem> currentItems = new LinkedList<>(items);
                                for(Knapsack knapsack : knapsacks) {
                                    if(knapsack.equals(localKnapsack)) {
                                        Knapsack local = new Knapsack(knapsack);
                                        local.setCap(localKnapsack.getCap() + localItems.get(lItem).getWeight() - globalItems.get(gItem).getWeight());
                                        local.getItems().set(lItem, globalItems.get(gItem));
                                        currentKnapsack.add(local);
                                    } else if(knapsack.equals(globalKnapsack)) {
                                        Knapsack global = new Knapsack(knapsack);
                                        global.setCap(global.getCap() + global.getItems().get(gItem).getWeight());
                                        global.getItems().remove(gItem);
                                        currentKnapsack.add(global);
                                    } else {
                                        currentKnapsack.add(new Knapsack(knapsack));
                                    }
                                }

                                neighbor.setKnapsacks(currentKnapsack);
                                neighbor.setItems(currentItems);
                                neighbor.shuffleItemsInKnapsacks(); //Move around items to make place - Might not work with the current solution
                                neighbor.greedyMultipleKnapsack(currentItems);
                                neighbor.calculateValue();
                                knapsackNeighbors.add(neighbor);
                            }
                        }
                    }
                }
            }
        }

        return knapsackNeighbors;
    }

    public MultipleKnapsack neighborSearch(MultipleKnapsack knapsacks) {
        LinkedList<MultipleKnapsack> neighbors = getNeighbors(knapsacks.getKnapsacks(), knapsacks.getItems());
        for (MultipleKnapsack neighbor : neighbors) {
            if(neighbor.getValue() > knapsacks.getValue()) {
                knapsacks = neighborSearch(neighbor);
            }
        }

        return knapsacks;
    }

    public void shuffleItemsInKnapsacks() {
        LinkedList<KnapsackItem> itemsInKnapsacks = new LinkedList<>();
        for (Knapsack knapsack : knapsacks) {
            itemsInKnapsacks.addAll(knapsack.getItems());
        }

        Collections.sort(itemsInKnapsacks, new Comparator<KnapsackItem>() {
            @Override
            public int compare(KnapsackItem i1, KnapsackItem i2) {
                if(i1.getWeight() > i2.getWeight()) {
                    return -1;
                } else if (i1.getWeight() < i2.getWeight()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        for(Knapsack knapsack : knapsacks) {
            knapsack.getItems().clear();
            knapsack.resetCap();
            for(Iterator<KnapsackItem> it = itemsInKnapsacks.iterator(); it.hasNext(); ) {
                KnapsackItem item = it.next();
                if(item.getWeight() <= knapsack.getCap()) {
                    knapsack.addItem(item);
                    it.remove();
                }
            }
        }
    }

    public void greedyMultipleKnapsack(LinkedList<KnapsackItem> items) {

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

        for (int i = 0; i < items.size(); i++) {
            if(!this.items.contains(items.get(i))) {
                this.items.add(items.get(i));
            }
            bestWeightDifference = Integer.MAX_VALUE;
            bestKnapsack = null;
            for (int j = 0; j < knapsacks.size(); j++) {
                if(knapsacks.get(j).getCap() >= items.get(i).getWeight()) {
                    currentWeightDifference = knapsacks.get(j).getCap() - items.get(i).getWeight();
                    if(currentWeightDifference < bestWeightDifference && currentWeightDifference > 0) {
                        bestWeightDifference = currentWeightDifference;
                        bestKnapsack = knapsacks.get(j);
                    }
                }
            }
            if(bestKnapsack != null) {
                bestKnapsack.addItem(items.get(i));
                this.items.remove(items.get(i));
            }
        }
    }

    public void calculateValue() {
        int value = 0;

        for(Knapsack knapsack : knapsacks) {
            for (KnapsackItem item : knapsack.getItems()) {
                value += item.getValue();
            }
        }

        this.value = value;
    }

    public void printResult() {
        for (Knapsack knapsack : knapsacks) {
            System.out.println("Knapsack\n" + "Name: " + knapsack.getName()
                    + "\nStart weight: " + knapsack.getStartWeight() + "\nWeight-Cap: " + knapsack.getCap() + "\n");
            for(KnapsackItem item : knapsack.getItems()) {
                System.out.println("Item\n" + "Name: " + item.getName()
                        + "\nValue: " + item.getValue() + "\nWeight: " + item.getWeight());
            }
            System.out.println("---------------------------");
        }

        System.out.println("Total value: " + value);
    }

    public void setItems(LinkedList<KnapsackItem> items) {
        this.items = items;
    }

    public void setKnapsacks(LinkedList<Knapsack> knapsacks) {
        this.knapsacks = knapsacks;
    }

    public int getValue() {
        return value;
    }

    public MultipleKnapsack() {
        knapsacks = new LinkedList<>();
        items = new LinkedList<>();
    }

    public LinkedList<Knapsack> getKnapsacks() {
        return knapsacks;
    }

    public LinkedList<KnapsackItem> getItems() {
        return items;
    }

    public void addKnapsack(Knapsack knapsack) {
        knapsacks.add(knapsack);
    }

}
