import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = CustomLogger.getInstance(Logger.getLogger(Main.class.getName())).logger;

        logger.info("Creating Person 1 and 2...");
        Person npc1 = new Person("Jackie", "Chène");
        Person npc2 = new Person("Jacques", "Ièsse");

        logger.info("Creating Account for person 1 and 2.");
        Account acc1 = new Account("FR-0123-3210", npc1);
        Account acc2 = new Account("FR-4567-7654", npc2);

        System.out.println(acc1 + "\n" + acc2);

        acc1.depositMoney(250);
        acc1.transferMoney(acc2, 100);
        acc2.takeoutMoney(50);

        System.out.println(acc1 + "\n" + acc2);
    }
}
