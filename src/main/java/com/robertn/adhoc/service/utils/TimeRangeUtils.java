package com.robertn.adhoc.service.utils;

import com.robertn.adhoc.service.common.TimeRange;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Robert Njenjic
 */
public class TimeRangeUtils {

    private final static String DAYS = "days";
    private final static String WEEKS = "weeks";
    private final static String MONTHS = "months";

    private static final Pattern PATTERN = Pattern.compile("last_(\\d+)_(" + DAYS + "|" + WEEKS + "|" + MONTHS + ")");

    /**
     * Parse relatively time range.
     *
     * e.g. last_N_[days|weeks|months]
     *</p>
     * Allowed:
     *     <ul>
     *         <li>days</li>
     *         <li>weeks</li>
     *         <li>months</li>
     *     </ul>
     *
     * @param range time range
     * @return {@link TimeRange}
     */
    public static TimeRange parse(String range) {
        if (range == null) {
            return TimeRange.builder().build();
        } else {
            GregorianCalendar from = new GregorianCalendar();

            Matcher matcher = PATTERN.matcher(range);

            if (matcher.find()) {
                Integer time = Integer.parseInt(matcher.group(1));
                String period = matcher.group(2);

                switch (period) {
                    case DAYS:
                        from.add(Calendar.DAY_OF_YEAR, -time);
                        break;
                    case WEEKS:
                        from.add(Calendar.WEEK_OF_YEAR, -time);
                        break;
                    case MONTHS:
                        from.add(Calendar.MONTH, -time);
                        break;
                    default:
                        return null;
                }
            } else {
                return null;
            }

            return TimeRange.builder()
                    .to(new Date(new java.util.Date().getTime()))
                    .from(new Date(from.getTime().getTime()))
                    .build();
        }
    }

    private TimeRangeUtils() {
    }
}
