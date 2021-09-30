package com.roch.employees.app;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Data
@Configurable
@ConfigurationProperties(prefix = "com.roch.employees.app")
public class CustomProperties {

    private String apiUrl;

}
