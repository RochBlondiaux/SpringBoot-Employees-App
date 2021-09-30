package com.roch.employees.app.repository;

import com.roch.employees.app.CustomProperties;
import com.roch.employees.app.model.Job;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Slf4j(topic = "Job Proxy")
@Component
public class JobProxy {

    @Autowired
    private CustomProperties properties;

    /**
     * Create a new job.
     *
     * @param job to create.
     * @return created job.
     */
    public Job create(@NonNull Job job) {
        String baseURl = properties.getApiUrl() + "/jobs";

        RestTemplate template = new RestTemplate();
        HttpEntity<Job> httpEntity = new HttpEntity<>(job);
        ResponseEntity<Job> response = template.exchange(
                baseURl,
                HttpMethod.POST,
                httpEntity,
                Job.class);
        log.debug("HTTP status for create job: {}", response.getStatusCode());
        return response.getBody();
    }

    /**
     * Update an existing job.
     *
     * @param job to update.
     * @return updated job or null if it failed.
     */
    public Job update(@NonNull Job job) {
        String baseURL = properties.getApiUrl() + "/job";

        RestTemplate template = new RestTemplate();
        HttpEntity<Job> httpEntity = new HttpEntity<>(job);
        ResponseEntity<Job> response = template.exchange(
                baseURL,
                HttpMethod.PATCH,
                httpEntity,
                Job.class
        );
        log.debug("HTTP status for update job: {}", response.getStatusCode());
        return response.getBody();
    }

    /**
     * Delete an existing job.
     *
     * @param job to delete
     * @return true if it has been deleted successfully, otherwise false
     */
    public boolean delete(@NonNull Job job) {
        String baseURL = properties.getApiUrl() + "/jobs";

        RestTemplate template = new RestTemplate();
        HttpEntity<Job> httpEntity = new HttpEntity<>(job);
        ResponseEntity<Void> response = template.exchange(
                baseURL,
                HttpMethod.DELETE,
                httpEntity,
                Void.class
        );
        log.debug("HTTP status for delete job: {}", response.getStatusCode());
        return response.getStatusCode().is2xxSuccessful();
    }

    /**
     * Get a specific job by its id.
     *
     * @return A fulfilled job object.
     */
    public Job getJob(@NonNull String name) {
        String baseURL = properties.getApiUrl() + "/jobs/" + name;

        RestTemplate template = new RestTemplate();
        ResponseEntity<Job> response = template.exchange(
                baseURL,
                HttpMethod.GET,
                null,
                Job.class);
        log.debug("HTTP status for get specific job: {}", response.getStatusCode());
        return response.getBody();
    }

    /**
     * Get all available jobs from API.
     *
     * @return An iterable of all jobs.
     */
    public Iterable<Job> getJobs() {
        String baseURL = properties.getApiUrl() + "/jobs";

        RestTemplate template = new RestTemplate();
        ResponseEntity<Iterable<Job>> response = template.exchange(
                baseURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        log.debug("HTTP status for get all jobs: {}", response.getStatusCode());
        return response.getBody();
    }
}
