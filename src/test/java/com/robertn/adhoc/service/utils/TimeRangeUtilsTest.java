package com.robertn.adhoc.service.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.robertn.adhoc.service.common.TimeRange;
import org.junit.jupiter.api.Test;

/**
 * @author Robert Njenjic
 */
class TimeRangeUtilsTest {

    @Test
    void parse() {
        TimeRange last_1_days = TimeRangeUtils.parse("last_1_days");
        assertTrue(last_1_days != null);

        TimeRange last_1_day = TimeRangeUtils.parse("last_1_day");
        assertTrue(last_1_day == null);

        TimeRange last_2_weeks = TimeRangeUtils.parse("last_2_weeks");
        assertTrue(last_2_weeks != null);

        TimeRange last_1_weeks = TimeRangeUtils.parse("last_1_weeks");
        assertTrue(last_1_weeks != null);

        TimeRange last_5_days = TimeRangeUtils.parse("last_5_days");
        assertTrue(last_5_days != null);

        TimeRange last_40_days = TimeRangeUtils.parse("last_40_days");
        assertTrue(last_40_days != null);

        TimeRange last_6_months = TimeRangeUtils.parse("last_6_months");
        assertTrue(last_6_months != null);

        TimeRange all = TimeRangeUtils.parse("all");
        assertTrue(all != null && all.getFrom() == null && all.getTo() == null);
    }

}