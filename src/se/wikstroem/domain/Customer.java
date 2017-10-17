package se.wikstroem.domain;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * En klass som motsvarar en kund i systemet
 */
public class Customer {

    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String name;
    private String personalNumber;
    private CustomerStatus status;
    private LocalDate lastPaymentDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getDateAsString() {
        return DATE_FORMATTER.format(lastPaymentDate);
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) throws ParseException {
        this.lastPaymentDate = LocalDate.parse(lastPaymentDate, DATE_FORMATTER);
    }
}