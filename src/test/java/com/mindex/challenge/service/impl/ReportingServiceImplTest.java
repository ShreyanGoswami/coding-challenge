package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingServiceImplTest {

    private String reportIdUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportIdUrl = "http://localhost:" + port + "/reports/{id}";
    }

    @Test
    public void testRead() {
        // Arrange data
        String id = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        int expectedNumberOfReports = 4;

        // Call API
        ReportingStructure res = restTemplate.getForObject(reportIdUrl, ReportingStructure.class, id);

        // Check result
        assertNotNull(res);
        assertNotNull(res.getEmployee());
        assertEquals(res.getEmployee().getEmployeeId(), id);
        assertEquals(res.getNumberOfReports(), expectedNumberOfReports);
    }

    @Test
    public void testReadWithEmployeeReportingToMultipleEmployees() {
        // Arrange data
        String id = "4790683e-6856-4548-ab4d-429bc22eddec";
        int expectedNumberOfReports = 3;

        // Call API
        ReportingStructure res = restTemplate.getForObject(reportIdUrl, ReportingStructure.class, id);

        // Check result
        assertNotNull(res);
        assertNotNull(res.getEmployee());
        assertEquals(res.getEmployee().getEmployeeId(), id);
        assertEquals(res.getNumberOfReports(), expectedNumberOfReports);
    }
}
