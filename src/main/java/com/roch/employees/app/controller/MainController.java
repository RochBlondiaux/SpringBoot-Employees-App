package com.roch.employees.app.controller;

import com.roch.employees.app.repository.JobProxy;
import com.roch.employees.app.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.StreamSupport;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Slf4j(topic = "Main Controller")
@Controller
public class MainController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JobProxy jobProxy;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("jobs_count", StreamSupport.stream(jobProxy.getJobs().spliterator(), false).count());
        model.addAttribute("employees_count", StreamSupport.stream(employeeService.getEmployees().spliterator(), false).count());

        return "index";
    }
}
