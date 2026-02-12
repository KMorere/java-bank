import custom.*;
import daos.OperationDao;
import models.*;
import daos.AccountDao;
import utils.AccountNumber;
import utils.SqlQuery;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Account accountDao = new AccountDao().read(1);
        System.out.println(accountDao.displayAccount());

        Operation operation = new OperationDao().read(1);
        System.out.println(operation.toString());

        new AccountDao().create(
                new AccountFactory().createAccount(AccountType.SAVING, 500, 1));
    }

    private static void test_init() {
        Logger logger = CustomLogger.getInstance(Logger.getLogger(Main.class.getName())).logger;

        Bank newBank = new Bank("Banque impopulaire");

        logger.info("Creating models.Person 1 and 2...");
        Person npc1 = new Person("Jackie", "Chène");
        Person npc2 = new Person("Jacques", "Ièsse");

        logger.info("Creating models.Account for person 1 and 2.");
        try {
            newBank.createAccount(npc1, AccountType.CHECKING);
            newBank.createAccount(npc2, AccountType.CHECKING);

            Account acc1 = npc1.getAccount();
            Account acc2 = npc2.getAccount();

            System.out.println(acc1 + "\n" + acc2);

            acc1.depositMoney(250);
            acc1.transferMoney(acc2, 100);
            acc2.withdrawMoney(50);

            System.out.println(acc1 + "\n" + acc2);

            System.out.println(AccountNumber.GetInstance().generateAccountNumber());
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
