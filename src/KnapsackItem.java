public class KnapsackItem {

    private int weight;
    private int value;
    private double valueByWeight;

    private String name;
    public KnapsackItem(int weight, int value, String name) {
        this.weight = weight;
        this.value = value;
        valueByWeight = (double) value / (double) weight; //Dunno if I have to cast here but I'll do it just in case.
        this.name = name;
    }

    public double getValueByWeight() {
        return valueByWeight;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
