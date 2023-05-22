package com.onexshield.wmm;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class main {
    public static void main(String[] args) {
        Date a = new Date();
        System.out.println(a);
        Instant instant = a.toInstant();
        System.out.println(instant);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        System.out.println(localDate);
        localDate.plusDays(10);
        System.out.println(localDate);
        System.out.println(a);
    }
}
