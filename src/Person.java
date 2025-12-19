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
    public Account getAccount() { return account; }
    public void setAccount(Account _account) { this.account = _account; }

    /**
     * Initial account creation application, validated to the bank.
     * @param _number The account number of the account to create.
     * @return Return 'accountNumber' of the newly created account.
     * @throws RuntimeException Throws an Exception if the account already exists.
     */
    public String createAccount(String _number) throws RuntimeException {
        if (getAccount() == null) {
            this.account = new Account(_number, this);
        }
        else {
            throw new RuntimeException("An account already exists for " + this.getFullName() + ".");
        }

        return _number;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
