package daos;

import utils.DatabaseConnection;
import models.*;
import utils.SqlQuery;

import java.sql.*;
import java.util.Map;

public class OperationDao extends Dao<Operation> {
    @Override
    public int create(Operation obj) {
        int id_operation = 0;
        String query = new SqlQuery.Builder()
                .table("operation")
                .insert(new String[] {"id_operation", "operation_type", "operation_amount", "operation_date", "id_account"},
                        new String[] {"NULL", "?", "?", "?", "?"})
                .build(SqlQuery.QueryType.INSERT);

        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            record.setString(1, obj.getAccountType());
            record.setFloat(2, obj.getAccountAmount());
            record.setString(3, obj.getDate());
            record.setInt(4, obj.getAccountID());

            try (ResultSet set = record.getGeneratedKeys()) {
                if (set.next())
                    id_operation = set.getInt(1);
            }

            if (record.executeUpdate() > 0)
                System.out.println("Insertion at id : " + id_operation);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id_operation;
    }

    @Override
    public Operation read(int id) {
        Operation operation = null;

        String query = new SqlQuery.Builder()
                .select("*")
                .table("operation")
                .join("account", "operation.id_account", "account.id_account")
                .filter("operation.id_operation = ?")
                .build(SqlQuery.QueryType.SELECT);

        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    operation = new Operation(
                            set.getInt("id_operation"),
                            set.getInt("id_account"),
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
