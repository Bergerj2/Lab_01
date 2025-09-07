import java.io.Serializable;
import java.util.Objects;
import java.io.Serializable;
import java.util.Objects;

/**
 * A class that represents a product with an ID, name, description, and cost.
 * This class provides methods to format the product data into different formats
 * such as CSV, JSON, and XML.
 */
public class Product implements Serializable {
    private final String ID; // Unique identifier for the product, should never change
    private String name;
    private String description;
    private double cost;

    /**
     * Constructs a new Product object.
     *
     * @param ID          The unique identifier for the product.
     * @param name        The name of the product.
     * @param description A brief description of the product.
     * @param cost        The cost of the product, which must be a non-negative value.
     * @throws IllegalArgumentException if the cost is negative.
     */
    public Product(String ID, String name, String description, double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be a negative value.");
        }
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    // Getters and Setters

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be a negative value.");
        }
        this.cost = cost;
    }

    /**
     * Returns a comma-separated value (CSV) string representation of the product.
     * @return A string in CSV format.
     */
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f", ID, name, description, cost);
    }

    /**
     * Returns a JSON string representation of the product.
     * @return A string in JSON format.
     */
    public String toJSON() {
        return String.format("{\"ID\": \"%s\", \"name\": \"%s\", \"description\": \"%s\", \"cost\": %.2f}",
                ID, name, description, cost);
    }

    /**
     * Returns an XML string representation of the product.
     * @return A string in XML format.
     */
    public String toXML() {
        return String.format("<Product><ID>%s</ID><name>%s</name><description>%s</description><cost>%.2f</cost></Product>",
                ID, name, description, cost);
    }

    /**
     * Returns a formatted string representation of the product's data.
     * @return A formatted string.
     */
    @Override
    public String toString() {
        return String.format("ID: %s | Name: %-25s | Description: %-30s | Cost: $%.2f",
                ID, name, description, cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.cost, cost) == 0 && Objects.equals(ID, product.ID) && Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, description, cost);
    }
}