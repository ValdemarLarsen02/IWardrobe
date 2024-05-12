package src;

import java.util.ArrayList;

public class Customer {
    private int id;
    private String name;

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}
