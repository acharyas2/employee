package com.march.example.demo.repository;

import com.march.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value="select * from employee order by age desc", nativeQuery = true)
    public List<Employee> getEmployeesByAgeDesc();


    @Query(value="select * from employee where age> :age order by age asc", nativeQuery = true)
    public List<Employee> getEmployeesByAgeGreaterThan(@Param("age") int age);
}
