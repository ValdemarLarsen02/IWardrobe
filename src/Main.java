package src;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Customer c = new Customer("1", "Micke", "52232323", 2);
        FileIO io = new FileIO();
        Company c1 = new Company(50, "Skum");
        Company c2 = new Company(100, "Retro");
        //io.generateAdminCode(c2);
        io.adminLogin(4959);
      }
    }

