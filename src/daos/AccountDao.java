package daos;

import models.Account;

import java.util.Map;

public class AccountDao extends Dao<Account> {
    @Override
    public int create(Account obj) {
        return 0;
    }

    @Override
    public Account read(int id) {
        return null;
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
