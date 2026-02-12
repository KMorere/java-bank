package daos;

import utils.DatabaseConnection;
import models.*;

import java.sql.*;
import java.util.Map;

public class OperationDao extends Dao<Operation> {
    @Override
    public int create(Operation obj, String query) {
        return 0;
    }

    @Override
    public Operation read(int id, String query) {
        Operation operation = null;

        try (Connection connection = DatabaseConnection.GetInstance().getConnection();
             PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    operation = new Operation(
                            set.getInt("id_operation"),
                            set.getString("id_account"),
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
