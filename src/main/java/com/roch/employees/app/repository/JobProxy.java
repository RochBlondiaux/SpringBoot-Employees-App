package com.roch.employees.app.repository;

import com.roch.employees.app.CustomProperties;
import com.roch.employees.app.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Slf4j(topic = "Employee Proxy")
@Component
public class JobProxy {

    @Autowired
    private CustomProperties properties;

    /**
     * Get all available jobs from API.
     *
     * @return An iterable of all jobs.
     */
    public Iterable<Employee> getJobs() {
        String baseURL = properties.getApiUrl() + "/jobs";

        RestTemplate template = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = template.exchange(
                baseURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        log.debug("HTTP status for get all jobs: {}", response.getStatusCode());
        return response.getBody();
    }
}
