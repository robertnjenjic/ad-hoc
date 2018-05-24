package com.robertn.adhoc.service.common;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

/**
 * @author Robert Njenjic
 */
@Data
@Builder
public class TimeRange {

    private Date from;
    private Date to;
}
