package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {
    private String path = "data/items.csv";
    ErrorHandler er = new ErrorHandler();
    Scanner scan = new Scanner(path);

    public void saveCustomerData(Customer customer, ArrayList<Customer> customers) {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("ID" + ", " + "Name" + "," + "\n");
            for (Customer c : customers) {
                fw.write(c.getId() + ", " + c.getName() + "\n");
            }
        } catch (IOException e) {
            er.errorMessage();
        }
    }

    public void getAllCustomerData(ArrayList<Customer> customers) {
        try {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split("\n");
                for (String s : data) {
                    System.out.println(s);
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            er.errorMessage();
        }
    }


    //Metode der henter seneste customer
    //Metode der opdatere "times visited", hvis customeren allerede er i csv filen
    public void removeCustomerData(Customer customer) {


    }
}
