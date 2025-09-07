import java.util.Scanner;

/**
 * A class to get safe user console input without the need to pass a Scanner as a parameter to each method.
 */
public class SafeInputObj {
    private Scanner pipe;

    /**
     * The default constructor. This constructor creates a Scanner that is associated with the System.in console.
     */
    public SafeInputObj() {
        this.pipe = new Scanner(System.in);
    }

    /**
     * A constructor that takes a Scanner object as a parameter.
     * @param scanner The Scanner object to be used for input.
     */
    public SafeInputObj(Scanner scanner) {
        this.pipe = scanner;
    }

    /**
     * Gets a string from the user that is not a zero-length string.
     *
     * @param prompt The prompt to display to the user.
     * @return A non-zero length String.
     */
    public String getNonZeroLenString(String prompt) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    /**
     * Gets a string from the user that is at least a specified minimum length.
     *
     * @param prompt The prompt to display to the user.
     * @param minLen The minimum length of the string.
     * @return A string with a length greater than or equal to minLen.
     */
    public String getMinLenString(String prompt, int minLen) {
        String retString = "";
        do {
            System.out.print("\n" + prompt + "[ >= " + minLen + " chars]: ");
            retString = pipe.nextLine();
            if (retString.length() < minLen) {
                System.out.println("Too short! Must be at least " + minLen + " chars long!");
            }
        } while (retString.length() < minLen);
        return retString;
    }

    /**
     * Gets an integer value from the user with no constraints.
     *
     * @param prompt The prompt to display to the user.
     * @return The unconstrained integer value entered by the user.
     */
    public int getInt(String prompt) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);
        return retVal;
    }

    /**
     * Gets an integer value from the user within a specified inclusive range.
     *
     * @param prompt The prompt to display to the user.
     * @param low The low end of the inclusive range.
     * @param high The high end of the inclusive range.
     * @return The integer value within the inclusive range.
     */
    public int getRangedInt(String prompt, int low, int high) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + "[" + low + "-" + high + "]: ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);
        return retVal;
    }

    /**
     * Gets an unconstrained double value from the user.
     *
     * @param prompt The prompt to display to the user.
     * @return An unconstrained double value.
     */
    public double getDouble(String prompt) {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while (!done);
        return retVal;
    }

    /**
     * Gets a double value from the user within a specified inclusive range.
     *
     * @param prompt The prompt to display to the user.
     * @param low The low end of the inclusive range.
     * @param high The high end of the inclusive range.
     * @return The double value within the inclusive range.
     */
    public double getRangedDouble(String prompt, double low, double high) {
        double retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + "[" + low + "-" + high + "]: ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine();
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("\nNumber is out of range [" + low + "-" + high + "]: " + retVal);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        } while (!done);
        return retVal;
    }

    /**
     * Gets a [Y/N] confirmation from the user.
     *
     * @param prompt The prompt to display to the user.
     * @return true if the user enters 'Y' or 'y', false if the user enters 'N' or 'n'.
     */
    public boolean getYNConfirm(String prompt) {
        boolean retVal = true;
        String response = "";
        boolean gotAVal = false;

        do {
            System.out.print("\n" + prompt + " [Y/N] ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                gotAVal = true;
                retVal = true;
            } else if (response.equalsIgnoreCase("N")) {
                gotAVal = true;
                retVal = false;
            } else {
                System.out.println("You must answer [Y/N]! " + response);
            }
        } while (!gotAVal);

        return retVal;
    }

    /**
     * Gets a string that matches a specified regular expression pattern.
     *
     * @param prompt The prompt to display to the user.
     * @param regExPattern The regular expression pattern to match.
     * @return A string that matches the RegEx pattern.
     */
    public String getRegExString(String prompt, String regExPattern) {
        String response = "";
        boolean gotAVal = false;

        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regExPattern)) {
                gotAVal = true;
            } else {
                System.out.println("\n" + response + " must match the pattern " + regExPattern);
                System.out.println("Try again!");
            }
        } while (!gotAVal);

        return response;
    }
}
