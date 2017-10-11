public class KnapsackItem {

    private int weight;
    private int value;
    private double valueByWeight;
    private String name;

    /**
     * Constructor that instantiates weight, value and name for an item.
     * @param weight
     * @param value
     * @param name
     */
    public KnapsackItem(int weight, int value, String name) {
        this.weight = weight;
        this.value = value;
        valueByWeight = (double) value / (double) weight;
        this.name = name;
    }

    /**
     * Method that gets the value / weight value from an item.
     * @return
     */
    public double getValueByWeight() {
        return valueByWeight;
    }

    /**
     * Method that returns the weight an item has.
     * @return
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Method that gets the value an item has.
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * Method that sets the name of an item.
     * @return
     */
    public String getName() {
        return name;
    }
}
