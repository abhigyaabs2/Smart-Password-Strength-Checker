import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Scanner;

enum PasswordStrength {
    WEAK("WEAK"),
    MEDIUM("MEDIUM"),
    STRONG("STRONG");

    private final String level;

    PasswordStrength(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}

class PasswordChecker {
    private static final int MIN_LENGTH_WEAK = 6;
    private static final int MIN_LENGTH_MEDIUM = 8;
    private static final int MIN_LENGTH_STRONG = 12;

    private static final Pattern UPPER_CASE = Pattern.compile("[A-Z]");
    private static final Pattern LOWER_CASE = Pattern.compile("[a-z]");
    private static final Pattern DIGIT = Pattern.compile("[0-9]");
    private static final Pattern SPECIAL_CHAR = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]");

    public PasswordStrength checkStrength(String password) {
        if (password == null || password.isEmpty()) {
            return PasswordStrength.WEAK;
        }

        int score = calculateScore(password);

        if (score >= 5) {
            return PasswordStrength.STRONG;
        } else if (score >= 3) {
            return PasswordStrength.MEDIUM;
        } else {
            return PasswordStrength.WEAK;
        }
    }

    private int calculateScore(String password) {
        int score = 0;

        if (password.length() >= MIN_LENGTH_STRONG) {
            score += 2;
        } else if (password.length() >= MIN_LENGTH_MEDIUM) {
            score += 1;
        }

        score += Stream.of(UPPER_CASE, LOWER_CASE, DIGIT, SPECIAL_CHAR)
                .filter(pattern -> pattern.matcher(password).find())
                .count();

        return (int) score;
    }

    public void displayAnalysis(String password) {
        System.out.println("\nPassword Analysis:");
        System.out.println("-------------------------");
        System.out.println("Length: " + password.length() + " characters " +
                (password.length() >= MIN_LENGTH_STRONG ? "[PASS]" :
                        password.length() >= MIN_LENGTH_MEDIUM ? "[WARN]" : "[FAIL]"));
        System.out.println("Uppercase: " + (UPPER_CASE.matcher(password).find() ? "[PASS]" : "[FAIL]"));
        System.out.println("Lowercase: " + (LOWER_CASE.matcher(password).find() ? "[PASS]" : "[FAIL]"));
        System.out.println("Digits: " + (DIGIT.matcher(password).find() ? "[PASS]" : "[FAIL]"));
        System.out.println("Special characters: " + (SPECIAL_CHAR.matcher(password).find() ? "[PASS]" : "[FAIL]"));

        PasswordStrength strength = checkStrength(password);
        System.out.println("\nStrength: " + strength.getLevel());
        System.out.println("-------------------------");
    }
}

class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()_+-=[]{}|;:,.<>?";
    private static final String ALL_CHARS = UPPER + LOWER + DIGITS + SPECIAL;

    private final SecureRandom random = new SecureRandom();

    public String generatePassword(int length) {
        if (length < 8) {
            length = 12;
        }

        StringBuilder password = new StringBuilder(length);

        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        String remaining = IntStream.range(0, length - 4)
                .mapToObj(i -> String.valueOf(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length()))))
                .collect(Collectors.joining());

        password.append(remaining);

        return shuffleString(password.toString());
    }

    private String shuffleString(String input) {
        char[] chars = input.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }
}

public class PasswordStrengthChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PasswordChecker checker = new PasswordChecker();
        PasswordGenerator generator = new PasswordGenerator();

        System.out.println("Smart Password Strength Checker + Generator");
        System.out.println("===========================================");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Check password strength");
            System.out.println("2. Generate strong password");
            System.out.println("3. Exit");
            System.out.print("\nChoose option (1-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("\nEnter password to check: ");
                    String password = scanner.nextLine();
                    checker.displayAnalysis(password);
                    break;

                case "2":
                    System.out.print("\nEnter password length (min 8, recommended 12+): ");
                    try {
                        int length = Integer.parseInt(scanner.nextLine().trim());
                        String generatedPassword = generator.generatePassword(length);
                        System.out.println("\nGenerated Password: " + generatedPassword);
                        checker.displayAnalysis(generatedPassword);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid length. Using default (12).");
                        String generatedPassword = generator.generatePassword(12);
                        System.out.println("\nGenerated Password: " + generatedPassword);
                        checker.displayAnalysis(generatedPassword);
                    }
                    break;

                case "3":
                    System.out.println("\nGoodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose 1-3.");
            }
        }
    }
}
