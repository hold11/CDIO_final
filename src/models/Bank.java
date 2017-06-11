package models;/*
           ,                                             |
          /#\         _         _     _    ___   ___     | Project: Matador - CDIO_final
         /###\       | |__   _ | | __| |  /_  | /_  |    | Version: v0.1.0
        /#####\      | '_ \ / \| |/ _  |    | |   | |    |
       /##,-,##\     | | | | O | | ( | |   _| |_ _| |_   | Anders Wiberg Olsen (s165241), Valentin Leon Christensen (s152735),
      /##(   )##\    |_| |_|\_/|_|\__,_|  |_____|_____|  | Iman Chelhi (s165228), Troels Just Christoffersen (s120052),
     /#.--   --.#\                                       | Sebastian Tibor Bakonyvári (s145918)
    /`           ´\                                      |
*/

import java.util.List;
import java.util.ArrayList;

public class Bank
{
    private static final List<BankAccount> bankAccounts = new ArrayList<>();

    public static BankAccount createBankAcct() {
        BankAccount bankAccount = new BankAccount();
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public static BankAccount createBankAcct(int startingBalance) {
        BankAccount bankAccount = new BankAccount(startingBalance);
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public static List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
