package models;

public class CheckingAccount extends Account {
    public CheckingAccount(float _balance, int _id_bank) {
        super(_balance, _id_bank);
        this.setAccountType(AccountType.CHECKING);
    }
}
