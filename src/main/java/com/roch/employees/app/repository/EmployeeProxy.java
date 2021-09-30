package com.roch.employees.app.repository;

import com.roch.employees.app.CustomProperties;
import com.roch.employees.app.model.Employee;
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
@Slf4j(topic = "Employee Proxy")
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties properties;

    /**
     * Create a new employee.
     *
     * @param employee to create.
     * @return created employee or null if it failed.
     */
    public Employee create(@NonNull Employee employee) {
        String baseURL = properties.getApiUrl() + "/employees";

        RestTemplate template = new RestTemplate();
        HttpEntity<Employee> httpEntity = new HttpEntity<>(employee);
        ResponseEntity<Employee> response = template.exchange(
                baseURL,
                HttpMethod.POST,
                httpEntity,
                Employee.class);
        log.debug("HTTP status for create employee: {}", response.getStatusCode());
        return response.getBody();
    }

    /**
     * Update an existing employee.
     *
     * @param employee to update.
     * @return updated employee or null if it failed.
     */
    public Employee update(@NonNull Employee employee) {
        String baseURL = properties.getApiUrl() + "/employees";

        RestTemplate template = new RestTemplate();
        HttpEntity<Employee> httpEntity = new HttpEntity<>(employee);
        ResponseEntity<Employee> response = template.exchange(
                baseURL,
                HttpMethod.PATCH,
                httpEntity,
                Employee.class
        );
        log.debug("HTTP status for update employee: {}", response.getStatusCode());
        return response.getBody();
    }

    /**
     * Delete an existing employee.
     *
     * @param employee to delete
     * @return true if it has been deleted successfully, otherwise false
     */
    public boolean delete(@NonNull Employee employee) {
        String baseURL = properties.getApiUrl() + "/employees";

        RestTemplate template = new RestTemplate();
        HttpEntity<Employee> httpEntity = new HttpEntity<>(employee);
        ResponseEntity<Void> response = template.exchange(
                baseURL,
                HttpMethod.DELETE,
                httpEntity,
                Void.class
        );
        log.debug("HTTP status for delete employee: {}", response.getStatusCode());
        return response.getStatusCode().is2xxSuccessful();
    }

    /**
     * Get all available employees from API.
     *
     * @return An iterable of all employees.
     */
    public Iterable<Employee> getEmployees() {
        String baseURL = properties.getApiUrl() + "/employees";

        RestTemplate template = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = template.exchange(
                baseURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        log.debug("HTTP status for get all employees: {}", response.getStatusCode());
        return response.getBody();
    }
}
