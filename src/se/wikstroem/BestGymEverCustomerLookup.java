package se.wikstroem;

import se.wikstroem.domain.Customer;
import se.wikstroem.io.CustomerDatabase;
import se.wikstroem.domain.CustomerStatus;
import se.wikstroem.io.PtLog;

import java.util.Scanner;


/**
 * Applikation som söker efter kund (personnummer/namn)
 * och registrerar kundens senaste pass, om kunden är aktiv.
 */
public class BestGymEverCustomerLookup {

    public static void main(String[] args) {

        CustomerDatabase customerDatabase = new CustomerDatabase();
        customerDatabase.initDatabase();

        Scanner scanner = new Scanner(System.in);


        System.out.println("Skriv in namn eller personnummer. Ange för och efternamn: ");
        String searchString = scanner.nextLine();

        Customer customer = customerDatabase.searchCustomer(searchString);

        if (customer == null){
            System.out.println("Kunden finns inte i systemet");

        } else if (customer.getStatus() != null && customer.getStatus().equals(CustomerStatus.ACTIVE)){
            System.out.println("Kunden är aktiv, ditt pass sparas");
            PtLog ptLog = new PtLog();
            ptLog.logSession(customer);

        } else if ( customer.getStatus()!= null && customer.getStatus().equals(CustomerStatus.INACTIVE)) {
            System.out.println("Kunden har inte betalat sin årsavgift de senaste 12 månaderna");
        } else {
            System.out.println("Något gick fel, prova igen");
        }


    }
}
