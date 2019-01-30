package com.westbank;

import com.westbank.config.*;
import com.westbank.demo.DataInitializer;
import com.westbank.ws.logging.XmlLoggingInInterceptor;
import com.westbank.ws.logging.XmlLoggingOutInterceptor;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class PortalApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PortalApplication.class);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(DataConfig.class,
                        HelperConfig.class,
                        ServiceConfig.class,
                        ServiceClientConfig.class,
                        RemoteConfig.class,
                        DataInitializer.class).web(WebApplicationType.NONE)
                .child(PortalApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
//        SpringApplication.run(PortalApplication.class, args);
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus springBus = new SpringBus();
        springBus.getInInterceptors().add(new XmlLoggingInInterceptor());
        springBus.getOutInterceptors().add(new XmlLoggingOutInterceptor());
        return springBus;
    }

}
