import custom.*;
import models.Account;
import models.Bank;
import models.Person;
import daos.AccountDao;
import database.SqlQuery;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        String sql = new SqlQuery.Builder()
                .select("*")
                .table("account")
                .join("account_client", "account.id_account", "account_client.id_account")
                .join("client", "account_client.id_client", "client.id_client")
                .join("bank", "account.id_bank", "bank.id_bank")
                .filter("account.id_account = ?")
                .build();

        Account accountDao = new AccountDao().read(1, sql);
        System.out.println(accountDao.displayAccount());
    }

    private static void test_init() {
        Logger logger = CustomLogger.getInstance(Logger.getLogger(Main.class.getName())).logger;

        Bank newBank = new Bank("Banque impopulaire");

        logger.info("Creating models.Person 1 and 2...");
        Person npc1 = new Person("Jackie", "Chène");
        Person npc2 = new Person("Jacques", "Ièsse");

        logger.info("Creating models.Account for person 1 and 2.");
        try {
            newBank.createAccount(npc1);
            newBank.createAccount(npc2);

            Account acc1 = npc1.getAccount();
            Account acc2 = npc2.getAccount();

            System.out.println(acc1 + "\n" + acc2);

            acc1.depositMoney(250);
            acc1.transferMoney(acc2, 100);
            acc2.withdrawMoney(50);

            System.out.println(acc1 + "\n" + acc2);

            System.out.println(newBank.generateAccountNumber());
        } catch (AccountAlreadyExistsException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (AccountDoesNotExistException e) {
            e.printStackTrace();
            throw new RuntimeException("The account does not exist ! " + e.getMessage());
        }
    }
}
