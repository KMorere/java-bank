package custom;

public class AccountAlreadyExistsException extends Exception {
    public AccountAlreadyExistsException() {
        super("An account already exists for this account.");
    }

    public AccountAlreadyExistsException(String msg) {
        super(msg);
    }
}