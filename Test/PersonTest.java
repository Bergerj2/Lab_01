import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

/**
 * JUnit test class for the Person.java class.
 * This class tests the constructor, setters, and all additional methods
 * as required by the lab instructions.
 */
class PersonTest {

    private Person validPerson;
    private final String VALID_ID = "000001";
    private final String VALID_FIRST_NAME = "John";
    private final String VALID_LAST_NAME = "Doe";
    private final String VALID_TITLE = "Mr.";
    private final int VALID_YOB = 1980;

    /**
     * Sets up a new valid Person object before each test.
     * This is a test fixture that provides a known state for testing.
     */
    @BeforeEach
    void setUp() {
        validPerson = new Person(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_TITLE, VALID_YOB);
    }

    /**
     * Tests the constructor with valid input to ensure the object is correctly initialized.
     * This test implicitly checks the getters to verify the constructor's work.
     */
    @Test
    void testValidConstructor() {
        // Assert that the fields are correctly set by the constructor
        assertEquals(VALID_ID, validPerson.getID(), "ID should be " + VALID_ID);
        assertEquals(VALID_FIRST_NAME, validPerson.getFirstName(), "First name should be " + VALID_FIRST_NAME);
        assertEquals(VALID_LAST_NAME, validPerson.getLastName(), "Last name should be " + VALID_LAST_NAME);
        assertEquals(VALID_TITLE, validPerson.getTitle(), "Title should be " + VALID_TITLE);
        assertEquals(VALID_YOB, validPerson.getYOB(), "YOB should be " + VALID_YOB);
    }

    /**
     * Tests the constructor with an invalid year of birth to ensure it throws an IllegalArgumentException.
     */
    @Test
    void testInvalidYOBConstructor() {
        // Test a year below the valid range
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("000002", "Jane", "Doe", "Ms.", 1939);
        }, "Constructor should throw an exception for YOB < 1940");

        // Test a year above the valid range
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("000003", "Jane", "Doe", "Ms.", 2011);
        }, "Constructor should throw an exception for YOB > 2010");
    }

    /**
     * Tests the setFirstName method.
     */
    @Test
    void setFirstName() {
        validPerson.setFirstName("Jane");
        assertEquals("Jane", validPerson.getFirstName(), "First name should be updated to Jane");
    }

    /**
     * Tests the setLastName method.
     */
    @Test
    void setLastName() {
        validPerson.setLastName("Smith");
        assertEquals("Smith", validPerson.getLastName(), "Last name should be updated to Smith");
    }

    /**
     * Tests the setTitle method.
     */
    @Test
    void setTitle() {
        validPerson.setTitle("Prof.");
        assertEquals("Prof.", validPerson.getTitle(), "Title should be updated to Prof.");
    }

    /**
     * Tests the setYOB method with a valid year.
     */
    @Test
    void setYOB() {
        validPerson.setYOB(2000);
        assertEquals(2000, validPerson.getYOB(), "YOB should be updated to 2000");
    }

    /**
     * Tests the setYOB method with an invalid year.
     */
    @Test
    void testSetInvalidYOB() {
        assertThrows(IllegalArgumentException.class, () -> {
            validPerson.setYOB(1930);
        }, "setYOB should throw an exception for year < 1940");

        assertThrows(IllegalArgumentException.class, () -> {
            validPerson.setYOB(2020);
        }, "setYOB should throw an exception for year > 2010");
    }

    /**
     * Tests the fullName method.
     */
    @Test
    void fullName() {
        assertEquals("John Doe", validPerson.fullName(), "Full name should be correctly formatted");
    }

    /**
     * Tests the formalName method.
     */
    @Test
    void formalName() {
        assertEquals("Mr. John Doe", validPerson.formalName(), "Formal name should be correctly formatted");
    }

    /**
     * Tests the getAge method (with current year).
     */
    @Test
    void testGetAgeCurrentYear() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        assertEquals(currentYear - VALID_YOB, validPerson.getAge(), "Age should be correct based on current year");
    }

    /**
     * Tests the getAge method (with a specified year).
     */
    @Test
    void testGetAgeSpecifiedYear() {
        assertEquals(20, validPerson.getAge(2000), "Age should be 20 for the year 2000");
    }

    /**
     * Tests the toString method.
     */
    @Test
    void testToString() {
        String expectedString = "ID: 000001 | First: John       | Last: Doe        | Title: Mr.   | YOB: 1980";
        assertEquals(expectedString, validPerson.toString(), "toString method should return a correctly formatted string");
    }

    /**
     * Tests the toCSV method.
     */
    @Test
    void toCSV() {
        String expectedCSV = "000001, John, Doe, Mr., 1980";
        assertEquals(expectedCSV, validPerson.toCSV(), "toCSV method should return a correctly formatted CSV string");
    }

    /**
     * Tests the toJSON method.
     */
    @Test
    void toJSON() {
        String expectedJSON = "{\"ID\": \"000001\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"title\": \"Mr.\", \"YOB\": 1980}";
        assertEquals(expectedJSON, validPerson.toJSON(), "toJSON method should return a correctly formatted JSON string");
    }

    /**
     * Tests the toXML method.
     */
    @Test
    void toXML() {
        String expectedXML = "<Person><ID>000001</ID><firstName>John</firstName><lastName>Doe</lastName><title>Mr.</title><YOB>1980</YOB></Person>";
        assertEquals(expectedXML, validPerson.toXML(), "toXML method should return a correctly formatted XML string");
    }

    /**
     * Tests the equals method with two equal objects.
     */
    @Test
    void testEquals() {
        Person anotherPerson = new Person(VALID_ID, "Jane", "Smith", "Ms.", 1990);
        assertEquals(validPerson, anotherPerson, "Two persons with the same ID should be considered equal");
    }

    /**
     * Tests the equals method with two different objects.
     */
    @Test
    void testEquals_NotEqual() {
        Person differentPerson = new Person("000002", "Jane", "Doe", "Ms.", 1990);
        assertNotEquals(validPerson, differentPerson, "Two persons with different IDs should not be considered equal");
    }
}