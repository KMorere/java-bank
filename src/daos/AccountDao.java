package daos;

import com.sun.security.ntlm.Client;
import models.*;

import java.sql.*;
import java.util.Map;

public class AccountDao extends Dao<Account> {
    @Override
    public int create(Account obj) {
        int id_account = 0;
        String query = "INSERT INTO account (id_bank, account_number, account_balance, bank_fk) VALUES (NULL, ?, ?, ?)";
        try (Connection connection = connection();
             PreparedStatement record = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            record.setString(1, obj.getAccountNumber());
            record.setFloat(2, obj.getAccountBalance());
            record.setInt(3, obj.getBankID());

            id_account = record.executeUpdate();

            if (id_account > 0)
                System.out.println("Insertion at id : " + id_account);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return id_account;
    }

    @Override
    public Account read(int id) {
        Account account = null;
        String query = "SELECT * FROM account " +
                "LEFT JOIN account_client ON account.id_account = account_client.id_account " +
                "LEFT JOIN client ON account_client.id_client = client.id_client " +
                "LEFT JOIN bank " +
                "ON account.id_bank = bank.id_bank " +
                "WHERE account.id_account = ?";

        try (Connection connection = connection();
        PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    account = new Account(
                            set.getInt("id_account"),
                            set.getString("account_number"),
                            set.getFloat("account_balance"),
                            set.getInt("id_bank")
                    );

                    if (set.getInt("id_client") > 0) {
                        account.setHolder(new Person(
                                set.getString("first_name"),
                                set.getString("last_Name")
                        ));
                    }

                    if (set.getInt("id_bank") > 0) {
                        account.setBank(new Bank(
                                set.getString("bank_name")
                        ));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Account[] readAll() {
        return new Account[0];
    }

    @Override
    public boolean update(Account obj, Map<String, Object> key) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
