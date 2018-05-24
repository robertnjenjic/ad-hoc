package com.robertn.adhoc.service.report.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.robertn.adhoc.service.BaseIT;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Robert Njenjic
 */
class ReportResourceImplT extends BaseIT {

    private Map<String, List<Object>> queryParams;

    @BeforeEach
    public void setUp() throws Exception {
        queryParams = new HashMap<>();
    }

    @Test
    public void testClient() throws Exception {
        // AD_NAME, AD_ID
        queryParams.put("dimensions", Arrays.asList("AD_NAME", "AD_ID"));

        // Get Response object
        Response response = getResponse(queryParams);
        assertTrue(response != null && response.getStatus() == 200);

        // Get Entity
        List<Object> entity = getEntity(queryParams);
        assertTrue(entity != null && !entity.isEmpty());
    }

    @Test
    public void generate() throws Exception {
        // DATE
        queryParams.clear();
        queryParams.put("dimensions", Arrays.asList("DATE"));
        List<Object> entity = getEntity(queryParams);
        assertTrue(entity != null && !entity.isEmpty());

        // DATE, CAMPAIGN_NAME, IMPRESSIONS
        queryParams.clear();
        queryParams.put("dimensions", Arrays.asList("DATE", "CAMPAIGN_NAME"));
        queryParams.put("metrics", Arrays.asList("IMPRESSIONS"));
        entity = getEntity(queryParams);
        assertTrue(entity != null && !entity.isEmpty());

        // DATE, CAMPAIGN_NAME, IMPRESSIONS
        queryParams.clear();
        queryParams.put("dimensions", Arrays.asList("DATE", "CAMPAIGN_NAME"));
        queryParams.put("metrics", Arrays.asList("IMPRESSIONS"));
        entity = getEntity(queryParams);
        assertTrue(entity != null && !entity.isEmpty());

        // DATE, CAMPAIGN_NAME, IMPRESSIONS, CLICKS, TOUCHES, SWIPES, PINCHES
        queryParams.clear();
        queryParams.put("dimensions", Arrays.asList("DATE", "CAMPAIGN_NAME"));
        queryParams.put("metrics", Arrays.asList("IMPRESSIONS", "CLICKS", "TOUCHES", "SWIPES", "PINCHES"));
        entity = getEntity(queryParams);
        assertTrue(entity != null && !entity.isEmpty());

        // DATE, CAMPAIGN_NAME, AD_ID, AD_NAME, IMPRESSIONS, CLICKS, TOUCHES, SWIPES, PINCHES
        queryParams.clear();
        queryParams.put("dimensions", Arrays.asList("DATE", "CAMPAIGN_NAME", "AD_ID", "AD_NAME"));
        queryParams.put("metrics", Arrays.asList("IMPRESSIONS", "CLICKS", "TOUCHES", "SWIPES", "PINCHES"));
        entity = getEntity(queryParams);
        assertTrue(entity != null && !entity.isEmpty());

        // DATE, last_2_days
        queryParams.clear();
        queryParams.put("dimensions", Arrays.asList("DATE"));
        queryParams.put("range", Arrays.asList("last_2_days"));
        entity = getEntity(queryParams);
        assertTrue(entity != null && !entity.isEmpty() && entity.size() == 2);

        // DATE, last_1_weeks
        queryParams.clear();
        queryParams.put("dimensions", Arrays.asList("DATE"));
        queryParams.put("range", Arrays.asList("last_2_weeks"));
        entity = getEntity(queryParams);
        assertTrue(entity != null && !entity.isEmpty() && entity.size() == 14);

        // FAIL - Bad request
        queryParams.clear();
        Response response = getResponse(queryParams);
        assertTrue(response != null && response.getStatus() == 400);

        // FAIL - Not found
        queryParams.put("dimensions", Arrays.asList("NONE"));
        response = getResponse(queryParams);
        assertTrue(response != null && response.getStatus() == 404);
    }
}