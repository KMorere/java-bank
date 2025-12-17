import java.util.logging.Logger;

/**
 * The account of a Person with a unique number and a balance.
 */
public class Account {
    private String accountNumber; // Account number in 'FR-XXXX-XXXX' format.
    private float balance;
    private Person holder;

    private static final Logger logger = CustomLogger.getInstance(Logger.getLogger(Account.class.getName())).logger;

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

    public boolean transferMoney(Account _account, float _amount) {
        if (_amount < 0)
            throw new RuntimeException("The amount to transfer must be positive !");
        float startbalance = this.getAccountBalance();
        float destbalance = _account.getAccountBalance();

        _account.setAccountBalance(_amount);
        this.setAccountBalance(-_amount);

        String msg = String.format("Successfully transfered %s to %s.", _amount, _account.getHolder());
        logger.info(msg);

        return (this.getAccountBalance() == startbalance - _amount &&
                _account.getAccountBalance() == destbalance + _amount);
    }

    public boolean depositMoney(float _amount) {
        if (_amount < 0)
            throw new RuntimeException("The amount to transfer must be positive !");
        this.setAccountBalance(_amount);

        String msg = String.format("Successfully deposited %s to the account.", _amount);
        logger.info(msg);

        return true;
    }

    public boolean takeoutMoney(float _amount) {
        if (_amount < 0)
            throw new RuntimeException("The amount to transfer must be positive !");
        this.setAccountBalance(-_amount);

        String msg = String.format("Successfully took out %s from the account.", _amount);
        logger.info(msg);

        return true;
    }

    @Override
    public String toString() {
        return ("Account : " + this.getAccountNumber() + " :\n"+
                "\t- Holder : " + this.getHolder() + ",\n"+
                "\t- Balance : " + this.getAccountBalance() + "\n");
    }
}
