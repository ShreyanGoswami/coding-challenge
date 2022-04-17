package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.service.ReportsService;
import jdk.internal.org.jline.utils.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Service
public class ReportsServiceImpl implements ReportsService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportsServiceImpl.class);

    private EmployeeRepository employeeRepository;

    ReportsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ReportingStructure read(String employeeId) {
        int count = 0;
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        Set<String> visited = new HashSet<>();
        if (employee == null) {
            throw new EmployeeNotFoundException(
                    String.format("Unable to find employee with id %s", employeeId)
            );
        }
        Queue<Employee> q = new LinkedList<>();
        q.offer(employee);
        while (!q.isEmpty()) {
            Employee current = q.poll();
            if (current.getDirectReports() != null && !current.getDirectReports().isEmpty()) {
                for (Employee report : current.getDirectReports()) {
                    // Check in case there is a cycle in the reports
                    if (visited.contains(report.getEmployeeId())) {
                        LOG.warn("Possible cycle in reporting structure at employee id [{}]", report.getEmployeeId());
                        continue;
                    }
                    q.add(employeeRepository.findByEmployeeId(report.getEmployeeId()));
                    visited.add(report.getEmployeeId());
                    count++;
                }
            }
        }
        return new ReportingStructure(employee, count);
    }
}
