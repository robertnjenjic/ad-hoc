package com.robertn.adhoc.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author Robert Njenjic
 */
public abstract class BaseIT {

    private static String API_URL = "http://localhost:8085/ad-hoc/reports";
    private static Client client;

    @BeforeAll
    public static void _setUp() throws Exception {
        client = ClientBuilder.newClient();
    }

    private WebTarget getTarget() {
        return client
                .target(API_URL);
    }

    protected List<Object> getEntity(Map<String, List<Object>> params) {
        Response response = getResponse(params);

        return response.readEntity(new GenericType<List<Object>>() {
        });
    }

    protected Response getResponse(Map<String, List<Object>> params) {
        WebTarget target = getTarget();

        for (Entry<String, List<Object>> entry : params.entrySet()) {
            String param = entry.getKey();
            for (Object o : entry.getValue()) {
                target = target.queryParam(param, o);
            }
        }

        return target
                .request(MediaType.APPLICATION_JSON)
                .get();
    }
}
