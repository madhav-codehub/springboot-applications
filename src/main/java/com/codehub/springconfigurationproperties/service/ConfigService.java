package com.codehub.springconfigurationproperties.service;

import com.codehub.springconfigurationproperties.config.AppConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {
    private final AppConfigProperties properties;

    public String getAppDetails(){
        return String.format("App Name: %s, Version: %d, Enabled: %b, Features: %s, Settings: %s",
                properties.getName(),
                properties.getVersion(),
                properties.isEnabled(),
                properties.getFeatures(),
                properties.getSettings());
    }
}
