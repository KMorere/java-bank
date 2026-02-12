package models;

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

    @Override
    public String toString() {
        return getFullName();
    }
}
