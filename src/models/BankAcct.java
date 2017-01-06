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

public class BankAcct
{
    private int balance;
    private int grossWorth;
    private int netWorth;

    public BankAcct() {
        this.balance = 0;
    }

    public BankAcct(int balance) {
        this.balance = balance;
    }

    public void deposit(int balance) {
        this.balance += Math.abs(balance);
        // Recalculate gross worth and net worth
    }

    public void withdraw(int balance) {
        this.balance -= Math.abs(balance);
        // Recalculate gross worth and net worth
    }

    public void transfer(int amount,Player transferToPlayer) {
        this.withdraw(amount);
        transferToPlayer.getPlayerAcct().deposit(amount);
    }

    public int getBalance() { return this.balance; }
}
