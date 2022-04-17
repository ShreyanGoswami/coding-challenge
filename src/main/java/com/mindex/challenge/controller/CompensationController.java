package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.impl.CompensationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    private CompensationServiceImpl compensationService;

    CompensationController(CompensationServiceImpl compensationService) {
        this.compensationService = compensationService;
    }

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received request to create compensation for employee with id [{}]",
                compensation.getEmployee().getEmployeeId());
        compensationService.create(compensation);
        return compensation;
    }

    @GetMapping("/compensation/{employeeId}")
    public Compensation read(@PathVariable String employeeId) {
        LOG.debug("Received request to get compensation for employee [{}]", employeeId);
        return compensationService.read(employeeId);
    }
}
