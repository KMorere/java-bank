package models;

public class Operation {
    private int id;
    private String accountNumber;
    private String type;
    private double amount;
    private String date;

    // Constructeur
    public Operation(int _id, String _account, String _type, double _amount, String _date ) {
        this.id = _id;
        this.accountNumber = _account;
        this.type = _type;
        this.amount = _amount;
        this.date = _date;
    }

    // Une méthode pour afficher joliment l'opération
    @Override
    public String toString() {
        return id + " | " + accountNumber + " | " + type + " | " + amount + "€" + " | " + date;
    }
}
