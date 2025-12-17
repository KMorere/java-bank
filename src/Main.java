import java.util.logging.Logger;

public class Main {
    private static final Logger logger = new CustomLogger().getLogger();

    public static void main(String[] args) {
        logger.info("Creating Person 1 and 2...");
        Person npc1 = new Person("Jackie", "Chène");
        Person npc2 = new Person("Jacques", "Ièsse");

        logger.info("Creating Account for person 1 and 2.");
        Account acc1 = new Account("FR-0123-3210", npc1);
        Account acc2 = new Account("FR-4567-7654", npc2);

        System.out.println(acc1 + "\n" + acc2);
    }
}
