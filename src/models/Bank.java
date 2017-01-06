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
    private static List<BankAcct> bankAccts = new ArrayList<>();

    public static BankAcct createBankAcct() {
        BankAcct bankAcct = new BankAcct();
        bankAccts.add(bankAcct);
        return bankAcct;
    }

    public static ArrayList<BankAcct> getBankAccts() {

    }
}
