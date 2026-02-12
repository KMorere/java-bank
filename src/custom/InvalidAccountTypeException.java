package custom;

public class InvalidAccountTypeException extends RuntimeException {
    public InvalidAccountTypeException() {
        super("Invalid account type.");
    }

    public InvalidAccountTypeException(String msg) {
        super(msg);
    }
}
