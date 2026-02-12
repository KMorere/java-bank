package models;

import custom.*;
import utils.AccountNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bank {
    private String name;

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
    public String createAccount(Person _person, AccountType _type) throws AccountAlreadyExistsException {
        if (_person.getAccount() == null) { // TODO: Change the exception condition to the accountNumber.
            Account newAccount = new AccountFactory().createAccount(_type, 0);
            newAccount.setAccountNumber(AccountNumber.GetInstance().generateAccountNumber());
            newAccount.setHolder(_person);
            newAccount.setBank(this);
            _person.setAccount(newAccount);
        }
        else {
            throw new AccountAlreadyExistsException("An account already exists for " + _person.getFullName() + ".");
        }

        return AccountNumber.GetInstance().generateAccountNumber();
    }

    /**
     * Get the account from an account number.
     * @param _accountNumber The account number used.
     * @return Returns the account.
     */
    public static String getAccount(String _accountNumber) {
        Pattern pattern = Pattern.compile(AccountNumber.REGEX);
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
