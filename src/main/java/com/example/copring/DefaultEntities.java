package com.example.copring;

import com.example.copring.dto.CompanyDTO;
import com.example.copring.dto.EmployeeDTO;
import com.example.copring.models.Company;
import com.example.copring.services.CompanyService;
import com.example.copring.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultEntities {

    private static final Logger log = LoggerFactory.getLogger(DefaultEntities.class);

    @Bean
    CommandLineRunner initDatabase(CompanyService companyService, EmployeeService employeeService) {
        return args -> {
            log.info("Preloading " + companyService.saveCompany(new CompanyDTO("Apple", "1 Infinite Loop, Cupertino, CA 95014", "1-408-996-1010")));
            log.info("Preloading " + companyService.saveCompany(new CompanyDTO("Google", "1600 Amphitheatre Parkway, Mountain View, CA 94043", "1-650-253-0000")));
            log.info("Preloading " + employeeService.saveEmployee(new EmployeeDTO("Employee 1", "Title 1"), companyService.getCompanyById(1)));
            log.info("Preloading " + employeeService.saveEmployee(new EmployeeDTO("Employee 2", "Title 2"), companyService.getCompanyById(1)));
            log.info("Preloading " + employeeService.saveEmployee(new EmployeeDTO("Employee 3", "Title 3"), companyService.getCompanyById(2)));
            log.info("Preloading " + employeeService.saveEmployee(new EmployeeDTO("Employee 4", "Title 4"), companyService.getCompanyById(2)));
        };
    }

}