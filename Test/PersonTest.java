import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

class PersonTest {

    private Person validPerson;
    private final String VALID_ID = "000001";
    private final String VALID_FIRST_NAME = "John";
    private final String VALID_LAST_NAME = "Doe";
    private final String VALID_TITLE = "Mr.";
    private final int VALID_YOB = 1980;

    @BeforeEach
    void setUp() {
        validPerson = new Person(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_TITLE, VALID_YOB);
    }

    @Test
    void testValidConstructor() {
        // Assert that the fields are correctly set by the constructor
        assertEquals(VALID_ID, validPerson.getID(), "ID should be " + VALID_ID);
        assertEquals(VALID_FIRST_NAME, validPerson.getFirstName(), "First name should be " + VALID_FIRST_NAME);
        assertEquals(VALID_LAST_NAME, validPerson.getLastName(), "Last name should be " + VALID_LAST_NAME);
        assertEquals(VALID_TITLE, validPerson.getTitle(), "Title should be " + VALID_TITLE);
        assertEquals(VALID_YOB, validPerson.getYOB(), "YOB should be " + VALID_YOB);
    }

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

    @Test
    void setFirstName() {
        validPerson.setFirstName("Jane");
        assertEquals("Jane", validPerson.getFirstName(), "First name should be updated to Jane");
    }

    @Test
    void setLastName() {
        validPerson.setLastName("Smith");
        assertEquals("Smith", validPerson.getLastName(), "Last name should be updated to Smith");
    }

    @Test
    void setTitle() {
        validPerson.setTitle("Prof.");
        assertEquals("Prof.", validPerson.getTitle(), "Title should be updated to Prof.");
    }

    @Test
    void setYOB() {
        validPerson.setYOB(2000);
        assertEquals(2000, validPerson.getYOB(), "YOB should be updated to 2000");
    }

    @Test
    void testSetInvalidYOB() {
        assertThrows(IllegalArgumentException.class, () -> {
            validPerson.setYOB(1930);
        }, "setYOB should throw an exception for year < 1940");

        assertThrows(IllegalArgumentException.class, () -> {
            validPerson.setYOB(2020);
        }, "setYOB should throw an exception for year > 2010");
    }

    @Test
    void fullName() {
        assertEquals("John Doe", validPerson.fullName(), "Full name should be correctly formatted");
    }

    @Test
    void formalName() {
        assertEquals("Mr. John Doe", validPerson.formalName(), "Formal name should be correctly formatted");
    }

    @Test
    void testGetAgeCurrentYear() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        assertEquals(currentYear - VALID_YOB, validPerson.getAge(), "Age should be correct based on current year");
    }

    @Test
    void testGetAgeSpecifiedYear() {
        assertEquals(20, validPerson.getAge(2000), "Age should be 20 for the year 2000");
    }

    @Test
    void testToString() {
        String expectedString = "ID: 000001 | First: John       | Last: Doe        | Title: Mr.  | YOB: 1980";
        assertEquals(expectedString, validPerson.toString(), "toString method should return a correctly formatted string");
    }

    @Test
    void toCSV() {
        String expectedCSV = "000001, John, Doe, Mr., 1980";
        assertEquals(expectedCSV, validPerson.toCSV(), "toCSV method should return a correctly formatted CSV string");
    }

    @Test
    void toJSON() {
        String expectedJSON = "{\"ID\": \"000001\", \"firstName\": \"John\", \"lastName\": \"Doe\", \"title\": \"Mr.\", \"YOB\": 1980}";
        assertEquals(expectedJSON, validPerson.toJSON(), "toJSON method should return a correctly formatted JSON string");
    }

    @Test
    void toXML() {
        String expectedXML = "<Person><ID>000001</ID><firstName>John</firstName><lastName>Doe</lastName><title>Mr.</title><YOB>1980</YOB></Person>";
        assertEquals(expectedXML, validPerson.toXML(), "toXML method should return a correctly formatted XML string");
    }

    @Test
    void testEquals() {
        Person anotherPerson = new Person(VALID_ID, "Jane", "Smith", "Ms.", 1990);
        assertEquals(validPerson, anotherPerson, "Two persons with the same ID should be considered equal");
    }

    @Test
    void testEquals_NotEqual() {
        Person differentPerson = new Person("000002", "Jane", "Doe", "Ms.", 1990);
        assertNotEquals(validPerson, differentPerson, "Two persons with different IDs should not be considered equal");
    }
}