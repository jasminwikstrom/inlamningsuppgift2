package se.wikstroem.io;

import se.wikstroem.domain.Customer;
import se.wikstroem.domain.CustomerStatus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Objekt som innehåller systemets alla kunder.
 * Kunderna läses in ifrån en txt-fil som ligger under resources/customer.txt
 */
public class CustomerDatabase {

    private List<Customer> customers;

    public void initDatabase() {

        customers = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/customer.txt"))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                Customer customer = new Customer();

                String personalNumber = line.substring(0, line.indexOf(','));

                customer.setPersonalNumber(personalNumber);
                customer.setName(line.substring(12).trim());

                line = bufferedReader.readLine();
                customer.setLastPaymentDate(line);

                if (personalNumber.length() == 10) {
                    customers.add(customer);
                }
            }


        } catch (Exception e) {
            throw new RuntimeException("Users could not be initiated", e);
        }

    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Customer searchCustomer(String searchString) {


        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(searchString) || customer.getPersonalNumber().equals(searchString)) {

                if (customer.getLastPaymentDate().isAfter(LocalDate.now().minusYears(1L))) {
                    customer.setStatus(CustomerStatus.ACTIVE);
                } else {
                    customer.setStatus(CustomerStatus.INACTIVE);
                }
                return customer;
            }

        }
        return null;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
