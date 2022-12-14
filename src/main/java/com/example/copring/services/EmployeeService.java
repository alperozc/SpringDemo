package com.example.copring.services;

import com.example.copring.dto.EmployeeDTO;
import com.example.copring.models.Company;
import com.example.copring.models.Employee;
import com.example.copring.repos.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor

public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    public Employee saveEmployee(EmployeeDTO employee, Company company){
        Employee e = new Employee();
        BeanUtils.copyProperties(employee, e);
        e.setCompany(company);
        return employeeRepository.save(e);
    }

    public Employee deleteEmployee(long id){
        Employee e = employeeRepository.findById(id).orElse(null);
        if (e != null) employeeRepository.deleteById(id);
        return e;
    }

    public Employee updateEmployee(long id, EmployeeDTO employee){
        Employee e = employeeRepository.findById(id).orElse(null);
        if (e == null) return null;
        BeanUtils.copyProperties(employee, e);
        return employeeRepository.save(e);
    }
    public List<Employee> getEmployeesByCompanyId(long id) {
        Company company = companyService.getCompanyById(id);
        if (company == null) return null;
        return employeeRepository.findAllByCompany(company);
    }
}
