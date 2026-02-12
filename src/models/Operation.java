package models;

import daos.AccountDao;
import daos.OperationDao;

public class Operation {
    private int id;
    private int accountID;
    private String type;
    private float amount;
    private String date;

    // Constructeur
    public Operation(int _id, int _id_account, String _type, float _amount, String _date ) {
        this.id = _id;
        this.accountID = _id_account;
        this.type = _type;
        this.amount = _amount;
        this.date = _date;

        new OperationDao().create(this);
        Account acc = new AccountDao().read(_id_account);

        new AccountDao().updateBalance(_id_account, (acc.getAccountBalance()+_amount));
    }

    public int getAccountID() { return this.accountID; }
    public String getAccountType() { return this.type; }
    public float getAccountAmount() { return this.amount; }
    public String getDate() { return this.date; }

    // Une méthode pour afficher joliment l'opération
    @Override
    public String toString() {
        return id + " | " + accountID + " | " + type + " | " + amount + "€" + " | " + date;
    }
}
