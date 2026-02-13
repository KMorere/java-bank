package models;

import custom.*;
import utils.AccountNumber;

import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * The account of a models.Person with a unique number and a balance.
 */
public abstract class Account {
    private int id;
    private String accountNumber; // models.Account number in 'FR-XXXX-XXXX' format.
    private float balance;
    private Client holder;
    private Bank bank;
    private int id_bank;
    private AccountType accountType;

    private static final Logger logger = CustomLogger.getInstance(Logger.getLogger(Account.class.getName())).logger;

    public Account(int _id, String _number, float _balance, int _id_bank) {
        this.id = _id;
        this.accountNumber = _number;
        this.balance = _balance;
        this.id_bank = _id_bank;
    }

    public Account(String _number, Client _holder) {
        this.setAccountNumber(_number);
        this.setHolder(_holder);
    }

    public Account(float _balance, int _id_bank) {
        this.accountNumber = AccountNumber.GetInstance().generateAccountNumber();
        this.balance = _balance;
        this.id_bank = _id_bank;
    }

//region Get & Set
    public int getAccountID() { return this.id; }
    public void setAccountID(int _id) { this.id = _id; }

    public String getAccountNumber() { return this.accountNumber; }
    public void setAccountNumber(String _number) { this.accountNumber = _number; }

    public float getAccountBalance() { return this.balance; }
    public void setAccountBalance(float _amount) { this.balance = _amount; }

    public Client getHolder() { return this.holder; }
    public void setHolder(Client _holder) { this.holder = _holder; }

    public Bank getBank() { return this.bank; }
    public int getBankID() { return this.id_bank; }
    public void setBank(Bank _bank) {this.bank = _bank; }
    public void setBankID(int _id) {this.id_bank = _id; }

    public AccountType getAccountType() { return this.accountType; }
    public void setAccountType(AccountType _type) { this.accountType = _type; }
//endregion

    public void updateAccountBalance(float _amount) { this.balance += _amount; }

    /**
     * Transfer '_amount' from this account to another.
     * @param _account The account receiving the transfer.
     * @param _amount The amount of funds to transfer.
     * @return Return true if the action was successful.
     * @throws InsufficientBalanceException Throws exception if the balance of the account is too low.
     * @throws AccountDoesNotExistException Throws exception if _account does not exist.
     */
    public boolean transferMoney(Account _account, float _amount)
            throws InsufficientBalanceException, AccountDoesNotExistException {
        if (_amount < 0)
            throw new RuntimeException("The amount to transfer must be positive !");
        if (this.balance < _amount)
            throw new InsufficientBalanceException();
        if (_account == null)
            throw new AccountDoesNotExistException();
        float startbalance = this.getAccountBalance();
        float destbalance = _account.getAccountBalance();

        _account.updateAccountBalance(_amount);
        this.updateAccountBalance(-_amount);

        String msg = String.format("Successfully transfered %s to %s.", _amount, _account.getHolder());
        logger.info(msg);

        if (this.getAccountBalance() == startbalance - _amount &&
                _account.getAccountBalance() == destbalance + _amount) {
            /*Operation op = new Operation(
                    0, this.getAccountID(), "TRANSFER", _amount, LocalDateTime.now().toString()
            );

            System.out.println(op);*/

            return true;
        }
        return false;
    }

    /**
     * Deposit '_amount' to this account.
     * @param _amount The amount of funds to add.
     * @return Return true if the action was successful.
     */
    public boolean depositMoney(float _amount) {
        if (_amount < 0)
            throw new RuntimeException("The amount to transfer must be positive !");
        this.updateAccountBalance(_amount);

        String msg = String.format("Successfully deposited %s to the account.", _amount);
        logger.info(msg);

        Operation op = new Operation(
                0, this.getAccountID(), "DEPOSIT", _amount, LocalDateTime.now().toString()
        );

        System.out.println(op);

        return true;
    }

    /**
     * Withdraw '_amount' from this account.
     * @param _amount The amount of funds to withdraw.
     * @return Return true if the action was successful.
     * @throws InsufficientBalanceException Throws exception if the balance of the account is too low.
     */
    public boolean withdrawMoney(float _amount) throws InsufficientBalanceException {
        if (_amount < 0)
            throw new RuntimeException("The amount to transfer must be positive !");
        if (this.balance < _amount)
            throw new InsufficientBalanceException();
        this.updateAccountBalance(-_amount);

        String msg = String.format("Successfully took out %s from the account.", _amount);
        logger.info(msg);

        Operation op = new Operation(
                0, this.getAccountID(), "WITHDRAW", -_amount, LocalDateTime.now().toString()
        );

        System.out.println(op);

        return true;
    }

    public String displayAccount() {
        return String.format("%s\n%s\n%s\n%s\n",
                getAccountNumber(),
                getAccountBalance(),
                getHolder(),
                getBank().getName());
    }

    @Override
    public String toString() {
        return ("Account : " + this.getAccountNumber() + " :\n"+
                "\t- Holder : " + this.getHolder() + ",\n"+
                "\t- Balance : " + this.getAccountBalance() + "\n");
    }
}
