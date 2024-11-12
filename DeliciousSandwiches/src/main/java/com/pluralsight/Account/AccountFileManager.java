package com.pluralsight.Account;

import com.pluralsight.customer.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountFileManager {
    private final static String dataFile = "Accounts.csv";
    private static List<Account> accounts = readFromCSV();
    private static String header;

    public static List<Account> readFromCSV() {
        List<Account> accounts = new ArrayList<>();
        try{
            BufferedReader bfr = new BufferedReader(new FileReader(dataFile));
            header = bfr.readLine(); //Avoid reading file header
            String input;

            while((input=bfr.readLine()) != null){
                String[] accountData = input.split("\\|");
                accounts.add(new Account(accountData[0],
                             new Customer(accountData[1],accountData[2],accountData[3],accountData[4])));
            }

            bfr.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage() + "<--- File Read Error");
        }

        return accounts;
    }
    public static void addAccount(Account account){
        accounts.add(account);
    }
    public static void writeToCSV(){
        try{
            BufferedWriter bfw = new BufferedWriter(new FileWriter(dataFile));
            bfw.write(header + "\n");
            for(Account a: accounts ){
                bfw.write(a.encodedString());
            }

            bfw.close();
        }
        catch(IOException e){
            System.out.println(e.getMessage() + "<--- File Write Error");
        }
    }
}
