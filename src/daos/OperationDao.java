package daos;

import utils.DatabaseConnection;
import models.*;
import utils.SqlQuery;

import java.sql.*;
import java.util.Map;

public class OperationDao extends Dao<Operation> {
    @Override
    public int create(Operation obj) {
        return 0;
    }

    @Override
    public Operation read(int id) {
        Operation operation = null;

        String query = new SqlQuery.Builder()
                .select("*")
                .table("operation")
                .join("account", "operation.id_account", "account.id_account")
                .filter("operation.id_operation = ?")
                .build();

        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    operation = new Operation(
                            set.getInt("id_operation"),
                            new AccountDao().read(set.getInt("id_account")).getAccountNumber(),
                            set.getString("operation_type"),
                            set.getFloat("operation_amount"),
                            set.getString("operation_date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operation;
    }

    @Override
    public Operation[] readAll() {
        return new Operation[0];
    }

    @Override
    public boolean update(Operation obj, Map<String, Object> key) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
