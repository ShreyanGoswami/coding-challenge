package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.exception.CompensationNotFoundException;
import com.mindex.challenge.exception.EmployeeNotFoundException;
import com.mindex.challenge.exception.ExceptionMessageConstants;
import com.mindex.challenge.service.CompensationService;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private CompensationRepository compensationRepository;
    private EmployeeRepository employeeRepository;

    public CompensationServiceImpl(CompensationRepository compensationRepository, EmployeeRepository employeeRepository) {
        this.compensationRepository = compensationRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Compensation create(Compensation comp) {
        String employeeId = comp.getEmployee().getEmployeeId();
        Employee emp = employeeRepository.findByEmployeeId(employeeId);
        if (emp == null) {
            throw new EmployeeNotFoundException(
                    String.format(ExceptionMessageConstants.EMPLOYEE_NOT_FOUND, employeeId)
            );
        }
        compensationRepository.insert(comp);
        return comp;
    }

    @Override
    public Compensation read(String employeeId) {
        Compensation comp = compensationRepository.getCompensationsByEmpId(employeeId);
        if (comp == null) {
            throw new CompensationNotFoundException(
                    String.format(ExceptionMessageConstants.COMPENSATION_NOT_FOUND, employeeId)
            );
        }
        Employee emp = employeeRepository.findByEmployeeId(employeeId);
        if (emp == null) {
            throw new EmployeeNotFoundException(
                    String.format(ExceptionMessageConstants.EMPLOYEE_NOT_FOUND, employeeId)
            );
        }
        comp.setEmployee(emp);
        return comp;
    }
}
