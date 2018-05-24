package com.robertn.adhoc.service.report.impl;

import com.robertn.adhoc.service.common.Dimension;
import com.robertn.adhoc.service.common.Metric;
import com.robertn.adhoc.service.common.TimeRange;
import com.robertn.adhoc.service.report.api.ReportResource;
import com.robertn.adhoc.service.report.impl.repository.ReportRepository;
import com.robertn.adhoc.service.utils.TimeRangeUtils;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robert Njenjic
 */
@Slf4j
@Component
public class ReportResourceImpl implements ReportResource {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public Response generate(List<Dimension> dimensions, List<Metric> metrics, String range, Integer offset, Integer limit) {
        if ((dimensions == null || dimensions.isEmpty()) && (metrics ==  null || metrics.isEmpty())) {
            throw new BadRequestException("Dimensions or Metrics should not be empty");
        }

        TimeRange timeRange = TimeRangeUtils.parse(range);
        if (timeRange == null) {
            throw new BadRequestException("Invalid time range");
        }

        log.info("Generating report for dimensions[{}], metrics[{}], range[{}]", dimensions, metrics, range);

        Object generateReport = reportRepository.generateReport(offset, limit, dimensions, metrics, timeRange);

        log.info("Generated report {}", generateReport);

        return Response.ok(generateReport).build();
    }
}
