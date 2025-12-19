package custom;

public class AccountDoesNotExistException extends Exception {
    public AccountDoesNotExistException() {
        super("The referenced account does not exist.");
    }

    public AccountDoesNotExistException(String msg) {
        super(msg);
    }
}
