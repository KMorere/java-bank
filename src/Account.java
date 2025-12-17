/**
 * The account of a Person with a unique number and a balance.
 */
public class Account {
    private String accountNumber; // Account number in 'FR-XXXX-XXXX' format.
    private float balance;
    private Person holder;

    public Account(String _number, Person _holder) {
        this.setAccountNumber(_number);
        this.setHolder(_holder);
    }

    public String getAccountNumber() { return this.accountNumber; }
    public void setAccountNumber(String _number) { this.accountNumber = _number; }

    public float getAccountBalance() { return this.balance; }
    private void setAccountBalance(float _amount) { this.balance += _amount; }

    public Person getHolder() { return this.holder; }
    private void setHolder(Person _holder) { this.holder = _holder; }

    public void transferMoney(Account _account, float _amount) {
        if (_amount < 0)
            throw new RuntimeException("The amount to transfer must be positive !");
        _account.setAccountBalance(_amount);
        this.setAccountBalance(-_amount);
    }
}
