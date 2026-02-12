package models;

import custom.InvalidAccountTypeException;

public class AccountFactory {
    public Account createAccount(AccountType _type, float _balance) {
        switch(_type) {
            case CHECKING:
                return new CheckingAccount(_balance);
            case SAVING:
                return new SavingAccount(_balance, 0);
            default:
                throw new InvalidAccountTypeException("Invalid account type : " + _type);
        }
    }
}
