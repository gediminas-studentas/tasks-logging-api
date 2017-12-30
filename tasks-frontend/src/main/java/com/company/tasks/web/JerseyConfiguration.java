package com.company.tasks.web;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        super();
        packages("com.company.tasks.web");
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        configureSwagger();
    }

    private BeanConfig configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("v1");
        beanConfig.setSchemes(new String[] { "http" });
        beanConfig.setResourcePackage("com.company.tasks.web");
        beanConfig.setBasePath("/");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);

        return beanConfig;
    }
}
