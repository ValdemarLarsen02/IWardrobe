package src;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
      Company C1 = new Company(150, "Skum");
      Customer c = new Customer("Micke", 200);
      Customer c2 = new Customer("Bob", 100);
      FileIO io = new FileIO();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(c);
        customers.add(c2);

      io.saveCustomerData(c, customers);

      io.getAllCustomerData(customers);

    }

}