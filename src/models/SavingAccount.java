package models;

public class SavingAccount extends Account {
    private float interest;

    public SavingAccount(float _balance, float _rate, int _id_bank) {
        super(_balance, _id_bank);
        this.setAccountType(AccountType.SAVING);
        this.interest = _rate;
    }

    public float getInterest() { return this.interest; }
}
