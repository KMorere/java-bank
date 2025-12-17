public class Person {
    private String firstName;
    private String lastName;
    private Account account;

    public Person(String _firstName, String _lastName) {
        this.firstName = _firstName;
        this.lastName = _lastName;
    }

    public String getFirstName() { return this.firstName; }
    public String getLastName() { return this.lastName; }
    public String getFullName() { return this.getFirstName() + " " + this.getLastName(); }

    /**
     * Return the account of a Person, otherwise return null.
     */
    public Account getAccount() {
        if (this.account != null)
            return account;
        else
            return null;
    }

    public Account createAccount(String _number) throws RuntimeException {
        if (getAccount() == null) {
            this.account = new Account(_number, this);
        }
        else {
            throw new RuntimeException("An account already exists for " + this.getFullName() + ".");
        }

        return this.getAccount();
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
