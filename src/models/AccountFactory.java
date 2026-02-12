package models;

import custom.InvalidAccountTypeException;

public class AccountFactory {
    public Account createAccount(AccountType _type, float _balance, int _id_bank) {
        switch(_type) {
            case CHECKING:
                return new CheckingAccount(_balance, _id_bank);
            case SAVING:
                return new SavingAccount(_balance, 0, _id_bank);
            default:
                throw new InvalidAccountTypeException("Invalid account type : " + _type);
        }
    }
}
