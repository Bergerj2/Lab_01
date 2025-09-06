import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test suite for the Product class.
 * This suite tests constructors, setter methods, and special methods.
 * It does NOT test getter methods as per the requirements.
 */
public class ProductTest {
    private Product validProduct;
    private final String VALID_ID = "000001";
    private final String VALID_NAME = "Laptop";
    private final String VALID_DESCRIPTION = "A powerful computing device";
    private final double VALID_COST = 1200.50;

    @BeforeEach
    void setUp() {
        validProduct = new Product(VALID_ID, VALID_NAME, VALID_DESCRIPTION, VALID_COST);
    }

    @Test
    void testConstructorWithValidData() {
        assertDoesNotThrow(() -> new Product("000002", "Mouse", "Ergonomic wireless mouse", 25.00),
                "Constructor should not throw an exception with valid data.");
    }

    @Test
    void testConstructorWithInvalidCost() {
        // Test that a negative cost throws an IllegalArgumentException
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                        new Product("000003", "Keyboard", "Mechanical keyboard", -10.00),
                "Constructor should throw an exception for negative cost.");
        assertEquals("Cost cannot be a negative value.", thrown.getMessage());
    }

    @Test
    void testSetName() {
        String newName = "Desktop";
        validProduct.setName(newName);
        assertEquals(newName, validProduct.getName(), "setName should correctly update the product's name.");
    }

    @Test
    void testSetDescription() {
        String newDescription = "A high-end gaming PC";
        validProduct.setDescription(newDescription);
        assertEquals(newDescription, validProduct.getDescription(), "setDescription should correctly update the product's description.");
    }

    @Test
    void testSetCost() {
        double newCost = 1500.75;
        validProduct.setCost(newCost);
        assertEquals(newCost, validProduct.getCost(), 0.01, "setCost should correctly update the product's cost.");
    }

    @Test
    void testSetCostWithInvalidData() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                        validProduct.setCost(-50.00),
                "setCost should throw an exception for negative cost.");
        assertEquals("Cost cannot be a negative value.", thrown.getMessage());
    }

    @Test
    void toCSV() {
        String expectedCSV = String.format("%s,%s,%s,%.2f", VALID_ID, VALID_NAME, VALID_DESCRIPTION, VALID_COST);
        assertEquals(expectedCSV, validProduct.toCSV(), "toCSV should return a correctly formatted string.");
    }

    @Test
    void toJSON() {
        String expectedJSON = String.format("{\"ID\": \"%s\", \"name\": \"%s\", \"description\": \"%s\", \"cost\": %.2f}",
                VALID_ID, VALID_NAME, VALID_DESCRIPTION, VALID_COST);
        assertEquals(expectedJSON, validProduct.toJSON(), "toJSON should return a correctly formatted string.");
    }

    @Test
    void toXML() {
        String expectedXML = String.format("<Product><ID>%s</ID><name>%s</name><description>%s</description><cost>%.2f</cost></Product>",
                VALID_ID, VALID_NAME, VALID_DESCRIPTION, VALID_COST);
        assertEquals(expectedXML, validProduct.toXML(), "toXML should return a correctly formatted string.");
    }

    @Test
    void testToString() {
        String expectedString = String.format("ID: %s | Name: %-25s | Description: %-30s | Cost: $%.2f",
                VALID_ID, VALID_NAME, VALID_DESCRIPTION, VALID_COST);
        assertEquals(expectedString, validProduct.toString(), "toString should return a correctly formatted string.");
    }
}