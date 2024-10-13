package model;

public class Treatment {
    private String description;
    private double cost;
//no need of cost....instead give discount percentage according to schemes
    public Treatment(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() { return description; }
    public double getCost() { return cost; }
}
