package com.example.copring.controllers;

import com.example.copring.dto.CompanyDTO;
import com.example.copring.dto.EmployeeDTO;
import com.example.copring.models.Company;
import com.example.copring.models.Employee;
import com.example.copring.services.CompanyService;
import com.example.copring.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor

public class CompanyController {
    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @GetMapping
    private List<Company> getCompanies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        if (size > 100) size = 100;
        return companyService.getCompanies(page, size);
    }

    @GetMapping("/{id}")
    private Company getCompany(@PathVariable long id){
        return companyService.getCompanyById(id);
    }

    @PostMapping
    private ResponseEntity<?> createCompany(@RequestBody CompanyDTO company){
        Company c = companyService.saveCompany(company);
        if (c == null) return ResponseEntity.status(409).body("Bu bilgilerden birine sahip bir şirket var");
        return ResponseEntity.status(200).body(c);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteCompany(@PathVariable long id){
        Company c = companyService.deleteCompany(id);
        if (c == null) return ResponseEntity.status(404).body("Şirket bulunamadı");
        return ResponseEntity.status(200).body(c);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateCompany(@PathVariable long id, @RequestBody CompanyDTO company){
        Company c = companyService.updateCompany(id, company);
        if (c == null) return ResponseEntity.status(404).body("Şirket bulunamadı");
        return ResponseEntity.status(200).body(c);
    }

    @GetMapping("/{id}/employees")
    private ResponseEntity<?> getEmployees(@PathVariable long id){
        List<Employee> e = employeeService.getEmployeesByCompanyId(id);
        if (e == null) return ResponseEntity.status(404).body("Şirket bulunamadı");
        return ResponseEntity.status(200).body(e);
    }

    @PostMapping("/{id}/employees")
    private ResponseEntity<?> createEmployee(@PathVariable long id, @RequestBody EmployeeDTO employee){
        Company c = companyService.getCompanyById(id);
        if (c == null) return ResponseEntity.status(404).body("Şirket bulunamadı");
        Employee e = employeeService.saveEmployee(employee, c);
        return ResponseEntity.status(200).body(e);
    }

    @DeleteMapping("/{id}/employees/{eid}")
    private ResponseEntity<?> deleteEmployee(@PathVariable long id, @PathVariable long eid){
        Company c = companyService.getCompanyById(id);
        if (c == null) return ResponseEntity.status(404).body("Şirket bulunamadı");
        Employee e = employeeService.deleteEmployee(eid);
        if (e == null) return ResponseEntity.status(404).body("Çalışan bulunamadı");
        return ResponseEntity.status(200).body(e);
    }

    @PutMapping("/{id}/employees/{eid}")
    private ResponseEntity<?> updateEmployee(@PathVariable long id, @PathVariable long eid, @RequestBody EmployeeDTO employee){
        Company c = companyService.getCompanyById(id);
        if (c == null) return ResponseEntity.status(404).body("Şirket bulunamadı");
        Employee e = employeeService.updateEmployee(eid, employee);
        if (e == null) return ResponseEntity.status(404).body("Çalışan bulunamadı");
        return ResponseEntity.status(200).body(e);
    }
}
