package com.mindex.challenge.dao;

import com.mindex.challenge.data.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    @Query("{'employeeId' : ?0}")
    Employee findByEmployeeId(String employeeId);
}
