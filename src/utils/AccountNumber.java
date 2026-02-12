package utils;

import java.util.Random;

public final class AccountNumber {
    private static AccountNumber instance;

    public static synchronized AccountNumber GetInstance() {
        if (instance == null) instance = new AccountNumber();
        return instance;
    }

    private static final String ACCOUNT_NUMBER_FORMAT = "FR-XXXX-XXXX";
    private static final Random rnd = new Random();
    public static final String REGEX = "[A-Z]{2}-\\d{4}-\\d{4}";

    /**
     * Generate a new account number using 'accountNumberFormat'.
     * @return Returns the generated account number.
     */
    public String generateAccountNumber() {
        StringBuilder newNumber = new StringBuilder(ACCOUNT_NUMBER_FORMAT);

        for (int i = 0; i < ACCOUNT_NUMBER_FORMAT.length(); i++) {
            if (newNumber.charAt(i) == 'X') {
                int rndInt = rnd.nextInt(10);
                newNumber.setCharAt(i, Character.forDigit(rndInt, 10));
            }
        }

        return newNumber.toString();
    }
}
