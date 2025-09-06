import java.util.Calendar;
import java.util.Objects;

/**
 * The Person class represents a person with an ID, name, title, and year of birth.
 * It provides methods for accessing and modifying data, as well as methods for
 * calculating age and formatting the data into different string representations.
 */
public class Person {

    private String ID;
    private String firstName;
    private String lastName;
    private String title;
    private int YOB; // Year of Birth

    /**
     * Constructs a new Person object with the specified details.
     * @param ID The unique ID of the person.
     * @param firstName The first name of the person.
     * @param lastName The last name of the person.
     * @param title The title of the person (e.g., Mr., Mrs., Ms.).
     * @param YOB The year of birth of the person, must be between 1940 and 2010.
     * @throws IllegalArgumentException if the year of birth is outside the valid range.
     */
    public Person(String ID, String firstName, String lastName, String title, int YOB) {
        if (YOB < 1940 || YOB > 2010) {
            throw new IllegalArgumentException("Year of Birth must be between 1940 and 2010.");
        }
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.YOB = YOB;
    }

    /**
     * Gets the unique ID of the person.
     * @return The person's ID.
     */
    public String getID() {
        return ID;
    }

    /**
     * Gets the first name of the person.
     * @return The person's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the person.
     * @param firstName The new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the person.
     * @return The person's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person.
     * @param lastName The new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the title of the person.
     * @return The person's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the person.
     * @param title The new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the year of birth of the person.
     * @return The person's year of birth.
     */
    public int getYOB() {
        return YOB;
    }

    /**
     * Sets the year of birth of the person.
     * @param YOB The new year of birth, must be between 1940 and 2010.
     * @throws IllegalArgumentException if the year of birth is outside the valid range.
     */
    public void setYOB(int YOB) {
        if (YOB < 1940 || YOB > 2010) {
            throw new IllegalArgumentException("Year of Birth must be between 1940 and 2010.");
        }
        this.YOB = YOB;
    }

    /**
     * Returns the person's full name, formatted as "firstName lastName".
     * @return The person's full name.
     */
    public String fullName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Returns the person's formal name, formatted as "title firstName lastName".
     * @return The person's formal name.
     */
    public String formalName() {
        return this.title + " " + fullName();
    }

    /**
     * Calculates and returns the person's age based on the current year.
     * @return The person's age.
     */
    public int getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - YOB;
    }

    /**
     * Calculates and returns the person's age for a specified year.
     * @param year The year to calculate the age for.
     * @return The person's age in the specified year.
     */
    public int getAge(int year) {
        return year - YOB;
    }

    /**
     * Overrides the default toString method to provide a formatted string
     * representation of the Person object's data.
     * @return A formatted string representation of the Person.
     */
    @Override
    public String toString() {
        return String.format("ID: %s | First: %-10s | Last: %-10s | Title: %-5s | YOB: %4d",
                ID, firstName, lastName, title, YOB);
    }

    /**
     * Returns the Person object's data in a comma-separated value (CSV) format.
     * @return A CSV-formatted string.
     */
    public String toCSV() {
        return ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
    }

    /**
     * Returns a JSON string representation of the person.
     *
     * @return A JSON formatted string.
     */
    public String toJSON() {
        return "{\"ID\": \"" + ID + "\", \"firstName\": \"" + firstName + "\", \"lastName\": \"" + lastName + "\", \"title\": \"" + title + "\", \"YOB\": " + YOB + "}";
    }

    /**
     * Returns an XML string representation of the person.
     *
     * @return An XML formatted string.
     */
    public String toXML() {
        return "<Person><ID>" + ID + "</ID><firstName>" + firstName + "</firstName><lastName>" + lastName + "</lastName><title>" + title + "</title><YOB>" + YOB + "</YOB></Person>";
    }

    /**
     * Compares this Person object to another object for equality.
     * Two persons are considered equal if their IDs are the same.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return ID.equals(person.ID);
    }
}