import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class StrongPasswordTool {

    static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    static final String DIGITS = "0123456789";
    static final String SYMBOLS = "!@#$%^&*()-_=+";
    static Random rand = new Random();
    public static double calculateEntropy(String password) {
        int charsetSize = 0;
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSymbol = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else hasSymbol = true;
        }

        if (hasUpper) charsetSize += 26;
        if (hasLower) charsetSize += 26;
        if (hasDigit) charsetSize += 10;
        if (hasSymbol) charsetSize += 14;
        if (charsetSize == 0) return 0;
        return password.length() * (Math.log(charsetSize) / Math.log(2));
    }
    public static boolean checkPassword(String password) {
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSymbol = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else hasSymbol = true;
        }
        return hasUpper && hasLower && hasDigit && hasSymbol;
    }

    public static String generatePassword(int length) {
        ArrayList<Character> pwd = new ArrayList<>();
        
        pwd.add(UPPERCASE.charAt(rand.nextInt(UPPERCASE.length())));
        pwd.add(LOWERCASE.charAt(rand.nextInt(LOWERCASE.length())));
        pwd.add(DIGITS.charAt(rand.nextInt(DIGITS.length())));
        pwd.add(SYMBOLS.charAt(rand.nextInt(SYMBOLS.length())));

        for (int i = 4; i < length; i++) {
            int type = rand.nextInt(4);
            switch (type) {
                case 0: pwd.add(UPPERCASE.charAt(rand.nextInt(UPPERCASE.length()))); break;
                case 1: pwd.add(LOWERCASE.charAt(rand.nextInt(LOWERCASE.length()))); break;
                case 2: pwd.add(DIGITS.charAt(rand.nextInt(DIGITS.length()))); break;
                case 3: pwd.add(SYMBOLS.charAt(rand.nextInt(SYMBOLS.length()))); break;
            }
        }

        Collections.shuffle(pwd); 

        StringBuilder sb = new StringBuilder();
        for (char ch : pwd) sb.append(ch);
        return sb.toString();
    }

    public static void displayMenu() {
        System.out.println("\n============================");
        System.out.println("   STRONG PASSWORD TOOL   ");
        System.out.println("============================");
        System.out.println("1. Check password strength");
        System.out.println("2. Generate strong passwords");
        System.out.println("3. Show history");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void handlePasswordCheck(Scanner sc) {
        System.out.print("Enter password to check: ");
        String pwd = sc.next();

        double entropy = calculateEntropy(pwd);
        System.out.println("\n--- Analysis ---");
        System.out.println("Entropy Score: " + String.format("%.2f", entropy) + " bits");

        if (!checkPassword(pwd)) {
            System.out.println("Requirement Status: FAILED (Missing required character types)");
        } else {
            System.out.println("Requirement Status: PASSED");
        }

        if (entropy < 40) System.out.println("Overall Strength: WEAK");
        else if (entropy < 60) System.out.println("Overall Strength: MEDIUM");
        else System.out.println("Overall Strength: STRONG");
    }

    public static void handlePasswordGeneration(Scanner sc, ArrayList<String> history) {
        System.out.print("How many passwords to generate? ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        int count = sc.nextInt();

        System.out.print("Password length (min 8 recommended): ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
        }
        int length = sc.nextInt();

        if (length < 4) {
            System.out.println("Length too short, setting to 8 for security.");
            length = 8;
        }

        System.out.println("\nGenerated Passwords:");
        for (int i = 0; i < count; i++) {
            String pwd = generatePassword(length);
            history.add(pwd);
            System.out.println("- " + pwd);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> history = new ArrayList<>();
        int choice = 0;

        do {
            displayMenu();
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                switch (choice) {
                    case 1: handlePasswordCheck(sc); break;
                    case 2: handlePasswordGeneration(sc, history); break;
                    case 3: 
                        if (history.isEmpty()) System.out.println("No history yet.");
                        else history.forEach(System.out::println);
                        break;
                    case 4: System.out.println("Goodbye!"); break;
                    default: System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
        } while (choice != 4);
        
        sc.close();
    }
}