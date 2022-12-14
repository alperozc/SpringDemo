package com.example.copring;

import com.example.copring.dto.CompanyDTO;
import com.example.copring.dto.EmployeeDTO;
import com.example.copring.models.Company;
import com.example.copring.services.CompanyService;
import com.example.copring.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CopringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopringApplication.class, args);
    }

}
