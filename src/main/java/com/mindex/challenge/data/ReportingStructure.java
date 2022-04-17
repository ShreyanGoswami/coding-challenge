package com.mindex.challenge.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReportingStructure {
    Employee emp;
    int numberOfReports;

    public ReportingStructure(Employee emp, int numberOfReports) {
        this.emp = emp;
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee() {
        return this.emp;
    }

    public int getNumberOfReports() {
        return this.numberOfReports;
    }

    public void setEmployee(Employee e) {
        this.emp = e;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }


}
