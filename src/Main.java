import custom.*;
import daos.ClientDao;
import models.*;
import daos.AccountDao;
import utils.AccountNumber;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        //operation_test();

        displayOptions();
    }

    private static void displayOptions() {
        System.out.println("=====[Welcome]=====");
        System.out.println("Select an operation :"+
                "\n\t 0. Display clients."+
                "\n\t 1. Display accounts of a client."+
                "\n\t 2. Deposit money."+
                "\n\t 3. Withdraw money.");

        switch (scan.nextInt()) {
            case 0:
                for(Client client : new ClientDao().readAll()) {
                    System.out.println("\t" + client + " | " + client.getAccount());
                }
                break;
            case 1:
                displayAccount();
                break;
            case 2:
                startOperation("DEPOSIT");
                break;
            case 3:
                startOperation("WITHDRAW");
                break;
            default:
                displayOptions();
                break;
        }
    }

    private static void displayAccount() {
        System.out.println("Select an account ID :");

        if (scan.hasNextInt()) {
            System.out.println(new ClientDao().read(scan.nextInt()).getAccount());
        }
    }

    private static void startOperation(String _type) {
        System.out.println("Select an account :");
        if (scan.hasNextInt()) {
            Account account = new AccountDao().read(scan.nextInt());
            System.out.println("Select an amount :");

            if (scan.hasNextInt()) {
                if (_type.equalsIgnoreCase("DEPOSIT")) {
                    account.depositMoney(scan.nextInt());
                }
                else if (_type.equalsIgnoreCase("WITHDRAW")) {
                    try {
                        account.withdrawMoney(scan.nextInt());
                    } catch (InsufficientBalanceException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(account);
            }
        }
    }

    private static void operation_test() {
        Account acc1 = new AccountDao().read(1);
        Account acc2 = new AccountDao().read(2);

        acc1.depositMoney(150);
        System.out.println(acc1 + "" + acc2);
    }
}
