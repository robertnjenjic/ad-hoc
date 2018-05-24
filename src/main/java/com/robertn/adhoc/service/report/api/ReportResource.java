package com.robertn.adhoc.service.report.api;

import com.robertn.adhoc.service.common.Dimension;
import com.robertn.adhoc.service.common.Metric;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Robert Njenjic
 */
@Path("/reports")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "reports")
public interface ReportResource {

    @GET
    @ApiOperation(value = "AD-HOC reports generator", notes = ""
            + "<ul>"
            + "<li><b>Dimensions</b> - are descriptions or characteristics of metric data that can be viewed, broken down, and compared in a report</li>"
            + "<li><b>Metrics</b> - are quantitative information about visitor activity, such as views, clicks, pinches, touches, swipes and so on.</li>"
            + "<li><b>Time/date range</b> - specify a time range relatively (last_N_[days|weeks|months] - e.g. last_2_weeks, last_7_days) </li>"
            + "</ul>")
    Response generate(
            @ApiParam(value = "Dimensions") @QueryParam("dimensions") List<Dimension> dimensions,
            @ApiParam(value = "Metrics") @QueryParam("metrics") List<Metric> metrics,
            @ApiParam(value = "Time/date range - last_N_[days|weeks|months]") @QueryParam("range") String range,
            @ApiParam(value = "Number of entries to skip") @QueryParam("offset") Integer offset,
            @ApiParam(value = "Max number of entries to return") @QueryParam("limit") Integer limit
    );
}
