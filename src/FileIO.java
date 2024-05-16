package src;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    private String customerDataPath = "data/CustomerData.csv";
    private String allTimeCustomerData = "data/AllTimeCustomerData.csv";
    private String adminDataPath = "data/AdminData.csv";
    private String statisticsDataPath = "data/Statistics.csv";
    private ErrorHandler er = new ErrorHandler();

    public void saveCustomerData(Customer customer, ArrayList<Customer> customers) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(customerDataPath));
             PrintWriter pw2 = new PrintWriter(new FileWriter(allTimeCustomerData, true))) {

            pw.println("ID, Name, Phone Number, Ticket Number");

            if (new File(allTimeCustomerData).length() == 0) {
                pw2.println("ID, Name, Phone Number, Ticket Number, Times Visited");
            }

            for (Customer c : customers) {
                pw.println(c.getCustomerID() + ", " + c.getFirstName() + ", " + c.getPhoneNumber() + ", " + c.getTicketNumber());
                pw2.println(c.getCustomerID() + ", " + c.getFirstName() + ", " + c.getPhoneNumber() + ", " + c.getTicketNumber() + ", 0");
            }

        } catch (IOException e) {
            er.saveCustomerDataError();
        }
    }

    public void getAllCustomerData(ArrayList<Customer> customers) {
        try {
            Scanner scan = new Scanner(new File(allTimeCustomerData));
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            er.getAllCustomerDataError();
        }
    }

    public void getCurrentCustomerData(ArrayList<Customer> customers) {
        if (!customers.isEmpty()) {
            System.out.println(customers.get(customers.size() - 1));
        } else {
            er.getCustomerDataError();
        }
    }

    public void timesVisited(Customer customer) {
        try (BufferedReader br = new BufferedReader(new FileReader(allTimeCustomerData));
             PrintWriter pw = new PrintWriter(new FileWriter(allTimeCustomerData + ".temp"))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length >= 5 && data[2].equals(customer.getPhoneNumber())) {
                    int timesVisited = Integer.parseInt(data[4].trim()) + 1;
                    pw.println(data[0] + ", " + data[1] + ", " + data[2] + ", " + data[3] + ", " + timesVisited);
                } else {
                    pw.println(line);
                }
            }

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            er.timesVisistedError();
        }
        File file = new File(allTimeCustomerData);
        File tempFile = new File(allTimeCustomerData + ".temp");
        if (tempFile.renameTo(file)) {
            System.out.println("Times visited updated successfully.");
        } else {
            System.out.println("Failed to update times visited.");
        }
    }

    public void removeCustomer(Customer customer) {
        try (BufferedReader br = new BufferedReader(new FileReader(customerDataPath));
             PrintWriter pw = new PrintWriter(new FileWriter(customerDataPath + ".temp"))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length >= 3 && data[2].equals(customer.getPhoneNumber())) {
                    continue;
                }
                pw.println(line);
            }

        } catch (IOException e) {
            er.removeCustomerError();
        }

        File file = new File(customerDataPath);
        File tempFile = new File(customerDataPath + ".temp");
        if (tempFile.renameTo(file)) {
            System.out.println("Customer removed successfully.");
        } else {
            System.out.println("Failed to remove customer.");
        }
    }

    public void generateAdminCode(Company company) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(adminDataPath, true))) {
            int adminCode = (int) (Math.random() * 10000);
            pw.println(company.getName() + ": Admin Code: " + adminCode);
        } catch (IOException e) {
            er.generateAdminCodeError();
        }
    }

    public String adminLogin(int adminCode) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(adminDataPath))) {
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(": Admin Code: ");
                if (userData.length == 2 && Integer.parseInt(userData[1].trim()) == adminCode) {
                    String username = userData[0].trim();
                    System.out.println("You are logged in as " + username);
                    return username;
                }
            }
        } catch (IOException | NumberFormatException e) {
            er.adminLogin();
        }
        return null;
    }

    public void saveTicketCount(int ticketCount) {
        try (FileWriter fw = new FileWriter(statisticsDataPath)) {
            fw.write(ticketCount + "\n");
        } catch (IOException e) {
        er.saveTicketCountError();
        }
    }

    public int getTicketCount(String statisticsDataPath) {
        try {
            File file = new File(statisticsDataPath);
            Scanner sc = new Scanner(file);
            if (sc.hasNextLine()) {
                return Integer.parseInt(sc.nextLine());
            }
            sc.close();
        } catch(IOException e) {
            er.getTicketCountError();
        }
        return 0;
    }
}
