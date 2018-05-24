package com.robertn.adhoc.service.report.impl.repository;

import com.robertn.adhoc.service.common.Dimension;
import com.robertn.adhoc.service.common.Metric;
import com.robertn.adhoc.service.common.TimeRange;
import java.util.List;

/**
 * @author Robert Njenjic
 */
public interface ReportRepositoryCustom {

    List<Object> generateReport(Integer offset, Integer limit, List<Dimension> dimensions, List<Metric> metrics,
            TimeRange timeRange);
}
