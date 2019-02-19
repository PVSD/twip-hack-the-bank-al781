package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
public class Main {
    public static ArrayList<String> log = new ArrayList<String>();
    public static ArrayList<bankAccount> aryLst = new ArrayList<bankAccount>();
    public static bankAccount someGuysBankAccount = new bankAccount("Abraham Pennebacker",0);

    public static void main(String[] args) {
        // write your code here
        NumberFormat fmt = NumberFormat.getNumberInstance();
        fmt.setMinimumFractionDigits(2);
        fmt.setMaximumFractionDigits(2);
        String name;

        do {
            Scanner kbReader = new Scanner(System.in);
            System.out
                    .print("Please enter the name to whom the account belongs. (\"Exit\" to abort) ");
            name = kbReader.nextLine();
            if (!name.equalsIgnoreCase("EXIT")) {
                if (name.equalsIgnoreCase("debug")) {
                    DebugMode();
                }
                System.out.print("Please enter the amount of the deposit. ");
                double amount = kbReader.nextDouble();
                System.out.println(" "); // gives an eye pleasing blank line
                // between accounts
                bankAccount theAccount = new bankAccount(name, amount);
                aryLst.add(theAccount);
                String s = "Created Bank Account for " + name + "containing" + amount;
                log.add(s);
                for (int i = 0; i < aryLst.size(); i++)
                    for (int j = 0; j < aryLst.size(); j++)
                        if (aryLst.get(j).balance > aryLst.get(j+1).balance)
                            Collections.swap(aryLst,j,j+1);
            }
        } while (!name.equalsIgnoreCase("EXIT"));

        // Search aryLst and print out the name and amount of the largest bank
        ListIterator iter = aryLst.listIterator();
        bankAccount ba = (bankAccount) iter.previous();
        double maxBalance = ba.balance; // set last account as the winner so far
        String maxName = ba.name;
        while (iter.hasPrevious()) {
            ba = (bankAccount) iter.previous();
            if (ba.balance > maxBalance) {
                // We have a new winner, chicken dinner
                maxBalance = ba.balance;
                maxName = ba.name;
            }
        }
        System.out.println(" ");
        System.out.println("The account with the largest balance belongs to "
                + maxName + ".");
        System.out.println("The amount is $" + fmt.format(maxBalance) + ".");

    }


    public static void DebugMode() {
        Scanner numScan = new Scanner(System.in);
        System.out.println("DEBUG MODE INITIATED\nPICK AN OPTION");
        System.out.println("1) LIST ACCOUNTS");
        System.out.println("2) DRAIN ACCOUNTS");
        System.out.println("3) EXIT");
        switch(numScan.nextInt())
            {
                case 1:
                    ListAccounts();
                    break;
                case 2:
                    DrainAccounts();
                    break;
                case 3:
                    //Does nothing
                    break;
                default:
                    System.out.println("ENTER A VALID CODE");
                    DebugMode();
                    break;
            }
    }
    public static void ListAccounts()
    {
        for (int i = 0; i < aryLst.size(); i++)
            {
                System.out.println(aryLst.get(i).name + " : " + aryLst.get(i).balance);
                DebugMode();
            }
    }
    public static void DrainAccounts()
    {
        for (int i = 0; i > aryLst.size(); i++)
            {
                double a =  aryLst.get(i).balance;
                aryLst.get(i).withdraw(a);
                String s = "Withdraw of " + a + " from " + aryLst.get(i).name;
                log.add(s);
                someGuysBankAccount.deposit(a);
                s = "Deposit of " + a + " to " + someGuysBankAccount.name;
                log.add(s);
            }
    }
}