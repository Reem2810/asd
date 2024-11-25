package nl.han.asd;

public class Pizza {
    // Attributes
    private String pizzaName;
    private int numberOfSlices;

    // Constructor
    public Pizza(String pizzaName, int numberOfSlices) {
        if (pizzaName == null || pizzaName.isEmpty()) {
            throw new IllegalArgumentException("Pizza name cannot be null or empty.");
        }
        if (numberOfSlices <= 0) {
            throw new IllegalArgumentException("Number of slices must be positive.");
        }
        this.pizzaName = pizzaName;
        this.numberOfSlices = numberOfSlices;
    }

    // Getters
    public String getPizzaName() {
        return pizzaName;
    }

    public int getNumberOfSlices() {
        return numberOfSlices;
    }

    // Optional: toString method for better readability in logs and tests
    @Override
    public String toString() {
        return "Pizza{" +
                "pizzaName='" + pizzaName + '\'' +
                ", numberOfSlices=" + numberOfSlices +
                '}';
    }
}
