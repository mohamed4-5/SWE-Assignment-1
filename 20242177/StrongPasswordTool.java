package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class StrongPasswordTool {

    static String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String lowercase = "abcdefghijklmnopqrstuvwxyz";
    static String digits = "0123456789";
    static String symbols = "!@#$%^&*()-_=+";
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
        pwd.add(uppercase.charAt(rand.nextInt(uppercase.length())));
        pwd.add(lowercase.charAt(rand.nextInt(lowercase.length())));
        pwd.add(digits.charAt(rand.nextInt(digits.length())));
        pwd.add(symbols.charAt(rand.nextInt(symbols.length())));

        for (int i = 4; i < length; i++) {
            int type = rand.nextInt(4);
            char ch = switch (type) {
                case 0 -> uppercase.charAt(rand.nextInt(uppercase.length()));
                case 1 -> lowercase.charAt(rand.nextInt(lowercase.length()));
                case 2 -> digits.charAt(rand.nextInt(digits.length()));
                case 3 -> symbols.charAt(rand.nextInt(symbols.length()));
                default -> 'a';
            };
            pwd.add(ch);
        }

        Collections.shuffle(pwd);

        StringBuilder sb = new StringBuilder();
        for (char ch : pwd) sb.append(ch);
        return sb.toString();
    }

    public static void displayMenu() {
        System.out.println("\n=== Strong Password Tool ===");
        System.out.println("1. Check password strength");
        System.out.println("2. Generate strong passwords");
        System.out.println("3. Show generated passwords");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void handlePasswordCheck(Scanner sc) {
        System.out.print("Enter password to check: ");
        String pwd = sc.next();

        if (!checkPassword(pwd)) {
            System.out.println("Password does NOT meet minimum requirements (uppercase, lowercase, digit, symbol).");
            return;
        }

        double entropy = calculateEntropy(pwd);
        System.out.println("Password meets all character requirements.");
        System.out.println("Entropy: " + entropy + " bits");
        if (entropy < 40) System.out.println("Strength: Weak");
        else if (entropy < 60) System.out.println("Strength: Medium");
        else System.out.println("Strength: Strong");
    }

    public static void handlePasswordGeneration(Scanner sc, ArrayList<String> passwords) {
        int count = 0, length = 0;

        while (true) {
            System.out.print("How many passwords to generate? ");
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // consume invalid input
            }
        }

        while (true) {
            System.out.print("Password length: ");
            if (sc.hasNextInt()) {
                length = sc.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
        }

        if (length < 4) {
            System.out.println("Warning: Password length too short to include all types. Setting length to 8.");
            length = 8;
        }

        for (int i = 0; i < count; i++) {
            String pwd = generatePassword(length);
            passwords.add(pwd);
            double entropy = calculateEntropy(pwd);
            System.out.print("Generated password: " + pwd + " | Strength: ");
            if (entropy < 40) System.out.println("Weak");
            else if (entropy < 60) System.out.println("Medium");
            else System.out.println("Strong");
        }
    }

    public static void displayGeneratedPasswords(ArrayList<String> passwords) {
        System.out.println("\n=== Generated Passwords ===");
        for (int i = 0; i < passwords.size(); i++) {
            System.out.println((i+1) + ": " + passwords.get(i));
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ArrayList<String> passwords = new ArrayList<>();
            int choice = 0;

            do {
                displayMenu();
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    sc.next(); 
                    continue;
                }

                switch (choice) {
                    case 1 -> handlePasswordCheck(sc);
                    case 2 -> handlePasswordGeneration(sc, passwords);
                    case 3 -> displayGeneratedPasswords(passwords);
                    case 4 -> System.out.println("Exiting");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 4);
        }
    }
}