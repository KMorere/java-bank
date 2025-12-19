package custom;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("The balance of the account is too low for this operation.");
    }

    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}
