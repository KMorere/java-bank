package models;

import custom.InvalidAccountTypeException;

public class AccountFactory {
    public Account createAccount(String _type, float _balance) {
        if (_type.equalsIgnoreCase("CHECKING")) {
            return new CheckingAccount();
        } else if (_type.equalsIgnoreCase("SAVING")) {
            return new SavingAccount();
        } else {
            throw new InvalidAccountTypeException("Invalid account type : " + _type);
        }
    }
}
