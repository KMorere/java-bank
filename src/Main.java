import custom.AccountAlreadyExistsException;
import custom.AccountDoesNotExistException;
import custom.InsufficientBalanceException;

import java.util.logging.Logger;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        Logger logger = CustomLogger.getInstance(Logger.getLogger(Main.class.getName())).logger;

        Bank newBank = new Bank("Banque impopulaire");

        logger.info("Creating Person 1 and 2...");
        Person npc1 = new Person("Jackie", "Chène");
        Person npc2 = new Person("Jacques", "Ièsse");

        logger.info("Creating Account for person 1 and 2.");
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
