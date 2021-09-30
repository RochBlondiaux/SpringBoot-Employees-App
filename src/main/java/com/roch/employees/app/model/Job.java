package com.roch.employees.app.model;

import lombok.Data;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Data
public class Job {

    private String name;
    private double salary;

    public Job() {
    }
}
