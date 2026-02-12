package daos;

import utils.DatabaseConnection;
import models.*;

import java.sql.*;
import java.util.Map;

public class AccountDao extends Dao<Account> {
    @Override
    public int create(Account obj, String query) {
        int id_account = 0;
        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            record.setString(1, obj.getAccountNumber());
            record.setFloat(2, obj.getAccountBalance());
            record.setInt(3, obj.getBankID());

            id_account = record.executeUpdate();

            if (id_account > 0)
                System.out.println("Insertion at id : " + id_account);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id_account;
    }

    @Override
    public Account read(int id, String query) {
        Account account = null;

        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    account = new AccountFactory().createAccount(
                        AccountType.fromLabel(set.getString("account_type")), 0
                    );
                    account.setAccountID(set.getInt("id_account"));
                    account.setAccountNumber(set.getString("account_number"));
                    account.setAccountBalance(set.getFloat("account_balance"));
                    account.setBankID(set.getInt("id_bank"));

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
        } catch (SQLException e) {
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
