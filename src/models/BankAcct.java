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

import fields.LandPlot;
import fields.Ownable;

import java.util.ArrayList;
import java.util.List;

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

    public int getGrossWorth(Player player) {
        Ownable[] properties = Ownable.getOwnedOwnables();
        grossWorth = player.getPlayerAcct().getBalance();
        for (Ownable o : properties) {
            if (o.getOwner() == player) {
                grossWorth += o.getPrice();
                if (o instanceof LandPlot) {
                    grossWorth += ((LandPlot) o).getHouseCount() * ((LandPlot) o).getHousePrice();
                }
            }
        }
        return grossWorth;
    }

    public int getNetWorth(Player player) {
        Ownable[] properties = Ownable.getOwnedOwnables();
        netWorth = player.getPlayerAcct().getBalance();
        for (Ownable o : properties) {
            if (o.getOwner() == player) {
                netWorth += o.getPrice()/2;
                if (o instanceof LandPlot) {
                    netWorth += (((LandPlot) o).getHouseCount() * ((LandPlot) o).getHousePrice())/2;
                }
            }
        }
        return netWorth;

    }
}
