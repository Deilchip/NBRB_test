package org.example.constant;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@UtilityClass
public class DataConstants {
    public static final double[] VALID_CURR_OF_RATE = {1, 30};
    public static final long[] VALID_CURR_ID = {1, 2};
    public static final String[] VALID_CURR_NAME = {"some", "text"};
    public static final int[] VALID_CUR_SCALE = {180, 89};
    public static final String[] VALID_CURR_ABBREVIATION = {"some", "text"};
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final Date[] VALID_DATES;
    public static final Date[] NON_VALID_DATES;

    static {
        try {
            VALID_DATES = new Date[]{
                    DATE_FORMAT.parse("2022-01-01"),
                    DATE_FORMAT.parse("2022-02-02")
            };
            NON_VALID_DATES = new Date[]{
                    DATE_FORMAT.parse("9000-01-01"),
                    DATE_FORMAT.parse("1300-02-02")
            };
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}