package com.pluralsight.customer;

import com.pluralsight.Account.Account;
import com.pluralsight.deli.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerFileManger {
    private final static String dataDirectory = "CustomerInformation/";
    private static String header;

    /*public static List<Order> readFromCSV(){

    }*/
    public static void writeToCSV(Account account){
        try{
            String accountIDFolderPath = dataDirectory + account.getId() + "/";
            Path path = Paths.get(accountIDFolderPath);
            Files.createDirectories(path);
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
            String datePosted = date.format(formatter);
            /*String header = bfw.write();*/
            for(Order order: account.getCustomer().getOrders()){
                BufferedWriter bfw = new BufferedWriter(new FileWriter(accountIDFolderPath + datePosted));
                bfw.write(order.encodedString());
                bfw.close();
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage() + "<---File write error when producing order history.");
        }
    }
}
