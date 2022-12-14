package com.example.copring.repos;

import com.example.copring.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);
    Page<Company> findAllBy(Pageable page);

    Optional<Company> findByPhone(String phone);
}

