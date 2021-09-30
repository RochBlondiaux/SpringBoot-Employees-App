package com.roch.employees.app.service;

import com.roch.employees.app.model.Employee;
import com.roch.employees.app.repository.EmployeeProxy;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Service
@Data
public class EmployeeService {

    @Autowired
    private EmployeeProxy proxy;

    public Employee getEmployee(@NonNull int id) {
        return proxy.getEmployee(id);
    }

    public boolean delete(@NonNull Employee employee) {
        return proxy.delete(employee);
    }

    public Employee save(@NonNull Employee employee) {
        if (employee.getId() == 0)
            return proxy.create(employee);
        return proxy.update(employee);
    }

    public Iterable<Employee> getEmployees() {
        return proxy.getEmployees();
    }

}
