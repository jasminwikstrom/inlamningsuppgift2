package se.wikstroem.domain;

import org.junit.Assert;
import org.junit.Test;
import se.wikstroem.io.CustomerDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDatabaseTest {

    @Test
    public void testInitCustomers() {

        CustomerDatabase customerDatabase = new CustomerDatabase();
        customerDatabase.initDatabase();
        List<Customer> customers = customerDatabase.getCustomers();

        Assert.assertEquals(14, customers.size());

    }

    @Test
    public void testFindUserByName() throws Exception {

        CustomerDatabase customerDatabase = new CustomerDatabase();

        Customer customer = new Customer();
        customer.setName("Jörgen Olovsson");
        customer.setPersonalNumber("8905312323");
        customer.setLastPaymentDate("2017-09-22");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        customerDatabase.setCustomers(customers);

        Customer foundCustomer = customerDatabase.searchCustomer("Jörgen Olovsson");

        Assert.assertNotNull(foundCustomer);
    }

    @Test
    public void testFindUserByPersonalNumber() throws Exception {

        CustomerDatabase customerDatabase = new CustomerDatabase();

        Customer customer = new Customer();
        customer.setName("Jörgen Olovsson");
        customer.setPersonalNumber("8905312323");
        customer.setLastPaymentDate("2017-09-22");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        customerDatabase.setCustomers(customers);

        Customer foundCustomer = customerDatabase.searchCustomer("8905312323");

        Assert.assertNotNull(foundCustomer);
    }


    @Test
    public void  testFindActiveUser() throws  Exception {

        LocalDate nowDate = LocalDate.now();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        Customer activeCustomer = new Customer();
        activeCustomer.setName("Anna Johansson");
        activeCustomer.setPersonalNumber("8905312323");
        activeCustomer.setLastPaymentDate(nowDate.minusMonths(6L).toString());
        List<Customer> customers = new ArrayList<>();
        customers.add(activeCustomer);
        customerDatabase.setCustomers(customers);

        Customer foundCustomer = customerDatabase.searchCustomer("Anna johansson");

        Assert.assertNotNull(foundCustomer);
        Assert.assertEquals(CustomerStatus.ACTIVE, foundCustomer.getStatus());



    }

    @Test
    public void  testFindInactiveUser() throws  Exception {

        LocalDate nowDate = LocalDate.now();
        CustomerDatabase customerDatabase = new CustomerDatabase();
        Customer inactiveUser = new Customer();
        inactiveUser.setName("Anna Johansson");
        inactiveUser.setPersonalNumber("8905312323");
        inactiveUser.setLastPaymentDate(nowDate.minusMonths(13L).toString());
        List<Customer> customers = new ArrayList<>();
        customers.add(inactiveUser);
        customerDatabase.setCustomers(customers);

        Customer foundCustomer = customerDatabase.searchCustomer("Anna johansson");

        Assert.assertNotNull(foundCustomer);
        Assert.assertEquals(CustomerStatus.INACTIVE, foundCustomer.getStatus());



    }

}