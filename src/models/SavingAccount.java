package models;

public class SavingAccount extends Account {
    private float interest;

    public SavingAccount(float _balance, float _rate) {
        super(_balance);
        this.interest = _rate;
    }

    public float getInterest() { return this.interest; }
}
