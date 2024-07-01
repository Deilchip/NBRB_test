package org.example.constants;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ConstantsValidator {
    public static final int ZERO= 0;
    public static final Date MIN_DATE;
    public static final Date MAX_DATE= Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        MIN_DATE = calendar.getTime();
    }
}
