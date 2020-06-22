package pl.obol;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TestDate {

    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime dt = LocalDateTime.now();
        System.out.println("Original dateTime: "+dt);

        String dateTimeFormatted = formatter.format(dt);
        System.out.println("Date formatted: "+dateTimeFormatted);

        LocalDateTime dateTimeParsed = LocalDateTime.parse(dateTimeFormatted, formatter);
        System.out.println("DateTime parsed: "+dateTimeParsed);


        System.out.println("==============");
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock);
        LocalTime lt = LocalTime.now(clock);
        LocalTime lt2 = LocalTime.now(ZoneId.of("Europe/Warsaw"));
        String stringlt = timeFormatter.format(LocalTime.now(clock));
        String stringlt2 = timeFormatter.format(LocalTime.now(ZoneId.of("Europe/Warsaw")));
        System.out.println("Unformatted: "+lt+" & "+lt2);
        System.out.println("Formatted: "+stringlt+" & "+stringlt2);
    }
}
