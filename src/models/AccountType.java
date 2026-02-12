package models;

import custom.InvalidAccountTypeException;

public enum AccountType {
    CHECKING("Checking"),
    SAVING("Saving");

    private final String label;

    AccountType(String _label) { this.label = _label; }
    public String getLabel() { return label; }

    /**
     * Converts a string into type 'AccountType',
     * throws 'InvalidAccountTypeException' if the type isn't recognized.
     * @param _label String to convert.
     * @return The associated type from 'AcountType'.
     */
    public static AccountType fromLabel(String _label) {
        for (AccountType t : values())
            if (t.label.equalsIgnoreCase(_label))
                return t;
        throw new InvalidAccountTypeException();
    }
}
