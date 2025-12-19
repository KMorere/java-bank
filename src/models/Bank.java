package models;

import custom.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bank {
    private String name;

    private static final String ACCOUNT_NUMBER_FORMAT = "FR-XXXX-XXXX";
    private static final Random rnd = new Random();
    private static final String REGEX = "[A-Z]{2}-\\d{4}-\\d{4}";

    public Bank(String _name) {
        this.setName(_name);
    }

    public String getName() { return this.name; }
    public void setName(String _name) { this.name = _name; }

    /**
     * Create an account for '_person' with an account number.
     * @param _person The person creating an account.
     * @return Returns the account's number.
     * @throws AccountAlreadyExistsException Throws an exception if an account exists for this user or number.
     */
    public String createAccount(Person _person) throws AccountAlreadyExistsException {
        if (_person.getAccount() == null) { // TODO: Change the exception condition to the accountNumber.
            _person.createAccount(generateAccountNumber());
        }
        else {
            throw new AccountAlreadyExistsException("An account already exists for " + _person.getFullName() + ".");
        }

        return generateAccountNumber();
    }

    /**
     * Generate a new account number using 'accountNumberFormat'.
     * @return Returns the generated account number.
     */
    public String generateAccountNumber() { // TODO: Save associated numbers so they are unique.
        StringBuilder newNumber = new StringBuilder(ACCOUNT_NUMBER_FORMAT);

        for (int i = 0; i < ACCOUNT_NUMBER_FORMAT.length(); i++) {
            if (newNumber.charAt(i) == 'X') {
                int rndInt = rnd.nextInt(10);
                newNumber.setCharAt(i, Character.forDigit(rndInt, 10));
            }
        }

        return newNumber.toString();
    }

    /**
     * Get the account from an account number.
     * @param _accountNumber The account number used.
     * @return Returns the account.
     */
    public static String getAccount(String _accountNumber) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(_accountNumber);

        if (matcher.find()) {
            // TODO: Get the account.
            System.out.println("models.Account exists");
            return _accountNumber;
        }
        else {
            System.out.println("models.Account does not exist");
        }

        return null;
    }

    @Override
    public String toString() {
        return "models.Bank : " + getName();
    }
}
