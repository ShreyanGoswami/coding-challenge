package com.mindex.challenge.exception;

import com.mindex.challenge.controller.CompensationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackageClasses = {CompensationController.class})
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value={EmployeeNotFoundException.class, CompensationNotFoundException.class})
    protected ResponseEntity handleEmployeeNotFoundException(RuntimeException ex, ServletWebRequest req) {
        LOG.error("Error: [{}]", ex.getMessage());
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", Instant.now().toString());
        responseBody.put("status", HttpStatus.NOT_FOUND);
        responseBody.put("error", "Employee not found");
        responseBody.put("message", ex.getMessage());
        responseBody.put("path", req.getRequest().getRequestURI());

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }
}
