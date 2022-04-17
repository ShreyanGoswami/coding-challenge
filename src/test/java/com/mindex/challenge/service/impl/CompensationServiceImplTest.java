package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationBaseUrl;
    private String compensationIdUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationBaseUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateAndRead() {
        // Arrange data
        Employee emp = new Employee();
        String employeeId = "b7839309-3348-463b-a7e3-5de1c168beb3";
        String expectedFirstName = "Paul";
        emp.setEmployeeId(employeeId);
        Compensation comp = new Compensation();
        comp.setSalary(90000);
        comp.setEffectiveDate(new Date(2022,4,10));
        comp.setEmployee(emp);

        // Call API
        Compensation res = restTemplate.postForObject(compensationBaseUrl, comp, Compensation.class);

        // Check if the data has been added
        Compensation actualResult = restTemplate.getForObject(compensationIdUrl, Compensation.class, employeeId);

        // Assert
        assertNotNull(res);
        assertNotNull(actualResult);
        assertEquals(actualResult.getEmployee().getEmployeeId(), employeeId);
        assertEquals(actualResult.getEmployee().getFirstName(), expectedFirstName);

    }

    @Test
    public void testRead() {
        // Arrange data
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        int expectedSalary = 130000;
        String expectedEffectiveDate = "04-22-2022";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-yyyy").withZone(ZoneId.of("UTC"));

        // Make API call
        Compensation res = restTemplate.getForObject(compensationIdUrl, Compensation.class, employeeId);

        // Check
        assertNotNull(res);
        assertEquals(res.getSalary(), expectedSalary);
        assertEquals(
                df.format(res.getEffectiveDate().toInstant()),
                expectedEffectiveDate
        );
    }
}
