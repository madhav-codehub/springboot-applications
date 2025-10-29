package com.codehub.springconfigurationproperties.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "app")
@Data
public class AppConfigProperties {
    private String name;
    private int version;
    private boolean enabled;
    private List<String> features;

    @NestedConfigurationProperty
    private Map<String, String> settings;
}
