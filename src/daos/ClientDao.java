package daos;

import models.Client;
import utils.DatabaseConnection;
import utils.SqlQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ClientDao extends Dao<Client> {
    @Override
    public int create(Client obj) {
        return 0;
    }

    @Override
    public Client read(int id) {
        Client client = null;
        String query = new SqlQuery.Builder()
                .select("*")
                .table("client")
                .join("account_client", "client.id_client", "account_client.id_client")
                .filter("client.id_client = ?")
                .build(SqlQuery.QueryType.SELECT);

        try(Connection connection = DatabaseConnection.GetInstance().getConnection();
            PreparedStatement record = connection.prepareStatement(query)) {
            record.setInt(1, id);

            try (ResultSet set = record.executeQuery()) {
                if (set.next()) {
                    client = new Client(set.getString("first_name"),
                            set.getString("last_name"));
                    client.setAccount(new AccountDao().read(set.getInt("id_account")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public Client[] readAll() {
        ArrayList<Client> clients = new ArrayList<>();
        String query = new SqlQuery.Builder()
                .select("*")
                .table("client")
                .join("account_client", "client.id_client", "account_client.id_client")
                .build(SqlQuery.QueryType.SELECT);

        try(Connection connection = DatabaseConnection.GetInstance().getConnection();
            PreparedStatement record = connection.prepareStatement(query)) {

            try (ResultSet set = record.executeQuery()) {
                while (set.next()) {
                    Client client = null;
                    client = new Client(set.getString("first_name"),
                            set.getString("last_name"));
                    client.setAccount(new AccountDao().read(set.getInt("id_account")));

                    clients.add(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients.toArray(new Client[0]);
    }

    @Override
    public boolean update(Client obj, Map<String, Object> key) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
