package com.horia.reminder.api.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidator {

    private static final Pattern PHONE_PATTERN = Pattern.compile("(\\+)?(\\d{10,12})");
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}");

    private static final Integer STANDARD_TITLE_LENGTH = 40;
    private static final Integer STANDARD_DESCRIPTION_LENGTH = 250;

    public static boolean validatePhoneField(String phoneNumber) {
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean validateTitleField(String title) {
        return title.length() <= STANDARD_TITLE_LENGTH;
    }

    public static boolean validateDescriptionField(String description) {
        return description.length() <= STANDARD_DESCRIPTION_LENGTH;
    }

    public static boolean validateDueDateAndTime(LocalDate date, String time) {
        Matcher matcher = DATE_TIME_PATTERN.matcher(date + "T" + time);
        if (!matcher.matches()) {
            return false;
        }

        // Check date validity
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(date + "T" + time);
            Date now = new Date();
            if (parsedDate.before(now)) {
                return false;
            }
        }catch (ParseException pe) {
            pe.printStackTrace();
            return false;
        }

        return true;
    }
}
