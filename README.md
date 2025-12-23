# Smart Password Strength Checker + Generator

A Java-based console application that analyzes password strength and generates cryptographically secure passwords using modern Java features.

## Features

- **Password Strength Analysis**: Comprehensive evaluation based on multiple criteria
- **Intelligent Scoring System**: Rates passwords as WEAK, MEDIUM, or STRONG
- **Secure Password Generation**: Creates strong passwords using `SecureRandom`
- **Real-time Feedback**: Instant analysis of password components

## Technology Stack

- Java (Core)
- Regular Expressions (Regex)
- Java Streams API
- SecureRandom for cryptographic strength
- Enums for type safety

## Evaluation Criteria

The system analyzes passwords based on:

1. **Length**
   - 12+ characters: Strong
   - 8-11 characters: Medium
   - Below 8: Weak

2. **Character Variety**
   - Uppercase letters (A-Z)
   - Lowercase letters (a-z)
   - Digits (0-9)
   - Special characters (!@#$%^&* etc.)

## Scoring Algorithm

| Criteria | Points |
|----------|--------|
| Length ≥ 12 | +2 |
| Length ≥ 8 | +1 |
| Contains Uppercase | +1 |
| Contains Lowercase | +1 |
| Contains Digits | +1 |
| Contains Special Chars | +1 |

**Strength Rating:**
- Score ≥ 5: STRONG
- Score 3-4: MEDIUM
- Score < 3: WEAK

## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA (or any Java IDE)

### Steps

1. Clone or download the project
2. Open IntelliJ IDEA
3. Create a new Java project
4. Copy `PasswordStrengthChecker.java` to the `src` folder
5. Run the file
6. Follow the on-screen menu

## Usage Examples

### Check Password Strength
```
Choose option (1-3): 1
Enter password to check: MyPass123!

Password Analysis:
-------------------------
Length: 10 characters [WARN]
Uppercase: [PASS]
Lowercase: [PASS]
Digits: [PASS]
Special characters: [PASS]

Strength: MEDIUM
-------------------------
```

### Generate Strong Password
```
Choose option (1-3): 2
Enter password length (min 8, recommended 12+): 16

Generated Password: K9#mPx2@qL5$nR8v

Password Analysis:
-------------------------
Length: 16 characters [PASS]
Uppercase: [PASS]
Lowercase: [PASS]
Digits: [PASS]
Special characters: [PASS]

Strength: STRONG
-------------------------
```

## Key Concepts Demonstrated

- **Enum Usage**: Type-safe password strength levels
- **Regular Expressions**: Pattern matching for character validation
- **Stream API**: Functional programming for scoring calculation
- **SecureRandom**: Cryptographically strong random generation
- **Object-Oriented Design**: Separation of concerns with dedicated classes

## Why This Project?

- **Security-Focused**: Addresses real-world cybersecurity needs
- **Modern Java**: Demonstrates contemporary programming practices
- **Clean Architecture**: Well-structured, maintainable code
- **Non-CRUD**: Shows algorithmic thinking beyond database operations

## Future Enhancements

- [ ] Password history tracking
- [ ] Dictionary word detection
- [ ] Common password blacklist
- [ ] Export passwords securely
- [ ] GUI interface

## License

This project is open source and available for educational purposes.
