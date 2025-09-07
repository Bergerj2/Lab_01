
/**
 * A short program to test the new object-oriented SafeInputObj class.
 */
public class ObjInputTest {
    public static void main(String[] args) {
        SafeInputObj in = new SafeInputObj();

        System.out.println("--- Testing SafeInputObj Methods ---");

        // Test getNonZeroLenString
        String nonZeroString = in.getNonZeroLenString("Enter a non-empty string");
        System.out.println("Non-zero string entered: " + nonZeroString);

        // Test getRangedInt
        int rangedInt = in.getRangedInt("Enter an integer between 1 and 10", 1, 10);
        System.out.println("Ranged integer entered: " + rangedInt);

        // Test getDouble
        double unconstrainedDouble = in.getDouble("Enter a double");
        System.out.println("Unconstrained double entered: " + unconstrainedDouble);

        // Test getYNConfirm
        boolean confirmation = in.getYNConfirm("Do you want to continue?");
        System.out.println("Confirmation result: " + confirmation);

        System.out.println("--- Test complete ---");
    }
}