package com.robertn.adhoc.config;

import com.robertn.adhoc.service.report.impl.ReportResourceImpl;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author Robert Njenjic
 */
@Configuration
@ApplicationPath("ad-hoc")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ReportResourceImpl.class);

        register(ApiListingResource.class);
        register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setTitle("AD-HOC");
        config.setVersion("v1");
        config.setBasePath("/ad-hoc");
        config.setResourcePackage("com.robertn");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
