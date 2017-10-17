package se.wikstroem.io;

import se.wikstroem.domain.Customer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * En klass som har som uppgift att skriva senaste passet till fil
 */
public class PtLog {

    private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public void logSession(Customer customer) {

        String name = customer.getName();
        String personalNumber = customer.getPersonalNumber();
        LocalDateTime now = LocalDateTime.now();

        StringBuilder logline = new StringBuilder();
        logline.append(name).append(", ").append(personalNumber).append(", ").append(now.format(DATE_TIME_FORMATTER)).append("\n");

        try (BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter("resources/ptlog.txt", true))) {

            bufferedWriter.write(logline.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
