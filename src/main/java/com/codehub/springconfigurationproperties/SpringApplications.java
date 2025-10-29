package com.codehub.springconfigurationproperties;

import com.codehub.springconfigurationproperties.config.AppConfigProperties;
import com.codehub.springconfigurationproperties.service.ConfigService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfigProperties.class)
public class SpringApplications {

    public static void main(String[] args) {
        var context = SpringApplication.run(SpringApplications.class, args);
        ConfigService service = context.getBean(ConfigService.class);
        System.out.println(service.getAppDetails());

    }

}
