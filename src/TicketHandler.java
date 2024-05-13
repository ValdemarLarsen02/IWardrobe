package src;

public class TicketHandler {


        /*Vi skal bruge noget Ticket Type eller noget i den stil. Jeg har i min organizer lavet en
    boolean der hedder "Acessory" der tjekker om vi har at gøre med en jakke eller ej, og organisere
    garderoben derefter.

         */
    public static void ticketGeneration(Customer customer) {
        System.out.println("Creating ticket for user " + customer.phoneNumber);

        // Opretter vores ticket nummer
        int ticketNumber = generateTicketNumber();
        customer.ticketNumber = ticketNumber;

        // kald gemt customer til csv fil

        System.out.println("Ticket number generated: " + customer.customerID);
        // Lige pt printer den bare her skal vi opdatere til customeren.
        System.out.println("Ticket number " + ticketNumber + " has been generated for " + customer.firstName);
    }

    private static int generateTicketNumber() {
        // SKal laves mere kompliceret med checks osv osv
        return (int)(Math.random() * 10000);
    }


}