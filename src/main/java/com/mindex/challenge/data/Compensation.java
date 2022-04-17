package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Compensation {
    Employee employee;
    int salary;
    @JsonFormat(pattern = "MM-dd-yyyy")
    Date effectiveDate;

    public Compensation() {

    }

    Compensation(Employee emp, int salary, Date effectiveDate) {
        this.employee = emp;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getSalary() {
        return salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }
}
