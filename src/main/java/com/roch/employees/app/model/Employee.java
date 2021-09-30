package com.roch.employees.app.model;

import lombok.Data;
import lombok.Setter;

import java.util.Date;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Data
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private Date hireDate;

    @Setter
    private String mail;

    @Setter
    private String password;

    @Setter
    private Job job;


}
