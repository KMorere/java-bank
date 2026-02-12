package daos;

import utils.DatabaseConnection;
import models.*;
import utils.SqlQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class AccountDao extends Dao<Account> {
    @Override
    public int create(Account obj) {
        int id_account = 0;
        String query = new SqlQuery.Builder()
                .table("account")
                .insert(new String[] {"id_account", "account_type", "account_number", "account_balance", "id_bank"},
                        new String[] {"NULL", "?", "?", "?", "?"})
                .build(SqlQuery.QueryType.INSERT);

        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            record.setString(1, obj.getAccountType().getLabel());
            record.setString(2, obj.getAccountNumber());
            record.setFloat(3, obj.getAccountBalance());
            record.setInt(4, obj.getBankID());

            try (ResultSet set = record.getGeneratedKeys()) {
                if (set.next())
                    id_account = set.getInt(1);
            }

            if (record.executeUpdate() > 0)
                System.out.println("Insertion at id : " + id_account);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id_account;
    }

    @Override
    public Account read(int id) {
        Account account = null;
        String query = new SqlQuery.Builder()
                .select("*")
                .table("account")
                .join("account_client", "account.id_account", "account_client.id_account")
                .join("client", "account_client.id_client", "client.id_client")
                .join("bank", "account.id_bank", "bank.id_bank")
                .filter("account.id_account = ?")
                .build(SqlQuery.QueryType.SELECT);

        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    account = new AccountFactory().createAccount(
                        AccountType.fromLabel(set.getString("account_type")),
                            0,
                            set.getInt("id_bank")
                    );
                    account.setAccountID(set.getInt("id_account"));
                    account.setAccountNumber(set.getString("account_number"));
                    account.setAccountBalance(set.getFloat("account_balance"));

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
        ArrayList<Account> accounts = new ArrayList<>();
        String query = new SqlQuery.Builder()
                .select("*")
                .table("account")
                .join("account_client", "account.id_account", "account_client.id_account")
                .join("client", "account_client.id_client", "client.id_client")
                .join("bank", "account.id_bank", "bank.id_bank")
                .build(SqlQuery.QueryType.SELECT);

        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query)) {

            try (ResultSet set = record.executeQuery()) {
                while (set.next()) {
                    Account account = null;
                    account = new AccountFactory().createAccount(
                            AccountType.fromLabel(set.getString("account_type")),
                            0,
                            set.getInt("id_bank")
                    );
                    account.setAccountID(set.getInt("id_account"));
                    account.setAccountNumber(set.getString("account_number"));
                    account.setAccountBalance(set.getFloat("account_balance"));

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

                    accounts.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts.toArray(new Account[0]);
    }

    public String getAccountNumber(int _id) {
        String accountNumber = "";
        String query = new SqlQuery.Builder()
                .select("account.account_number")
                .table("account")
                .filter("account.id_account = ?")
                .build(SqlQuery.QueryType.SELECT);

        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, _id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    accountNumber = set.getString("account_number");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountNumber;
    }

    public String[] getAccountNumbers() {
        ArrayList<String> accountNumbers = new ArrayList<>();
        String query = new SqlQuery.Builder()
                .select("account.account_number")
                .table("account")
                .build(SqlQuery.QueryType.SELECT);

        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query)) {

            try (ResultSet set = record.executeQuery()) {
                while (set.next()) {
                    accountNumbers.add(set.getString("account_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountNumbers.toArray(new String[0]);
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
