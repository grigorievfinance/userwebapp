package org.softmaker.userwebapp.web.converter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.softmaker.userwebapp.util.DateTimeUtil.parseLocalDate;
import static org.softmaker.userwebapp.util.DateTimeUtil.parseLocalTime;

public class DateTimeFormatters {
    public static class LocalDateFormatter implements Formatter<LocalDate>{
        @Override
        public LocalDate parse(String s, Locale locale) throws ParseException {
            return parseLocalDate(s);
        }

        @Override
        public String print(LocalDate date, Locale locale) {
            return date.format(DateTimeFormatter.BASIC_ISO_DATE);
        }
    }
    public static class LocalTimeFormatter implements Formatter<LocalTime>{
        @Override
        public LocalTime parse(String s, Locale locale) throws ParseException {
            return parseLocalTime(s);
        }

        @Override
        public String print(LocalTime localTime, Locale locale) {
            return localTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        }
    }
}
