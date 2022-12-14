package com.example.copring.repos;

import com.example.copring.models.Company;
import com.example.copring.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);
    Page<Employee> findAllBy(Pageable page);

    List<Employee> findAllByCompany(Company company);
}
